package com.vcarvalho27.belzebase;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.vcarvalho27.belzebase.annotation.Column;
import com.vcarvalho27.belzebase.annotation.PrimaryKey;
import com.vcarvalho27.belzebase.annotation.Table;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;


/**
 * Created by VMC on 03/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    //private List<String> scripts = new ArrayList<>();

    private static String DBVERSIONPACKAGENAME = ".dbversion.";
    private int dbVersion;
    private String packageName;
    private String modelPackage;
    private Context context;

    public DatabaseHelper(Context context, String dbName, int dbVersion, String packageName, String modelPackage) {
        super(context, dbName+".sqlite3", null, dbVersion);
        this.context = context;
        this.dbVersion = dbVersion;
        this.packageName = packageName;
        this.modelPackage = modelPackage;
    }

    private static DatabaseHelper databaseHelper;

    public static SQLiteDatabase getDatabase(Context context){

        ApplicationInfo app = null;
        try {
            app = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Bundle bundle = app.metaData;

        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(context, bundle.getString("DB_NAME"),  bundle.getInt("DB_VERSION"),  context.getPackageName(), bundle.getString("MODEL_PACKAGE"));

        return databaseHelper.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, this.dbVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            List<String> classes = getClassesOfPackage();
            Class<?> clazz = null;
            for (String modelClass : classes) {
                try {
                    clazz = Class.forName(modelClass);
                    if (clazz != null && !clazz.isEnum() && !clazz.isInterface() && !clazz.isAnnotation() && !clazz.isPrimitive()) {
                        Constructor<?> ctor = clazz.getConstructor();
                        if (ctor != null) {
                            Object classObj = ctor.newInstance();
                            if (classObj instanceof IModel) {
                                IModel object = (IModel) classObj;
                                Table tableAnnotation = object.getClass().getAnnotation(Table.class);
                                if (tableAnnotation != null && !tableAnnotation.value().isEmpty()) {
                                    String tableName = tableAnnotation.value();
                                    String sqlCreate = "create table " + tableName + "(";

                                    int fieldCount = 0;
                                    for (Field field : object.getClass().getDeclaredFields()) {
                                        Column columnAnnot = field.getAnnotation(Column.class);
                                        PrimaryKey primaryAnnot = field.getAnnotation(PrimaryKey.class);
                                        if (columnAnnot != null) {
                                            String fieldName = columnAnnot.value();
                                            String fieldType = getSQLiteType(field.getType());
                                            sqlCreate += primaryAnnot == null ? fieldName + " " + fieldType + ", " : fieldName + " " + fieldType + " primary key autoincrement, ";
                                            fieldCount++;
                                        }
                                    }
                                    if (fieldCount > 0) {
                                        sqlCreate = sqlCreate.substring(0, sqlCreate.length() - 2) + ")";
                                        dropTable(db, tableName);
                                        createTable(db, sqlCreate);
                                    }
                                }
                            }
                        }
                    }
                }
                catch (NoSuchMethodException nsme){
                    if ("<init> []".equals(nsme.getMessage()) && clazz.getSimpleName() != null) {
                        Log.e("Belzebase", "Its necessary to implement a default constructor with no parameters");
                        Log.e("Belzebase", "Object: " +clazz.getSimpleName());
                    }
                    nsme.printStackTrace();
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getSQLiteType(Class<?> type){
        switch (type.getName()){
            case "Integer":
            case "int":
                return "integer";
            case "String":
                return "text";
            case "DateTime":
                return "numeric";
            case "double":
            case "float":
                return "numeric";
            case "boolean":
            case "Boolean":
                return "boolean";
        }

        return "TEXT";
    }

    private void createTable(SQLiteDatabase db,String sql){
        try {
            db.execSQL(sql);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void dropTable(SQLiteDatabase db,String tableName){
        try {
            db.execSQL("drop table if exists " + tableName);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<String> getScript(int version){
        /*try {
            IDatabaseVersion obj = (IDatabaseVersion) Class.forName(packageName +DBVERSIONPACKAGENAME + "version_"+ String.format("%07d", version)).newInstance();
            return obj.getScriptList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
        return null;
    }

    private List<String> getClassesOfPackage() {
        ArrayList<String> classes = new ArrayList<>();
        try {
            String packageCodePath = context.getPackageCodePath();
            DexFile df = new DexFile(packageCodePath);
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
                String className = iter.nextElement();
                if (className.contains(packageName+"."+modelPackage)) {
                    //classes.add(className.substring(className.lastIndexOf(".") + 1, className.length()));
                    classes.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }


}