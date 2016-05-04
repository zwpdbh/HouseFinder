package com.otago.zw.housefinder;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MapsActivity extends AppCompatActivity implements HouseListFragment.HouseListFragmentListener {

    private String[] drawrerItems = {"Home", "Setting", "Saved Houses"};
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


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

        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new MapFragment();
                break;
            case 1:
                fragment = new SettingFragment();
                break;
            case 2:
                fragment = new SavedHouseFragment();
                break;
            default:
                fragment = new MapFragment();
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.home_screen_container,fragment);
        ft.addToBackStack(null);
        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);

        if (fragmentContainer != null) {
            DetailFragment details = new DetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();

            details.setHouseId(id);
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent(this, HouseDetailActivity.class);
            intent.putExtra(HouseDetailActivity.HOUSE_ID, (int) id);
            startActivity(intent);
        }
    }

}
