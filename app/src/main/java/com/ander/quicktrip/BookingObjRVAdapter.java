package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ander on 9/2/2016.
 */
public class BookingObjRVAdapter extends RecyclerView.Adapter<BookingObjViewHolder> {

    private static final String TAG = "BookingObjRVAdapter";
    Context mContext;
    ArrayList<BookingObj> mList;
    BookingObjViewHolder BDVH;
    int mPosition;

    public BookingObjRVAdapter(Context context, ArrayList<BookingObj> arrayList){
        mContext = context;
        mList = arrayList;
    }

    @Override
    public BookingObjViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.booking_rec_view_items,parent,false);
        BDVH = new BookingObjViewHolder(view);
        return BDVH;
    }

    @Override
    public void onBindViewHolder(BookingObjViewHolder holder, final int position) {

        mPosition = position;
        BDVH.mWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: url " + mList.get(mPosition).getmDeepLinkUrl());
                Intent intent = new Intent(mContext, AgentWebView.class);
                intent.putExtra("url", mList.get(mPosition).getmDeepLinkUrl());
                mContext.startActivity(intent);
            }
        });

        Log.i(TAG, "onBindViewHolder: agent pic " + mList.get(mPosition).getmAgentPic());
        // agent photo
        Picasso.with(mContext).load(mList.get(mPosition).getmAgentPic()).fit().into(BDVH.mAgentPic);

        String price2 = mList.get(position).getmPrice();
        for (int i = 0; i < price2.length(); i++) {
            if (price2.substring(i, i + 1).equals(".")) {
                price2 = price2.substring(0, i + 2) + 0;
            }
        }
        BDVH.mPrice.setText(price2);

//        BDVH.mDestinedAirportName.setText(mList.get(mPosition).getdAirName());
//        BDVH.mDestinedCity.setText(mList.get(mPosition).getmDestinedCity());
////        BDVH.mInDate.setText(mList.get(position).getmInboundDate());
//        BDVH.mDestinedCountry.setText(mList.get(mPosition).getmDcountry());
//
//        BDVH.mOriginAirportName.setText(mList.get(mPosition).getoAirName());
////        BDVH.mOutDate.setText(mList.get(mPosition).getmOutboundDate());
//        BDVH.mOriginCity.setText(mList.get(mPosition).getmOriginCity());
//        BDVH.mOriginCountry.setText(mList.get(mPosition).getmOCountry());

    }

    @Override
    public int getItemCount() {
        if (mList == null || mList.size() == 0) {
            return 0;
        }
        else{ return mList.size();}
    }
}
