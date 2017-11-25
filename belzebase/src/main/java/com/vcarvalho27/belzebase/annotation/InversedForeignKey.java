package com.vcarvalho27.belzebase.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by VMC on 26/01/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface InversedForeignKey {
    String foreignTableName();

    /**
     * Local field name, referenced in the foreign table
    * */
    String fieldName();

}
