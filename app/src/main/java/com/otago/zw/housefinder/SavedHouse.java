package com.otago.zw.housefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SavedHouse extends AppCompatActivity implements HouseListFragment.HouseListFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_house);
    }

    @Override
    public void itemClicked(long id) {
        System.out.println("clicked");
    }
}
