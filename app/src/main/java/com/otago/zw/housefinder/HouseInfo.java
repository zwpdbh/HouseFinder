package com.otago.zw.housefinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.otago.zw.housefinder.database.DatabaseHelper;
import com.otago.zw.housefinder.database.HouseDBSchema;
import com.otago.zw.housefinder.database.HouseDBSchema.HouseTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zw on 5/15/16.
 */
public class HouseInfo {
    private static HouseInfo sHouseInfo;

//    private List<House> mHouses;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static HouseInfo get(Context context) {
        if (sHouseInfo == null) {
            sHouseInfo = new HouseInfo(context);
        }

        return sHouseInfo;
    }

    private HouseInfo(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
//        mHouses = new ArrayList<>();
//
//        for (int i=5; i < 10; i++) {
//            House house = new House();
//            house.setAddress("House_" + i );
//            house.setPrice(i * i * 100);
//            house.setDescription("House: " + house.getUUID());
//            mHouses.add(house);
//        }
    }

    public List<House> getHouses() {
//        return mHouses;
        return new ArrayList<>();
    }

    public House getHouse(UUID uuid) {
        return null;
    }

    private static ContentValues getContentValues(House house) {
        ContentValues values = new ContentValues();
        values.put(HouseTable.Cols.UUID, house.getUUID().toString());
        values.put(HouseTable.Cols.ADDRESS, house.getAddress());
        values.put(HouseTable.Cols.DESCRIPTION, house.getDescription());
        values.put(HouseTable.Cols.LATITUDE, house.getLatitude());
        values.put(HouseTable.Cols.LATITUDE, house.getLongitude());
        values.put(HouseTable.Cols.PRICE, house.getPrice());
        values.put(HouseTable.Cols.PICTURE_ID, house.getPictureId());

        return values;
    }

    public void addHouse(House h) {
        ContentValues values = getContentValues(h);
        mDatabase.insert(HouseTable.NAME, null, values);
    }

    public void updateHouse(House house) {
        String uuidString = house.getUUID().toString();
        ContentValues values = getContentValues(house);
        mDatabase.update(HouseTable.NAME, values, HouseTable.Cols.UUID + " = ?", new String[] { uuidString });
    }
}
