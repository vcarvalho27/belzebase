package com.vcarvalho27.belzebaseexample.model;

import java.util.Date;
import java.util.List;

import com.vcarvalho27.belzebase.IModel;
import com.vcarvalho27.belzebase.annotation.*;

/**
 * Created by VMC on 26/01/2017.
 */

@Table("MASTER")
public class Master implements IModel {

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
