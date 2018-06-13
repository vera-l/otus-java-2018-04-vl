package ru.otus.HW08;

import org.json.JSONObject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Array;


public class JsonLib {

    public static String toJson(Object o) {
        JsonObject json = getJsonObjectFromJavaObject(o);
        return json.toString();
    }

    public static JsonArray getJsonArrayFromJavaObject(Object iterable) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();

        for (Object fieldValue : (Iterable) iterable) {
            Class fieldType = fieldValue.getClass();

            if(fieldValue == null) {
                jsonArray.addNull();
                continue;
            }

            if(fieldType == boolean.class || fieldType == Boolean.class) {
                jsonArray.add((boolean) fieldValue);
                continue;
            }

            if(fieldType == String.class) {
                jsonArray.add((String) fieldValue);
                continue;
            }

            if(fieldType == long.class || fieldType == Long.class) {
                jsonArray.add((long) fieldValue);
                continue;
            }

            if(fieldType == int.class || fieldType == Integer.class
                || fieldType == short.class || fieldType == Short.class
                || fieldType == byte.class || fieldType == Byte.class
                || fieldType == char.class || fieldType == Character.class) {
                jsonArray.add((int) fieldValue);
                continue;
            }

            if(fieldType == double.class || fieldType == Double.class
                || fieldType == float.class || fieldType == Float.class) {
                jsonArray.add((double) fieldValue);
                continue;
            }

            if(fieldType == BigInteger.class) {
                jsonArray.add((BigInteger) fieldValue);
                continue;
            }

            if(fieldType == BigDecimal.class) {
                jsonArray.add((BigDecimal) fieldValue);
                continue;
            }

            if (isTypeConvertableToJsonArray(fieldValue)) {
                if (fieldValue instanceof Iterable) {
                    jsonArray.add(
                        getJsonArrayFromJavaObject((Iterable) fieldValue)
                    );
                } else {
                    jsonArray.add(
                        getJsonArrayFromJavaObject(getListFromArray(fieldValue))
                    );
                }
                continue;
            }

            jsonArray.add(getJsonObjectFromJavaObject(fieldValue));

        }

        return jsonArray.build();
    }

    public static JsonObject getJsonObjectFromJavaObject(Object o) {
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        JsonObjectBuilder json = Json.createObjectBuilder();

        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                Class fieldType = field.getType();
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1, fieldName.length());

                Method getter = cls.getDeclaredMethod(methodName);
                Object fieldValue = getter.invoke(o);

                if(fieldValue == null) {
                    json.addNull(fieldName);
                    continue;
                }

                if(fieldType == boolean.class || fieldType == Boolean.class) {
                    json.add(fieldName, (boolean) fieldValue);
                    continue;
                }

                if(fieldType == String.class) {
                    json.add(fieldName, (String) fieldValue);
                    continue;
                }

                if(fieldType == long.class || fieldType == Long.class) {
                    json.add(fieldName, (long) fieldValue);
                    continue;
                }

                if(fieldType == int.class || fieldType == Integer.class
                    || fieldType == short.class || fieldType == Short.class
                    || fieldType == byte.class || fieldType == Byte.class
                    || fieldType == char.class || fieldType == Character.class) {
                    json.add(fieldName, (int) fieldValue);
                    continue;
                }

                if(fieldType == double.class || fieldType == Double.class
                    || fieldType == float.class || fieldType == Float.class) {
                    json.add(fieldName, (double) fieldValue);
                    continue;
                }

                if(fieldType == BigInteger.class) {
                    json.add(fieldName, (BigInteger) fieldValue);
                    continue;
                }

                if(fieldType == BigDecimal.class) {
                    json.add(fieldName, (BigDecimal) fieldValue);
                    continue;
                }

                if (isTypeConvertableToJsonArray(fieldValue)) {
                    if (fieldValue instanceof Iterable) {
                        json.add(
                            fieldName,
                            getJsonArrayFromJavaObject((Iterable) fieldValue)
                        );
                    } else {
                        json.add(
                            fieldName,
                            getJsonArrayFromJavaObject(getListFromArray(fieldValue))
                        );
                    }
                    continue;
                }

                json.add(fieldName, getJsonObjectFromJavaObject(fieldValue));
            }

        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException");
        }

        return json.build();
    }

    private static boolean isTypeConvertableToSimpleJsonType(Class type) {
        return type == null
            || type == String.class
            || type == boolean.class || type == Boolean.class
            || type == long.class || type == Long.class
            || type == int.class || type == Integer.class
            || type == short.class || type == Short.class
            || type == byte.class || type == Byte.class
            || type == char.class || type == Character.class
            || type == double.class || type == Double.class
            || type == float.class || type == Float.class
            || type == BigInteger.class
            || type == BigDecimal.class;
    }

    private static boolean isTypeConvertableToJsonArray(Object value) {
        return value.getClass().isArray() || value instanceof Iterable;
    }

    private static List getListFromArray(Object arr) {
        List list = new ArrayList<>();

        for (int i = 0; i < Array.getLength(arr); i++) {
            list.add(Array.get(arr, i));
        }

        return list;
    }

    public static void prettyPrintJson(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        System.out.println(jsonObject.toString(4));
    }

}