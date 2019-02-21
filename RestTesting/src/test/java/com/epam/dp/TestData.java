package com.epam.dp;

import com.cdp.util.Parser;
import com.epam.dp.annotation.SourcePath;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestData {

    @DataProvider
    public Object[] CSVData(Method method) {
        SourcePath sourceAnnotation = method.getAnnotation(SourcePath.class);
        if (sourceAnnotation != null) {
            return Parser.parseCSV(sourceAnnotation.path(), sourceAnnotation.model(), ",").toArray();
        } else return new Object[]{};
    }


}
