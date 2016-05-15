package com.otago.zw.housefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.otago.zw.housefinder.database.DatabaseHelper;

import org.w3c.dom.Text;

public class AddHouseActivity extends AppCompatActivity {
    public static final String LATITUDE = "House_latitude";
    public static final String LONGITUDE = "House_longitude";
    public static final String ADDRESS = "House_address";
    public static final String DESCRIPTION = "House_description";
    public static final String PRICE = "House_Price";

    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        TextView latitudeView = (TextView) findViewById(R.id.latitude);
        TextView longitudeView = (TextView) findViewById(R.id.longitude);

        final Intent intent = getIntent();
        LatLng latLng = new LatLng(intent.getDoubleExtra(LATITUDE, 0), intent.getDoubleExtra(LONGITUDE, 0));
        System.out.println("Get latLng: " + latLng.latitude + "<>" +latLng.longitude);
        latitudeView.setText(latLng.latitude+"");
        longitudeView.setText(latLng.longitude+"");


        mButton = (Button) findViewById(R.id.save_house_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HouseInfo houseInfo = HouseInfo.get(v.getContext());
                House house = new House();

                TextView latitudeView = (TextView)findViewById(R.id.latitude);
                double latitude = Double.parseDouble(latitudeView.getText().toString());

                TextView longitudeView = (TextView)findViewById(R.id.longitude);
                double longitude = Double.parseDouble(longitudeView.getText().toString());

                TextView priceView = (TextView) findViewById(R.id.house_price);
                int price = Integer.parseInt(priceView.getText().toString());

                TextView descriptionView = (TextView) findViewById(R.id.house_description);
                String description = descriptionView.getText().toString();

                TextView addressView = (TextView) findViewById(R.id.house_address);
                String address = addressView.getText().toString();


                System.out.println("Saved Position is: " + latitude + "<>" + longitude);
                house.setDescription(description);
                house.setPrice(price);
                house.setAddress(address);
                house.setLatitude(latitude);
                house.setLongitude(longitude);

                houseInfo.addHouse(house);
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
