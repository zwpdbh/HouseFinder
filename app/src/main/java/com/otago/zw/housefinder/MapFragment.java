package com.otago.zw.housefinder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {
    public MapView mMapView;
    private GoogleMap mGoogleMap;



    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mGoogleMap = mMapView.getMap();
        double latitude = 17.385044;
        double longitude = 78.486671;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        mGoogleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(17.385044, 78.486671)).zoom(12).build();
        mGoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));


        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    //
//    protected void onStart() {
//        mGoogleApiClient.connect();
//        super.onStart();
//    }
//
//    protected void onStop() {
//        mGoogleApiClient.disconnect();
//        super.onStop();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_setting:
//                Intent settingIntent = new Intent(this, SettingActivity.class);
//                startActivity(settingIntent);
//                return true;
//            case R.id.add_house_info:
//                Intent addHouseInfoIntent = new Intent(this, AddHouseActivity.class);
//                startActivity(addHouseInfoIntent);
//                return true;
//            case R.id.Otago_about:
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("http://www.otago.ac.nz"));
//                startActivity(i);
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        System.out.println("Connected");
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            mMap.setMyLocationEnabled(true);
//            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//            System.out.println("sett my location:\n" + mCurrentLocation.getLatitude() + "\n" + mCurrentLocation.getLongitude());
//        } else {
//            // Show rationale and request permission.
//            System.out.println("Location Permission Failed");
//            System.out.println("Give me permission: Location");
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//        System.out.println("onConnectionSuspended:" + i);
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        System.out.println(connectionResult.toString());
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//
//        // add listener
//        mMap.setOnMapClickListener(this);
//        mMap.setOnMapLongClickListener(this);
//        mMap.setOnMarkerClickListener(this);
//        mMap.setOnInfoWindowClickListener(this);
//
//        // Add a marker in Sydney and move the camera
//
//
//        if (mCurrentLocation != null) {
//            LatLng position = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
//        } else {
//            LatLng sydney = new LatLng(-34, 151);
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")).setSnippet("Test");
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        }
//
//    }
//
//    @Override
//    public void onMapClick(LatLng point) {
//        System.out.println(point.latitude + " : " + point.longitude);
//    }
//
//    @Override
//    public void onMapLongClick(LatLng point) {
//        LatLng newPosition = new LatLng(point.latitude, point.longitude);
//        mMap.addMarker(new MarkerOptions().position(newPosition).title("Added new position").draggable(true));
//    }
//
//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        System.out.println(marker.getTitle());
//        return false;
//    }
//
//    @Override
//    public void onInfoWindowClick(Marker marker) {
//        System.out.println(marker.getTitle());
//        Intent intent = new Intent(this, HouseDetailActivity.class);
//        intent.putExtra(HouseDetailActivity.HOUSE_ID, marker.getTitle());
//        startActivity(intent);
//    }
}
