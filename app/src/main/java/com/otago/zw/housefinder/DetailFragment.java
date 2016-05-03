package com.otago.zw.housefinder;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private long houseId;

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        View rootView = getView();
//        if (rootView != null) {
//            House house = House.mHouses[(int) houseId];
//
//            TextView addressView = (TextView) rootView.findViewById(R.id.house_title);
//            addressView.setText(house.getAddress());
//
//            TextView priceView = (TextView) rootView.findViewById(R.id.house_price);
//            priceView.setText(house.getPrice());
//        }
//    }
}
