package com.cdp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static <T> List<T> parseCSV(String path, Class<T> clazz, String separator) {
        // LOG.info(String.format("Starting parse CSV document on path %s ", path));
        List<T> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                T newObject = clazz.newInstance();
                String[] split = line.split(separator);
                list.add(createElement(newObject, split));
            }
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            // LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    private static <T> T createElement(T object, String[] split) {
        // LOG.error("Create object from CSV");
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(CSVElement.class)) {
                fields[i].setAccessible(true);
                try {
                    fields[i].set(object, parse(fields[i], split[i]));
                } catch (IllegalArgumentException | IllegalAccessException | InstantiationException |
                        InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    //LOG.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    private static Object parse(Field field, String value) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        Object parseValue = null;
        if (field.getType().isPrimitive()) {
            if (field.getType().equals(int.class)) {
                parseValue = Integer.parseInt(value);
            } else if (field.getType().equals(double.class)) {
                parseValue = Double.parseDouble(value);
            } else if (field.getType().equals(float.class)) {
                parseValue = Float.parseFloat(value);
            } else if (field.getType().equals(byte.class)) {
                parseValue = Byte.parseByte(value);
            } else if (field.getType().equals(boolean.class)) {
                parseValue = Boolean.parseBoolean(value);
            } else if (field.getType().equals(short.class)) {
                parseValue = Short.parseShort(value);
            } else if (field.getType().equals(long.class)) {
                parseValue = Long.parseLong(value);
            }
        } else {
            parseValue = field.getType().getConstructor(String.class).newInstance(value);
        }
        return parseValue;
    }

}
