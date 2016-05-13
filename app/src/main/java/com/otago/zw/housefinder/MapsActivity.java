package com.otago.zw.housefinder;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuItem;


public class MapsActivity extends AppCompatActivity implements HouseListFragment.HouseListFragmentListener {

    private String[] drawerItems;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        drawerItems = getResources().getStringArray(R.array.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                drawerItems
        );

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    private void setActionBarTitle(int position) {
        String title;
        if (position==0) {
            title = getResources().getString(R.string.app_name);
        } else {
            title = drawerItems[position];
        }
        try {
            getActionBar().setTitle(title);
        } catch (NullPointerException e) {
            System.err.println("Null for ActionBar");
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

        // Set the action bar title
        setActionBarTitle(position);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    /**For showing the detailed house info, if it is on tablet, then use fragment
     * Otherwise, use activity.
     * */
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


    /**ActionBar*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Otago_about:
                // can create intent, and start intent when click on the item
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
