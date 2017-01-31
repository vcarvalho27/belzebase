package com.vcarvalho27.belzebase;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VMC on 03/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private List<String> scripts = new ArrayList<>();

    private static String DBVERSIONPACKAGENAME = ".dbversion.";
    private int dbVersion;
    private String packageName;

    public DatabaseHelper(Context context, String dbName, int dbVersion, String packageName) {
        super(context, dbName+".sqlite3", null, dbVersion);
        this.dbVersion = dbVersion;
        this.packageName = packageName;
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
            databaseHelper = new DatabaseHelper(context, bundle.getString("DB_NAME"),  bundle.getInt("DB_VERSION"), context.getPackageName());

        return databaseHelper.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, this.dbVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        for (int i = oldVersion; i < newVersion; i++){
            scripts.addAll(getScript(i+1));
        }

        try {
            for (String s : scripts) {
                db.execSQL(s);
            }
        }
        catch (SQLException e) {
            Log.e("BelzeBase.DatabaseHelper.onUpgrade(newVersion="+newVersion+")", e.getMessage());
        }




    }

    public List<String> getScript(int version){
        try {
            IDatabaseVersion obj = (IDatabaseVersion) Class.forName(packageName +DBVERSIONPACKAGENAME + "version_"+ String.format("%07d", version)).newInstance();
            return obj.getScriptList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}