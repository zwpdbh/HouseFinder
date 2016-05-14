package com.otago.zw.housefinder;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnInfoWindowClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private MapView mapView;
    private GoogleMap googleMap;

    private GoogleApiClient mGoogleApiClient;
    private Context mContext;
    private Location mLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        /* Gets the MapView from the XML layout and creates it */
        mapView  = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // why it is null, and later is not?
        googleMap = mapView.getMap();

//        // latitude and longitude
//        double latitude = 17.385044;
//        double longitude = 78.486671;



        // Perform any camera updates here
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

        // why onResume() the googleMap is not null?
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    // helper method to add marker
    private void addMarker(double latitude, double longitude) {
        System.out.println(latitude + " : " + longitude);

        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        System.out.println("click marker");
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        System.out.println("click map");
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        addMarker(latLng.latitude, latLng.longitude);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        System.out.println(marker.getTitle());
        Intent intent = new Intent(mContext, AddHouseActivity.class);
        intent.putExtra(HouseDetailActivity.HOUSE_ID, marker.getTitle());
        startActivity(intent);
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.err.println("onConnectionSuspended: Error");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            googleMap.setMyLocationEnabled(true);
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLocation!=null) {
                System.out.println(mLocation.getLatitude());
                System.out.println(mLocation.getLongitude());
                addMarker(mLocation.getLatitude(), mLocation.getLongitude());
            } else {
                System.out.println("My Last Location Is NULL");
            }
        } catch (SecurityException e) {
            System.out.println("Permission denied by user!!!");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.err.println("onConnectionFailed: Error");
    }
}
