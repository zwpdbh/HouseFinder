package com.otago.zw.housefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class AddHouseActivity extends AppCompatActivity {
    public static final String LATITUDE = "House_latitude";
    public static final String LONGITUDE = "House_longitude";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        TextView latitudeView = (TextView) findViewById(R.id.latitude);
        TextView longitudeView = (TextView) findViewById(R.id.longitude);

        Intent intent = getIntent();
        LatLng latLng = new LatLng(intent.getDoubleExtra(LATITUDE, 0), intent.getDoubleExtra(LONGITUDE, 0));
        System.out.println("Get latLng: " + latLng.latitude + "<>" +latLng.longitude);
        latitudeView.setText(latLng.latitude+"");
        longitudeView.setText(latLng.longitude+"");
    }
}
