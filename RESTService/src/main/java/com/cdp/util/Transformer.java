package com.cdp.util;

import com.cdp.db.annotation.Column;
import com.cdp.db.annotation.ColumnClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transformer {
    private static final Logger LOG = LoggerFactory.getLogger(Transformer.class);

    private Transformer() {
    }

    public static <T> T toInstance(ResultSet resultSet, Class<?> clazz) throws SQLException {
        @SuppressWarnings("unchecked")
        T element = null;
        try {
            element = (T) clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)) {
                    Object value = resultSet.getObject(field.getAnnotation(Column.class).value());
                    field.set(element, value);
                }
                if (field.isAnnotationPresent(ColumnClass.class)) {
                    field.set(element, toInstance(resultSet, field.getType().newInstance().getClass()));
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage());
        }
        return element;
    }

}
