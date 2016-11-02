package com.ander.quicktrip;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anders on 10/13/2016.
 */
public class CabinClassFragment extends Fragment{

    CabinClass mCabinClass;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCabinClass = (CabinClass) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.cabin_fragment,container,false);

        final ArrayList<String> cabinClassArray = new ArrayList<>();
        cabinClassArray.add("Economy");
        cabinClassArray.add("PremiumEconomy");
        cabinClassArray.add("Business");
        cabinClassArray.add("First");

        ListView listView = (ListView) root.findViewById(R.id.cabin_listview);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,cabinClassArray);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cabinClassMethod(cabinClassArray.get(i));
            }
        });

        ImageView fab = (ImageView) root.findViewById(R.id.back_press);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return root;
    }

    public void cabinClassMethod(String data){
        mCabinClass.cabinClassData(data);
    }

    public interface CabinClass{
        public void cabinClassData(String data);
    }
}
