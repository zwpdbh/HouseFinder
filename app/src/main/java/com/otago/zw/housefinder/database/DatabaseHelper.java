package com.otago.zw.housefinder.database;

/**
 * Created by zw on 5/13/16.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.otago.zw.housefinder.House;
import com.otago.zw.housefinder.database.HouseDBSchema.HouseTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HouseFinder.db";
    private static final int DB_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + HouseTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                HouseTable.Cols.UUID + ", " +
                HouseTable.Cols.ADDRESS + ", " +
                HouseTable.Cols.DESCRIPTION + ", " +
                HouseTable.Cols.LATITUDE + ", " +
                HouseTable.Cols.LONGITUDE + ", " +
                HouseTable.Cols.PRICE + ", " +
                HouseTable.Cols.PICTURE_ID + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + HouseTable.NAME);
        onCreate(db);
    }


}
