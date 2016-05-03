package com.otago.zw.housefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SaveHouseInfoActivity extends AppCompatActivity implements SaveHouseListFragment.SaveHouseListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_house_info);
    }

    @Override
    public void itemClicked(long id) {

    }
}
