package com.ander.quicktrip;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ander on 9/2/2016.
 */
public class BookingObjViewHolder extends RecyclerView.ViewHolder {


    TextView mDestinationCode, mPrice, mOutDate, mInDate;
    TextView mDestinedCity, mOriginAirportName, mOriginCity, mDestinedAirportName;
    TextView mOriginCountry, mDestinedCountry;
    ImageView mAgentPic;
    CardView mWebView;

    public BookingObjViewHolder(View itemView) {
        super(itemView);

//        mDestinedCity = (TextView) itemView.findViewById(R.id.destination_city);
//        mOriginAirportName = (TextView) itemView.findViewById(R.id.origin_airport_name);
//        mOriginCity = (TextView) itemView.findViewById(R.id.origin_city);
//        mDestinedAirportName = (TextView) itemView.findViewById(R.id.destined_airport_name);
//        mOriginCountry = (TextView) itemView.findViewById(R.id.origin_country);
//        mDestinationCode = (TextView) itemView.findViewById(R.id.destination_code);
        mPrice = (TextView) itemView.findViewById(R.id.agent_price);
//        mDestinedCountry = (TextView) itemView.findViewById(R.id.destined_country);
        mAgentPic = (ImageView) itemView.findViewById(R.id.agent_pic);
        mWebView = (CardView) itemView.findViewById(R.id.purchase);

    }
}
