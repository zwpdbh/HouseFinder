package com.otago.zw.housefinder;

import android.content.Intent;
import android.renderscript.Double2;
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
    public static final String ADDRESS = "HOUSE_ADDRESS";
    public static final String DESCRIPTION = "HOUSE_DESCRIPTION";
    public static final String PRICE = "HOUSE_PRICE";
    public static final String UPDATE = "UPDATE_OR_CREATE";

    private Button mButton;
    private boolean update;

    TextView latitudeView;
    TextView longitudeView;
    TextView priceView;
    TextView descriptionView;
    TextView addressView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        latitudeView = (TextView) findViewById(R.id.latitude);
        longitudeView = (TextView) findViewById(R.id.longitude);
        priceView = (TextView) findViewById(R.id.house_price);
        descriptionView = (TextView) findViewById(R.id.house_description);
        addressView = (TextView) findViewById(R.id.house_address);
        update = false;

        final Intent intent = getIntent();      // why declared as final ?

        if (savedInstanceState!=null) {
            LatLng latLng = new LatLng(intent.getDoubleExtra(LATITUDE, 0), intent.getDoubleExtra(LONGITUDE, 0));
            System.out.println("Get latLng: " + latLng.latitude + "<>" +latLng.longitude);
            latitudeView.setText(Double.toString(latLng.latitude));
            longitudeView.setText(Double.toString(latLng.longitude));

            if (intent.getStringExtra(UPDATE).equals("update")) {
                addressView.setText(intent.getStringExtra(ADDRESS));
                descriptionView.setText(intent.getStringExtra(DESCRIPTION));
                priceView.setText(intent.getIntExtra(PRICE, 0));
                update = false;
            }
        }

        mButton = (Button) findViewById(R.id.save_house_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HouseInfo houseInfo = HouseInfo.get(v.getContext());
                House house = new House();

                double latitude = Double.parseDouble(latitudeView.getText().toString());
                double longitude = Double.parseDouble(longitudeView.getText().toString());
                int price = Integer.parseInt(priceView.getText().toString());
                String description = descriptionView.getText().toString();
                String address = addressView.getText().toString();

                house.setDescription(description);
                house.setPrice(price);
                house.setAddress(address);
                house.setLatitude(latitude);
                house.setLongitude(longitude);

                if (update) {
                    houseInfo.addHouse(house);
                } else {
                    houseInfo.updateHouse(house);
                }

                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
