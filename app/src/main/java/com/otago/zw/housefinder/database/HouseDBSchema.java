package com.otago.zw.housefinder.database;

import android.provider.BaseColumns;

import java.util.UUID;

/**
 * Created by zw on 5/15/16.
 */
public class HouseDBSchema {
    public HouseDBSchema() {};

    public static final class HouseTable {
        public static final String NAME = "HOUSE";

        public static final class Cols implements BaseColumns{
            public static final String UUID = "UUID";
            public static final String ADDRESS = "ADDRESS";
            public static final String DESCRIPTION = "DESCRIPTION";
            public static final String LATITUDE = "LATITUDE";
            public static final String LONGITUDE = "LONGITUDE";
            public static final String PRICE = "PRICE";
            public static final String PICTURE_ID = "PICTURE";
        }
    }
}
