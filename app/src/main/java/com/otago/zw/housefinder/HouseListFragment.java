package com.otago.zw.housefinder;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HouseListFragment extends ListFragment {
    static interface HouseListFragmentListener {
        void itemClicked(long id);
    }

    private HouseListFragmentListener listener;

    public HouseListFragment() {
        // Required empty public constructor
    }

    public HouseInfo mHouseInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] names = new String[mHouseInfo.getHouses().size()];
        for (int i=0; i<names.length; i++) {
            names[i] = mHouseInfo.getHouses().get(i).getDescription();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfo = HouseInfo.get(context);
        this.listener = (HouseListFragmentListener) context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (listener != null) {
            listener.itemClicked(id);
        }
    }

}
