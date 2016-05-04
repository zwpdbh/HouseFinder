package com.otago.zw.housefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.View;

public class SavedHouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_house);
    }

//    @Override
//    public void itemClicked(long id) {
//        View fragmentContainer = findViewById(R.id.fragment_container);
//
//        if (fragmentContainer!=null) {
//            DetailFragment details = new DetailFragment();
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//
//            details.setHouseId(id);
//            ft.replace(R.id.fragment_container, details);
//            ft.addToBackStack(null);
//            ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.commit();
//        } else {
//            Intent intent = new Intent(this, HouseDetailActivity.class);
//            intent.putExtra(HouseDetailActivity.HOUSE_ID, (int) id);
//            startActivity(intent);
//        }
//        System.out.println("clicked");
//    }
}
