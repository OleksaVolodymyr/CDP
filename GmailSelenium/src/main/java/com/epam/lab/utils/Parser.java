package com.epam.lab.utils;

import com.epam.lab.anotation.CSVElement;
import com.epam.lab.model.UsersModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    @SuppressWarnings("unchecked")
    public static <T> T XMLParse(String path) {
        JAXBContext jaxbContext;
        T newObject = null;
        try {
            jaxbContext = JAXBContext.newInstance(UsersModel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            newObject = (T) jaxbUnmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> XLSXParse(String path, T object) {
        List<T> list = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(path)))) {
            Sheet dataSheet = workbook.getSheetAt(0);
            for (Row currentRow : dataSheet) {
                T newObject = (T) object.getClass().getConstructor(String.class, String.class)
                        .newInstance(currentRow.getCell(0).toString(), currentRow.getCell(1).toString());
                list.add(newObject);
            }
        } catch (InvocationTargetException | IOException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> List<T> CSVParse(String path, T object, String separator) {
        List<T> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = read.readLine()) != null) {
                String[] split = line.split(separator);
                list.add(createElement(object, split));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static <T> T createElement(T object, String[] split) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(CSVElement.class)) {
                fields[i].setAccessible(true);
                try {
                    fields[i].set(object, parse(fields[i], split[i]));
                } catch (IllegalArgumentException | IllegalAccessException | InstantiationException |
                        InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    private static Object parse(Field field, String value) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Object parseValue = null;
        if (field.getType().isPrimitive()) {
            if (field.getType().equals(int.class)) {
                parseValue = Integer.parseInt(value);
            } else if (field.getClass().equals(double.class)) {
                parseValue = Double.parseDouble(value);
            } else if (field.getClass().equals(float.class)) {
                parseValue = Float.parseFloat(value);
            } else if (field.getClass().equals(byte.class)) {
                parseValue = Byte.parseByte(value);
            } else if (field.getClass().equals(boolean.class)) {
                parseValue = Boolean.parseBoolean(value);
            } else if (field.getClass().equals(short.class)) {
                parseValue = Short.parseShort(value);
            } else if (field.getClass().equals(long.class)) {
                parseValue = Long.parseLong(value);
            }
        } else {
            parseValue = field.getType().getConstructor(String.class).newInstance(value);
        }
        return parseValue;
    }
}
