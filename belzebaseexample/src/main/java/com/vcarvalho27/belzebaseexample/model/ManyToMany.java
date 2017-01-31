package com.vcarvalho27.belzebaseexample.model;

import com.vcarvalho27.belzebase.IModel;
import com.vcarvalho27.belzebase.annotation.*;

/**
 * Created by VMC on 26/01/2017.
 */

@Column("MANY_TO_MANY")
public class ManyToMany implements IModel {

    @PrimaryKey
    @Column("ID")
    private int id;

    @Column("ID_MANY_A")
    @ForeignKey(foreignTableName = "MANY_A", foreignFieldName = "ID")
    private int idManyA;

    @ForeignKeyField("idManyA")
    private ManyA ManyA;


    @Column("ID_MANY_B")
    @ForeignKey(foreignTableName = "MANY_B", foreignFieldName = "ID")
    private int idManyB;

    @ForeignKeyField("idManyB")
    private ManyB ManyB;

}
