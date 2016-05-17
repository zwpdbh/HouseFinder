package com.otago.zw.housefinder.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.otago.zw.housefinder.House;
import com.otago.zw.housefinder.database.HouseDBSchema.HouseTable;

import java.util.UUID;

/**
 * Created by zw on 5/15/16.
 */
public class HouseCursorWrapper extends CursorWrapper {
    public HouseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public House getHouse() {
        String uuidString = getString(getColumnIndex(HouseTable.Cols.UUID));
        String address = getString(getColumnIndex(HouseTable.Cols.ADDRESS));
        String description = getString(getColumnIndex(HouseTable.Cols.DESCRIPTION));
        double latitude = getDouble(getColumnIndex(HouseTable.Cols.LATITUDE));
        double longitude = getDouble(getColumnIndex(HouseTable.Cols.LONGITUDE));
        int price = getInt(getColumnIndex(HouseTable.Cols.PRICE));
        int pictureId = getInt(getColumnIndex(HouseTable.Cols.PICTURE_ID));

        House house = new House(UUID.fromString(uuidString));
        house.setDescription(description);
        house.setAddress(address);
        house.setLatitude(latitude);
        house.setLongitude(longitude);
        house.setPrice(price);
        house.setPictureId(pictureId);

        return house;
    }
}
