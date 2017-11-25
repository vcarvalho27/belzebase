package com.vcarvalho27.belzebaseexample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.icu.util.Calendar;

import com.vcarvalho27.belzebase.DaoObject;
import com.vcarvalho27.belzebaseexample.model.Master;

/**
 * Created by vcarv on 30/01/2017.
 */

public class MasterDAO extends DaoObject<Master>{

    public MasterDAO(Context ctx) {
        super(ctx);
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
        ContentValues contentValues = new ContentValues();

        contentValues.put("OBSERVACAO", obj.getObs());

        return contentValues;
    }

    @Override
    protected Master getFromCursor(Cursor cursor) {
        Master master = new Master();


        if (cursor != null && cursor.getCount() > 0){
            master.setObs(getString(cursor, "OBSERVACAO"));
        }


        return master;

    }
}
