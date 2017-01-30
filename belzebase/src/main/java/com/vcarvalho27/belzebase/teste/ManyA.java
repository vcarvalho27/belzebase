package com.vcarvalho27.belzebase.teste;

import java.util.Date;
import java.util.List;

import appcompras.jcom.com.br.database.IModel;
import appcompras.jcom.com.br.database.annotation.Column;
import appcompras.jcom.com.br.database.annotation.ForeignKey;
import appcompras.jcom.com.br.database.annotation.InversedForeignKey;
import appcompras.jcom.com.br.database.annotation.PrimaryKey;
import appcompras.jcom.com.br.database.annotation.Required;
import appcompras.jcom.com.br.database.annotation.StringLength;
import appcompras.jcom.com.br.database.annotation.Uniq;

/**
 * Created by VMC on 26/01/2017.
 */

@Column("MANY_A")
public class ManyA implements IModel {

    @PrimaryKey
    @Column("ID")
    private int id;

    @Column("OBS")
    @StringLength(200)
    private String name;


    @InversedForeignKey(foreignTableName = "MANY_TO_MANY", fieldName = "ID")
    public List<Detail> detailList;

}
