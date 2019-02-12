package com.cdp.utils;

import com.cdp.database.annotation.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transformers {

    public static <T> T toInstance(ResultSet resultSet, Class<?> clazz) throws SQLException {
        @SuppressWarnings("unchecked")
        T element = null;
        try {
            element = (T) clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Object value = resultSet.getObject(field.getAnnotation(Column.class).value());
                    field.setAccessible(true);
                    field.set(element, value);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return element;
    }

}
