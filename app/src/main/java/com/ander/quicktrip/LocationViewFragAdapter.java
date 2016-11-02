package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import java.util.ArrayList;

public class LocationViewFragAdapter extends RecyclerView.Adapter<LocationViewHolder> {

    int mPosition;
    OriginAirport mInterface;
    AlphaAnimation mAlphaClickAnim;

    ArrayList<AirportObject> mList;
    private static final String TAG = "LocationViewFragAdapter";
    Context mContext;

    public LocationViewFragAdapter(ArrayList<AirportObject> array, Context context) {
        mList = array;
        mContext = context;
        mAlphaClickAnim = new AlphaAnimation(0.8f, 1f);
        mAlphaClickAnim.setDuration(100);
    }


    public LocationViewFragAdapter(ArrayList<AirportObject> array, Context context, OriginAirport givenInterface) {
        mList = array;
        mContext = context;
        mInterface = givenInterface;
        mAlphaClickAnim = new AlphaAnimation(0.8f, 1f);
        mAlphaClickAnim.setDuration(100);
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_view_items, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LocationViewHolder holder, int position) {
        mPosition = position;
        if (mList.size() > 0) {
            Log.i(TAG, "onBindViewHolder: mList.size " + mList.size());
            holder.mCode.setText(mList.get(position).getmCode());
            holder.mName.setText(mList.get(position).getmName());
            holder.mCity.setText(mList.get(position).getmCity());
            holder.mCountry.setText(mList.get(position).getmCountry());

            holder.mContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAnimation(mAlphaClickAnim);
                    String code = holder.mCode.getText().toString();
                    String country = holder.mCountry.getText().toString();
                    String city = holder.mCity.getText().toString();
                    String name = holder.mName.getText().toString();

                    MasterAirportList.getINSTANCE().getmInitialValuesObj().setOutAirportName(name);
                    MasterAirportList.getINSTANCE().getmInitialValuesObj().setOutAirportCode(code);
                    MasterAirportList.getINSTANCE().getmInitialValuesObj().setCountry(country);
                    MasterAirportList.getINSTANCE().getmInitialValuesObj().setCity(city);

                    if (mInterface != null) {
                        applyClickedItem(code, name, city, country);
                    }

                    Log.i(TAG, "onClick working: " + code + name + city + country);

                    Intent intent = new Intent(mContext, MainEventPageActivity.class);
                    mContext.startActivity(intent);

                }
            });
        }
    }

    public void applyClickedItem(String code, String name, String city, String country) {
        mInterface.origionAirport(code, name, city, country);
    }

    public interface OriginAirport {
        void origionAirport(String code, String name, String city, String country);
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    public void updateCurrentList(ArrayList<AirportObject> arrayList) {
        mList = arrayList;
        notifyDataSetChanged();
    }

}
