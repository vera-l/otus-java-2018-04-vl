package ru.otus.HW09;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.otus.HW09.annotations.Column;
import ru.otus.HW09.annotations.Id;
import ru.otus.HW09.annotations.Table;
import ru.otus.HW09.exceptions.BadDataModelException;
import ru.otus.HW09.models.DataSet;
import static ru.otus.HW08.JsonLib.getAllNonTransientFields;

class Executor {

    private final static String LOAD_SQL_QUERY_TEMPLATE = "SELECT * FROM %s WHERE id=?";
    private final static String SAVE_SQL_QUERY_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";

    private Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public <T extends DataSet> void save(T user) throws BadDataModelException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, SQLException {

        List<Field> fields = getAllNonTransientFields(new ArrayList<>(), user.getClass());
        fields = fields.stream().filter(
            field -> field.getAnnotation(Id.class) == null).collect(Collectors.toList());

        PreparedStatement statement = connection.prepareStatement(
            String.format(
                SAVE_SQL_QUERY_TEMPLATE,
                getTableNameFromClass(user.getClass()),
                String.join(", ", fields.stream().map(
                    Executor::getColumnNameFromField).collect(Collectors.toList())),
                String.join(", ", fields.stream().map(
                    field -> "?").collect(Collectors.toList()))
            )
        );

        int i = 0;
        for (Field field : fields) {
            statement.setObject(++i, getGetterMethod(field).invoke(user));
        }

        statement.execute();
    }

    public <T extends DataSet> T load(long id, Class<T> cls) throws SQLException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException,
            BadDataModelException {
        Object result = cls.getDeclaredConstructor().newInstance();

        PreparedStatement statement = connection.prepareStatement(
            String.format(
                LOAD_SQL_QUERY_TEMPLATE,
                getTableNameFromClass(cls)
            )
        );
        statement.setLong(1, id);
        ResultSet queryResult = statement.executeQuery();
        if(!queryResult.next()) {
            return null;
        }

        List<Field> fields = getAllNonTransientFields(new ArrayList<>(), cls);
        for (Field field : fields) {
            if(field.getAnnotation(Column.class) != null) {
                Object columnValue = queryResult.getObject(
                    getColumnNameFromField(field),
                    field.getType()
                );

                getSetterMethod(field).invoke(result, columnValue);
            }
        }


        return (T) result;
    }

    private static String getTableNameFromClass(Class cls) throws BadDataModelException {
        Annotation tableAnnotation = cls.getAnnotation(Table.class);
        if(tableAnnotation != null) {
            String tableName = ((Table) tableAnnotation).value();
            if (tableName.isEmpty()) {
                tableName = cls.getName();
            }
            return tableName;
        } else {
            throw new BadDataModelException("This data model is bad");
        }
    }

    private static String getColumnNameFromField(Field field) {
        String columnName = field.getAnnotation(Column.class).value();
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
