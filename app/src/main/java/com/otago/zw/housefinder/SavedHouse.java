package com.otago.zw.housefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentTransaction;

public class SavedHouse extends AppCompatActivity implements HouseListFragment.HouseListFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_house);

    }

    @Override
    public void itemClicked(long id) {
        DetailFragment details = new DetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        details.setHouseId(id);
        ft.replace(R.id.fragment_container, details);
        ft.addToBackStack(null);
        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        System.out.println("clicked");
    }
}
