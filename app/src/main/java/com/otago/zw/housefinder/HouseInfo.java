package com.otago.zw.housefinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.otago.zw.housefinder.database.DatabaseHelper;
import com.otago.zw.housefinder.database.HouseCursorWrapper;
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
    }

    public List<House> getHouses() {
        List<House> houses = new ArrayList<>();
        HouseCursorWrapper cursorWrapper = (HouseCursorWrapper) queryHouses(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                houses.add(cursorWrapper.getHouse());
                cursorWrapper.moveToNext();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            cursorWrapper.close();
        }
        return houses;
    }

    public House getHouseById(UUID uuid) {
        HouseCursorWrapper cursor = (HouseCursorWrapper) queryHouses(
                HouseTable.Cols.UUID + " = ?",
                new String[] { uuid.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getHouse();
        } finally {
            cursor.close();
        }

    }

    public House getHouseByCoordinate(LatLng latLng) {
        double latitude = latLng.latitude;
        double longitude = latLng.longitude;

        String where = HouseTable.Cols.LATITUDE + " = ?" + " AND " + HouseTable.Cols.LONGITUDE + " = ?";
        String[] whereArgs = new String[] { Double.toString(latitude), Double.toString(longitude)};
        Cursor cursor = mDatabase.query(HouseTable.NAME, null, where, whereArgs, null, null, null);
        

        try {
            if (cursor.getCount() == 0) {
                return null;
            } else {
                cursor.moveToFirst();
                House house = new House();
                house.setDescription(cursor.getString(cursor.getColumnIndex(HouseTable.Cols.DESCRIPTION)));
                house.setAddress(cursor.getString(cursor.getColumnIndex(HouseTable.Cols.ADDRESS)));
                house.setLatitude(cursor.getDouble(cursor.getColumnIndex(HouseTable.Cols.LATITUDE)));
                house.setLongitude(cursor.getDouble(cursor.getColumnIndex(HouseTable.Cols.LONGITUDE)));
                house.setPrice(cursor.getInt(cursor.getColumnIndex(HouseTable.Cols.PRICE)));
                house.setPictureId(cursor.getInt(cursor.getColumnIndex(HouseTable.Cols.PICTURE_ID)));
                return house;
            }
        } finally {
            cursor.close();
        }
    }


    private static ContentValues getContentValues(House house) {
        ContentValues values = new ContentValues();
        values.put(HouseTable.Cols.UUID, house.getUUID().toString());
        values.put(HouseTable.Cols.ADDRESS, house.getAddress());
        values.put(HouseTable.Cols.DESCRIPTION, house.getDescription());
        values.put(HouseTable.Cols.LATITUDE, house.getLatitude());
        values.put(HouseTable.Cols.LONGITUDE, house.getLongitude());
        values.put(HouseTable.Cols.PRICE, house.getPrice());
        values.put(HouseTable.Cols.PICTURE_ID, house.getPictureId());

        return values;
    }


    private Cursor queryHouses(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                HouseTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new HouseCursorWrapper(cursor);
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

    public void updateHouseByCoordinate(House house) {
        String latitude = Double.toString(house.getLatitude());
        String longitude = Double.toString(house.getLongitude());

        ContentValues values = getContentValues(house);
        mDatabase.update(HouseTable.NAME, values, HouseTable.Cols.LATITUDE + " = ?" + " AND " + HouseTable.Cols.LONGITUDE + " = ?", new String[] { latitude, longitude });
    }

}
