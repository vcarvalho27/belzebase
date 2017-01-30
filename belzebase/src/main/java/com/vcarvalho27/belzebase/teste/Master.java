package com.vcarvalho27.belzebase.teste;

import java.util.Date;
import java.util.List;

import appcompras.jcom.com.br.database.annotation.*;

/**
 * Created by VMC on 26/01/2017.
 */

@Table("MASTER")
public class Master {

    @PrimaryKey
    @Column("ID")
    private int id;

    @Uniq
    @Column("INCREMENT_UNIQ")
    private int incrementUniq;

    @Required
    @Column("DATA")
    private Date data;

    @Column("OBS")
    @StringLength(200)
    private String obs;


    @Column("TRUE_OR_FALSE")
    @DefaultValueBool(false)
    private boolean trueOrFalse;

    @InversedForeignKey(foreignTableName = "DETAIL", fieldName = "ID")
    public List<Detail> detailList;
}
