package com.vcarvalho27.belzebase.annotation;

/**
 * Created by VMC on 26/01/2017.
 */

public @interface ForeignKey {
    String foreignTableName();
    String foreignFieldName();
}
