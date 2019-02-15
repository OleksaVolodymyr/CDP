package com.epam.dataprovider.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SourcePath {
    String path();

    Class<?> model();

}
