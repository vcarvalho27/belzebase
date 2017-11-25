package com.vcarvalho27.belzebase.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

/**
 * Created by VMC on 26/01/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValueString {
    String value();
}
