package com.otago.zw.housefinder;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedHouseFragment extends Fragment implements HouseListFragment.HouseListFragmentListener {


    public SavedHouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_house, container, false);
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = getView().findViewById(R.id.fragment_container);

        if (fragmentContainer != null) {
            DetailFragment details = new DetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();

            details.setHouseId(id);
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent(getActivity(), HouseDetailActivity.class);
            intent.putExtra(HouseDetailActivity.HOUSE_ID, (int) id);
            startActivity(intent);
        }
    }
}
