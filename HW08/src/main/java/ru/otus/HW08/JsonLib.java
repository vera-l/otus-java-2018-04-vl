package ru.otus.HW08;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.Map;


public class JsonLib {

    public static String toJson(Object o) {
        return JSONValue.toJSONString(toValue(o));
    }

    public static Object toValue(Object o) {
        if(o == null) {
            return null;
        }

        Class type = o.getClass();

        if(type == Boolean.class) {
            return o;
        }

        if(type == String.class) {
            return o;
        }

        if (type == Character.class) {
            return String.valueOf(o);
        }

        if(o.getClass() == Long.class) {
            return o;
        }

        if(o.getClass() == Integer.class
            || o.getClass() == Short.class
            || o.getClass() == Byte.class) {
            return o;
        }

        if(o.getClass() == Double.class
            || o.getClass() == Float.class) {
            return o;
        }

        if (o instanceof Map) {
            return o;
        }

        if (o instanceof Iterable) {
            return getJsonArrayFromJavaCollection(o);
        }

        if (o.getClass().isArray()) {
            return getJsonArrayFromJavaCollection(getListFromArray(o));
        }

        return getJsonObjectFromJavaObject(o);
    }

    public static JSONArray getJsonArrayFromJavaCollection(Object iterable) {
        JSONArray jsonArray = new JSONArray();

        for (Object fieldValue : (Iterable) iterable) {
            jsonArray.add(toValue(fieldValue));
        }

        return jsonArray;
    }

    public static JSONObject getJsonObjectFromJavaObject(Object o) {
        Class cls = o.getClass();
        List<Field> fields = getAllNonTransientFields(new ArrayList<>(), cls);
        JSONObject jsonObject = new JSONObject();

        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                Object fieldValue = field.get(o);

                jsonObject.put(fieldName, toValue(fieldValue));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private static List getListFromArray(Object arr) {
        List list = new ArrayList<>();

        for (int i = 0; i < Array.getLength(arr); i++) {
            list.add(Array.get(arr, i));
        }

        return list;
    }

    public static List<Field> getAllNonTransientFields(List<Field> fields, Class cls) {
        Field[] currentFields = cls.getDeclaredFields();
        for (int i = 0; i < currentFields.length; i++) {
            if (!Modifier.isTransient(currentFields[i].getModifiers())) {
                fields.add(currentFields[i]);
            }
        }

        if (cls.getSuperclass() != null) {
            getAllNonTransientFields(fields, cls.getSuperclass());
        }

        return fields;
    }

}