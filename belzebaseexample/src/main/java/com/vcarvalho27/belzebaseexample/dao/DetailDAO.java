package com.vcarvalho27.belzebaseexample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.vcarvalho27.belzebase.DaoObject;
import com.vcarvalho27.belzebaseexample.model.Detail;

/**
 * Created by vcarv on 30/01/2017.
 */

public class DetailDAO extends DaoObject<Detail> {

    public DetailDAO(Context ctx) {
        super(ctx);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String getWhereClause() {
        return null;
    }

    @Override
    protected String[] getWhereArgs(Detail obj) {
        return new String[0];
    }

    @Override
    protected ContentValues getContentValues(Detail obj) {
        return null;
    }

    @Override
    protected Detail getFromCursor(Cursor cursor) {
        return null;
    }
}
