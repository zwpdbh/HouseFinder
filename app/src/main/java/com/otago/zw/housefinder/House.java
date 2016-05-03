package com.otago.zw.housefinder;

/**
 * Created by zw on 5/2/16.
 */
public class House {
    private String address;
    private int price;

    public House(String address, int price) {
        this.address = address;
        this.price = price;
    }

    public static House[] mHouses = {new House("Test1", 100),
            new House("Test2", 200), new House("Test3", 300)};
}
