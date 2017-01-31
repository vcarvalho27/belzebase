package com.vcarvalho27.belzebaseexample.model;

import com.vcarvalho27.belzebase.IModel;
import com.vcarvalho27.belzebase.annotation.Column;
import com.vcarvalho27.belzebase.annotation.DefaultValueBool;
import com.vcarvalho27.belzebase.annotation.DefaultValueDouble;
import com.vcarvalho27.belzebase.annotation.DefaultValueInt;
import com.vcarvalho27.belzebase.annotation.NotNull;
import com.vcarvalho27.belzebase.annotation.PrimaryKey;
import com.vcarvalho27.belzebase.annotation.Range;
import com.vcarvalho27.belzebase.annotation.StringLength;
import com.vcarvalho27.belzebase.annotation.Table;
import com.vcarvalho27.belzebase.annotation.Uniq;

/**
 * Created by vcarv on 30/01/2017.
 */

@Table("MASTER")
public class Master implements IModel {

    @PrimaryKey
    @Column("ID")
    private int id;

    @Column("NAME")
    @StringLength(40)
    @NotNull
    @Uniq
    private String name;

    @Column("INT_VALUE")
    @Range(minValue = -15, maxValue = 15)
    @DefaultValueInt(0)
    private int intValue;

    @Column("DOUBLE_VALUE")
    @Range(minValue = 0)
    @DefaultValueDouble(10)
    private double doubleValue;


    @Column("BOOL_VALUE")
    @DefaultValueBool(false)
    private boolean boolValue;

}
