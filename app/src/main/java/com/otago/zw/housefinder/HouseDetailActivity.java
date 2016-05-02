package com.otago.zw.housefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HouseDetailActivity extends AppCompatActivity {

    public static final String HOUSEDETAILINFO = "HOUSE_DETAIL_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        String houseTitle = getIntent().getStringExtra(HOUSEDETAILINFO);
        TextView titleView = (TextView) findViewById(R.id.house_title);
        titleView.setText(houseTitle);


    }
}
