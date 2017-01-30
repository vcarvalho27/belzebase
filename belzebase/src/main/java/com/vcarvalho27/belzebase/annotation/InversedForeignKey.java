package com.vcarvalho27.belzebase.annotation;

/**
 * Created by VMC on 26/01/2017.
 */

public @interface InversedForeignKey {
    String foreignTableName();

    /**
     * Local field name, referenced in the foreign table
    * */
    String fieldName();

}
