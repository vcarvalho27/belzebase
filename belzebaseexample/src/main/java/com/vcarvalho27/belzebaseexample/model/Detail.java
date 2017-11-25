package com.vcarvalho27.belzebaseexample.model;

import com.vcarvalho27.belzebase.IModel;
import com.vcarvalho27.belzebase.annotation.*;

/**
 * Created by VMC on 26/01/2017.
 */

@Table("DETAIL")
public class Detail implements IModel {
    public Detail(){

    }

    @PrimaryKey
    @Column("ID")
    private int id;

    @Column("ID_MASTER")
    @ForeignKey(foreignTableName = "MASTER", foreignFieldName = "ID")
    private int idMaster;

    @ForeignKeyField("idMaster")
    private Master master;

    @Column("VALUE")
    @Range(minValue = 0, maxValue = 95.6)
    private double value;

    @Column("QTD")
    @Range(maxValue = 5)
    @DefaultValueInt(1)
    private int qtd;
}
