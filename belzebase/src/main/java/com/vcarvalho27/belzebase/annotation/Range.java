package com.vcarvalho27.belzebase.annotation;

/**
 * Created by VMC on 26/01/2017.
 */

public @interface Range {
    double minValue()default Double.NEGATIVE_INFINITY;
    double maxValue()default Double.POSITIVE_INFINITY;
}
