package com.epam.dataprovider;

import com.cdp.util.Parser;
import com.epam.dataprovider.annotation.SourcePath;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestData {

    @DataProvider
    public Object[] CSVData(Method method) {
        SourcePath sourceAnnotation = method.getAnnotation(SourcePath.class);
        return Parser.parseCSV(sourceAnnotation.path(), sourceAnnotation.model(), ",").toArray();
    }


}
