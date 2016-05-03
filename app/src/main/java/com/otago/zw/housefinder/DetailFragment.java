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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            houseId = savedInstanceState.getLong("houseId");
        }

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {
            TextView addressView = (TextView) view.findViewById(R.id.house_address);
            House house = House.mHouses[(int) houseId];
            addressView.setText(house.getAddress());

            TextView priceView = (TextView) view.findViewById(R.id.house_price);
            priceView.setText(""+house.getPrice());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("houseId", this.houseId);
    }
}
