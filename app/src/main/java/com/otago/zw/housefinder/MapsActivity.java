package com.otago.zw.housefinder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener {

    private House[] mHouses = {new House("Test1", 100),
        new House("Test2", 200), new House("Test3", 300)};

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;

    private String[] drawrerItems = {"Setting", "Saved Houses"};
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                drawrerItems
        );

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        if (position == 0) {
            Intent settingIntent = new Intent(this, SettingActivity.class);
            startActivity(settingIntent);
        } else if (position == 1) {
            Intent intent = new Intent(this, SavedHouse.class);
            startActivity(intent);
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                return true;
            case R.id.add_house_info:
                Intent addHouseInfoIntent = new Intent(this, AddHouseActivity.class);
                startActivity(addHouseInfoIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        System.out.println("Connected");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            System.out.println("sett my location:\n" + mCurrentLocation.getLatitude() + "\n" + mCurrentLocation.getLongitude());
        } else {
            // Show rationale and request permission.
            System.out.println("Location Permission Failed");
            System.out.println("Give me permission: Location");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("onConnectionSuspended:" + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println(connectionResult.toString());
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // add listener
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        // Add a marker in Sydney and move the camera


        if (mCurrentLocation != null) {
            LatLng position = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        } else {
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")).setSnippet("Test");
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

    }

    @Override
    public void onMapClick(LatLng point) {
        System.out.println(point.latitude + " : " + point.longitude);
    }

    @Override
    public void onMapLongClick(LatLng point) {
        LatLng newPosition = new LatLng(point.latitude, point.longitude);
        mMap.addMarker(new MarkerOptions().position(newPosition).title("Added new position").draggable(true));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        System.out.println(marker.getTitle());
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        System.out.println(marker.getTitle());
        Intent intent = new Intent(this, HouseDetailActivity.class);
        intent.putExtra(HouseDetailActivity.HOUSEDETAILINFO, marker.getTitle());
        startActivity(intent);
    }
}
