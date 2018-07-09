package ru.otus.HW09;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import ru.otus.HW09.exceptions.BadDataModelException;
import ru.otus.HW09.models.DataSet;
import static ru.otus.HW08.JsonLib.getAllNonTransientFields;

public class Executor {

    private final static String CONDITION_SQL_QUERY_TEMPLATE = " WHERE id=?";
    private final static String LOAD_SQL_QUERY_TEMPLATE = "SELECT * FROM %s";
    private final static String SAVE_SQL_QUERY_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";

    private Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public <T extends DataSet> void save(T dataSet) throws SQLException {
        List<Field> fields = getAllNonTransientFields(new ArrayList<>(), dataSet.getClass());
        fields = fields.stream().filter(
            field -> field.getAnnotation(Column.class) != null &&
                field.getAnnotation(Id.class) == null
        ).collect(Collectors.toList());

        String tableName = null;
        try {
            tableName = getTableNameFromClass(dataSet.getClass());
        } catch (BadDataModelException e) {
            System.out.println(e.getStackTrace());
        }

        String query = String.format(
            SAVE_SQL_QUERY_TEMPLATE,
            tableName,
            String.join(", ", fields.stream().map(
                Executor::getColumnNameFromField).collect(Collectors.toList())),
            String.join(", ", fields.stream().map(
                field -> "?").collect(Collectors.toList()))
        );

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int i = 0;
            for (Field field : fields) {
                try {
                    statement.setObject(++i, getGetterMethod(field).invoke(dataSet));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    System.out.print(e.getStackTrace());
                }
            }

            try {
                statement.execute();
            } catch (SQLException e) {
                System.out.print(e.getStackTrace());
            }
        }
    }

    public <T extends DataSet> T load(long id, Class<T> cls) throws SQLException {
        String query = null;
        Object result = null;

        try {
            query = String.format(
                LOAD_SQL_QUERY_TEMPLATE + CONDITION_SQL_QUERY_TEMPLATE,
                getTableNameFromClass(cls)
            );
        } catch (BadDataModelException e) {
            System.out.println(e.getStackTrace());
        }

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try(ResultSet queryResult = statement.executeQuery()) {
                if (!queryResult.next()) {
                    return null;
                }

                result = getFilledDataSet(cls, queryResult);
            }
        }

        return (T) result;
    }

    public <T extends DataSet> List<T> load(Class<T> cls) throws SQLException {
        String query = null;
        List<T> resultList = new ArrayList<>();

        try {
            query = String.format(
                LOAD_SQL_QUERY_TEMPLATE,
                getTableNameFromClass(cls)
            );
        } catch (BadDataModelException e) {
            System.out.println(e.getStackTrace());
        }

        try(Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    resultList.add(
                        getFilledDataSet(cls, resultSet)
                    );
                }
            }
        }

        return resultList;
    }

    public <T extends DataSet> T getFilledDataSet(Class<T> cls, ResultSet queryResult) throws SQLException {
        Object result = null;

        try {
            result = cls.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException |
            IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println(e.getStackTrace());
        }

        List<Field> fields = getAllNonTransientFields(new ArrayList<>(), cls);

        for (Field field : fields) {
            if(field.getAnnotation(Column.class) != null) {
                Object columnValue = queryResult.getObject(
                    getColumnNameFromField(field),
                    field.getType()
                );

                try {
                    getSetterMethod(field).invoke(result, columnValue);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getStackTrace());
                }
            }
        }

        return (T) result;
    }

    private static String getTableNameFromClass(Class cls) throws BadDataModelException {
        Annotation tableAnnotation = cls.getAnnotation(Table.class);
        if(tableAnnotation != null) {
            String tableName = ((Table) tableAnnotation).name();
            if (tableName.isEmpty()) {
                tableName = cls.getName();
            }
            return tableName;
        } else {
            throw new BadDataModelException("This data model is bad");
        }
    }

    private static String getColumnNameFromField(Field field) {
        String columnName = field.getAnnotation(Column.class).name();
        if (columnName.isEmpty()) {
            columnName = field.getName();
        }
        return columnName;
    }

    private static String getMethodName(Field field, String prefix) throws NoSuchMethodException {
        return prefix + field.getName().substring(0, 1).toUpperCase() +
            field.getName().substring(1);
    }

    private static Method getSetterMethod(Field field) throws NoSuchMethodException {
        return field.getDeclaringClass().getMethod(
            getMethodName(field, "set"),
            field.getType()
        );
    }

    private static Method getGetterMethod(Field field) throws NoSuchMethodException {
        return field.getDeclaringClass().getMethod(
            getMethodName(field, "get")
        );
    }

}
