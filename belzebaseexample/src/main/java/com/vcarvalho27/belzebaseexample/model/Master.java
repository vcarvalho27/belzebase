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

    public Master(){

    }

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncrementUniq() {
        return incrementUniq;
    }

    public void setIncrementUniq(int incrementUniq) {
        this.incrementUniq = incrementUniq;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }
}
