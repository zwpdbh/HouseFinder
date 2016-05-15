package com.otago.zw.housefinder.database;

import android.provider.BaseColumns;

import java.util.UUID;

/**
 * Created by zw on 5/15/16.
 */
public class HouseDBSchema {
    public HouseDBSchema() {};

    public static final class HouseTable {
        public static final String NAME = "Houses";

        public static final class Cols implements BaseColumns{
            public static final String ADDRESS = "address";
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "longitude";
            public static final String PRICE = "price";
            public static final String DESCRIPTION = "description";
            public static final String PICTURE_ID = "pictureId";
        }
    }
}
