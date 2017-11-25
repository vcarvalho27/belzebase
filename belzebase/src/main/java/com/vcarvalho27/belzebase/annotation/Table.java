package com.vcarvalho27.belzebase.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by VMC on 25/01/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
