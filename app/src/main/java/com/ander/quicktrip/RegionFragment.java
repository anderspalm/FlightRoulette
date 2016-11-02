package com.ander.quicktrip;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by anders on 10/13/2016.
 */
public class RegionFragment extends Fragment {

    CustomRegionAdapter.RegionInterface mInterface;
    ObjectAnimator mAnimation;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mInterface = (CustomRegionAdapter.RegionInterface) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.region_fragment,container,false);

        ImageView fab = (ImageView) root.findViewById(R.id.back_press);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.region_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<RegionObj> arrayList = new ArrayList<>();
        arrayList.add(new RegionObj("Europe","https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Europe_topography_map_en.png/1259px-Europe_topography_map_en.png"));
        arrayList.add(new RegionObj("South East Asia","https://c1.staticflickr.com/1/614/22108218624_96e259d704_b.jpg"));
        arrayList.add(new RegionObj("North America","http://www.ezilon.com/maps/images/political-map-of-North-Amer.gif"));
        arrayList.add(new RegionObj("South America","https://c5.staticflickr.com/4/3454/3386095628_e4178db78d_b.jpg"));
        arrayList.add(new RegionObj("Anywhere","https://c2.staticflickr.com/6/5136/5580232038_5d6eb3d292_b.jpg"));

        CustomRegionAdapter adapter = new CustomRegionAdapter (arrayList,getContext(),mInterface);

        recyclerView.setAdapter(adapter);

        getActivity().setTitle("Region Filter");



        return root;
    }
}
