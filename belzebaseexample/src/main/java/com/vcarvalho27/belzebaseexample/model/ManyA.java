package com.vcarvalho27.belzebaseexample.model;

import java.util.List;

import com.vcarvalho27.belzebase.IModel;
import com.vcarvalho27.belzebase.annotation.*;

/**
 * Created by VMC on 26/01/2017.
 */

@Table("MANY_A")
public class ManyA implements IModel {

    public ManyA(){

    }

    @PrimaryKey
    @Column("ID")
    private int id;

    @Column("OBS")
    @StringLength(200)
    private String name;


    @InversedForeignKey(foreignTableName = "MANY_TO_MANY", fieldName = "ID")
    public List<Detail> detailList;

}
