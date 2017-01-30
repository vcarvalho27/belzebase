package com.vcarvalho27.belzebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMC on 03/11/2016.
 */
public abstract class DaoObject<T extends IModel> {

    private Class<T> type;
    private SQLiteDatabase db;

    protected DaoObject(Context ctx) {
        this.db = DatabaseHelper.getDatabase(ctx);
    }


    protected abstract String getTableName();

    protected abstract String getWhereClause();

    protected abstract String[] getWhereArgs(T obj);

    protected abstract ContentValues getContentValues(T obj);

    protected abstract T getFromCursor(Cursor cursor);

    public Boolean any() {
        return this.count() > 0;
    }

    public Boolean any(String whereClause, String[] whereArgs) {
        return this.count(whereClause, whereArgs) > 0;
    }

    public T getFirst() {
        Cursor cursor = db.rawQuery(" SELECT * FROM " + getTableName(), null);

        T obj = null;
        if(cursor!=null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                obj = getFromCursor(cursor);
            }
            cursor.close();
        }
        return obj;
    }

    public T getFirst(String whereClause, String[] whereArgs) {
        Cursor cursor = db.rawQuery(" SELECT * FROM " + getTableName() + " WHERE " + whereClause, whereArgs);

        T obj = null;
        if(cursor!=null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                obj = getFromCursor(cursor);
            }
            cursor.close();
        }
        return obj;
    }

    public T get(String[] key){
        Cursor cursor = db.rawQuery(" SELECT * FROM " + getTableName()+ " WHERE " + getWhereClause(), key);

        T obj = null;
        if(cursor!=null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                obj = getFromCursor(cursor);
            }
            cursor.close();
        }
        return obj;
    }

    public Boolean insert(T obj) {
        return db.insert(this.getTableName(), null, getContentValues(obj)) != -1;
    }

    public Boolean insert(List<T> listObj) {

        if ((listObj != null)&&(listObj.size() > 0)) {
            for (T obj : listObj) {
                this.insert(obj);
            }
            return true;
        }
        else
            return false;
    }

    public Boolean insertIfNotExists(List<T> listObj) {

        if ((listObj != null)&&(listObj.size() > 0)) {
            for (T obj : listObj) {
                this.insertIfNotExists(obj);
            }
            return true;
        }
        else
            return false;
    }

    public Boolean insertIfNotExists(T obj){
        return !exists(obj) ? insert(obj) : true;
    }

    public Boolean update(T obj) {

        db.update(getTableName(), getContentValues(obj), getWhereClause(), getWhereArgs(obj));
        return true;
    }

    public Boolean insertOrUpdateIfExists(T obj){
        if (exists(obj))
            return update(obj);
        else
            return insert(obj);
    }

    public Boolean insertOrUpdateIfExists(List<T> listObj) {

        if ((listObj != null)&&(listObj.size() > 0)) {
            for (T obj : listObj) {
                this.insertOrUpdateIfExists(obj);
            }
            return true;
        }
        else
            return false;
    }


    public Boolean exists(T obj){
        Cursor c = this.db.rawQuery("SELECT COUNT(*) AS QTD FROM "+getTableName() + " where "+getWhereClause(), getWhereArgs(obj));
        c.moveToFirst();
        Boolean retorno = c.getInt(c.getColumnIndex("QTD")) > 0;
        c.close();
        return retorno;
    }

    public Boolean delete(T obj) {
        db.delete(getTableName(),getWhereClause(),getWhereArgs(obj));
        return true;
    }

    public Boolean delete(List<T> listObj) {

        if ((listObj != null)&&(listObj.size() > 0)) {
            for (T obj : listObj) {
                this.delete(obj);
            }
            return true;
        }
        else
            return false;
    }


    public Boolean deleteAll(){
        db.delete(getTableName(), null, null);
        return true;
    }

    public Boolean deleteAll(String whereClause, String[] whereArgs){
        db.delete(getTableName(), whereClause, whereArgs);
        return true;
    }

    public int count(){
        Cursor c = this.db.rawQuery("SELECT COUNT(*) AS QTD FROM " + getTableName(), null);
        c.moveToFirst();
        int retorno = c.getInt(c.getColumnIndex("QTD"));
        c.close();
        return retorno;
    }

    public int count(String whereClause, String[] whereArgs){
        Cursor c = this.db.rawQuery("SELECT COUNT(*) AS QTD FROM "+getTableName()+ " WHERE " + whereClause, whereArgs);
        c.moveToFirst();
        int retorno = c.getInt(c.getColumnIndex("QTD"));
        c.close();
        return retorno;
    }

    public List<T> listAll() {
        Cursor cursor = db.rawQuery(" SELECT * FROM " + getTableName(), null);
        List<T> lista = new ArrayList<>();

        while(cursor.moveToNext()){
            lista.add(getFromCursor(cursor));
        }

        cursor.close();
        return lista;
    }

    public List<T> listAll(String whereClause, String[] whereArgs){
        Cursor cursor = db.rawQuery(" SELECT * FROM " + getTableName() + " WHERE " + whereClause, whereArgs);
        List<T> lista = new ArrayList<>();

        while(cursor.moveToNext()){
            lista.add(getFromCursor(cursor));
        }

        cursor.close();
        return lista;
    }


}
