package com.otago.zw.housefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HouseDetailActivity extends AppCompatActivity {

    public static final String HOUSE_ID = "House_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.house_detail_frag);
        int houseId = (int)getIntent().getExtras().getInt(HOUSE_ID);
        detailFragment.setHouseId(houseId);

//        String houseTitle = getIntent().getStringExtra(HOUSE_ID);
//        TextView titleView = (TextView) findViewById(R.id.house_title);
//        titleView.setText(houseTitle);



    }
}
