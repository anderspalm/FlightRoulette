package com.ander.quicktrip;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ander on 9/30/2016.
 */
public class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView mCode, mName, mCity, mCountry;
    LinearLayout mContainer;

    public LocationViewHolder(View itemView) {
        super(itemView);

        itemView.setClickable(true);

        mContainer = (LinearLayout) itemView.findViewById(R.id.location_click_listener);
        mCode = (TextView) itemView.findViewById(R.id.location_code);
        mName = (TextView) itemView.findViewById(R.id.location_name);
        mCity = (TextView) itemView.findViewById(R.id.location_city);
        mCountry = (TextView) itemView.findViewById(R.id.location_country);

//        mContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        v.setSelected(true);

        //or new Handler().postDelayed(new Runnable...

        //your onClick action
    }
}
