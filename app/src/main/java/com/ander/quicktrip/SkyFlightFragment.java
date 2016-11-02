package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anders on 10/22/2016.
 */
public class SkyFlightFragment extends Fragment {

    ArrayList<BookingObj> arrayList;
    private static final String TAG = "SkyFlightFragment";
    TextView mDestinationCode, mPrice, mOutDate, mInDate;
    TextView mDestinedCity, mOriginAirportName, mOriginCity, mDestinedAirportName;
    TextView mOriginCountry, mDestinedCountry;
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.sky_flight_fragment,container,false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.booking_items_recycler_view);

        mContext = getContext();

        arrayList = new ArrayList<>();

        Bundle bundle = getArguments();
        int position = bundle.getInt("position");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
                arrayList = MasterAirportList.getINSTANCE().getSkyFlightObject().get(position).getAllBookingObjs();
        Log.i(TAG, "onCreateView: array list " + arrayList.size());
        
        BookingObjRVAdapter arrayAdapter = new BookingObjRVAdapter(getContext(),arrayList);
        recyclerView.setAdapter(arrayAdapter);


        mDestinedCity = (TextView) root.findViewById(R.id.destination_city);
        mOriginAirportName = (TextView) root.findViewById(R.id.origin_airport_name);
        mOriginCity = (TextView) root.findViewById(R.id.origin_city);
        mDestinedAirportName = (TextView) root.findViewById(R.id.destined_airport_name);
        mOriginCountry = (TextView) root.findViewById(R.id.origin_country);
        mDestinedCountry = (TextView) root.findViewById(R.id.destined_country);

        mDestinedAirportName.setText(arrayList.get(0).getdAirName());
        mDestinedCity.setText(arrayList.get(0).getmDestinedCity());
//        BDVH.mInDate.setText(mList.get(position).getmInboundDate());
        mDestinedCountry.setText(arrayList.get(0).getmDcountry());

        mOriginAirportName.setText(arrayList.get(0).getoAirName());
//        BDVH.mOutDate.setText(mList.get(position).getmOutboundDate());
        mOriginCity.setText(arrayList.get(0).getmOriginCity());
        mOriginCountry.setText(arrayList.get(0).getmOCountry());

        ImageView imageView1 = (ImageView) root.findViewById(R.id.back_press);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity().onBackPressed();
                Intent intent = new Intent(mContext,AgentsObjRVActivity.class);
                mContext.startActivity(intent);
            }
        });

        return root;
    }
}
