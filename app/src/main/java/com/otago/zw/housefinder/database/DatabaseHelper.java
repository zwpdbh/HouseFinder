package com.otago.zw.housefinder.database;

/**
 * Created by zw on 5/13/16.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HouseFinder.db";
    private static final int DB_VERSION = 1;


    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE HOUSE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT, "
            + "DESCRIPTION TEXT, "
            + "LATITUDE REAL, "
            + "LONGITUDE REAL, "
            + "IMAGE_RESOURCE_ID INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertHouse(SQLiteDatabase db, String name, String description, int latitude, int longitude, int imageResourceID) {
        ContentValues houseValue = new ContentValues();
        houseValue.put("NAME", name);
        houseValue.put("DESCRIPTION", description);
        houseValue.put("LATITUDE", latitude);
        houseValue.put("LONGITUDE", longitude);
        houseValue.put("IMAGE_RESOURCE_ID", imageResourceID);
        db.insert("HOUSE", null, houseValue);
    }


}
