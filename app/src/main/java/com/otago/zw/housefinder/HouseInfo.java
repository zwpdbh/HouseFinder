package com.otago.zw.housefinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.otago.zw.housefinder.database.DatabaseHelper;

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
//        for (House each: mHouses) {
//            if (each.getUUID().equals(uuid)) {
//                return each;
//            }
//        }
        return null;
    }

    public void addHouse(House h) {
//        mHouses.add(h);
    }
}
