package com.otago.zw.housefinder;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaveHouseListFragment extends ListFragment {

    interface SaveHouseListListener {
        void itemClicked(long id);
    }

    private SaveHouseListListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (SaveHouseListListener) context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (listener!=null) {
            listener.itemClicked(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_house_list, container, false);
    }



}
