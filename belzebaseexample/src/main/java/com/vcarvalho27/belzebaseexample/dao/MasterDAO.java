package com.vcarvalho27.belzebaseexample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.vcarvalho27.belzebase.DaoObject;
import com.vcarvalho27.belzebaseexample.model.Master;

/**
 * Created by vcarv on 30/01/2017.
 */

public class MasterDAO extends DaoObject<Master> {

    public MasterDAO(Context ctx) {
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
    protected String[] getWhereArgs(Master obj) {
        return new String[0];
    }

    @Override
    protected ContentValues getContentValues(Master obj) {
        return null;
    }

    @Override
    protected Master getFromCursor(Cursor cursor) {
        return null;
    }
}
