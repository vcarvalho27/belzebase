package com.vcarvalho27.belzebase.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by VMC on 25/01/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value();
}
