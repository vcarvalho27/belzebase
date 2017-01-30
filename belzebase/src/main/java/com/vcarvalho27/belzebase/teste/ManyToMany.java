package com.vcarvalho27.belzebase.teste;

import appcompras.jcom.com.br.database.IModel;
import appcompras.jcom.com.br.database.annotation.Column;
import appcompras.jcom.com.br.database.annotation.ForeignKey;
import appcompras.jcom.com.br.database.annotation.ForeignKeyField;
import appcompras.jcom.com.br.database.annotation.PrimaryKey;

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
