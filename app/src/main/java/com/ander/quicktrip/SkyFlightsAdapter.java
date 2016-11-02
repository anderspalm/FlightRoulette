package com.ander.quicktrip;

import android.content.Context;
import android.os.Bundle;
import android.print.PrinterCapabilitiesInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by anders on 10/22/2016.
 */
public class SkyFlightsAdapter extends RecyclerView.Adapter<SkyFlightsAdapter.SkyFlightsViewHolder> {

    private static final String TAG = "SkyFlightsAdapter";

    Context mContext;
    ArrayList<SkyFlightObject> mList;
    FragmentManager mFragmentManager;
    FrameLayout mFrameLayout;
    String origin;
    String destination;

    public SkyFlightsAdapter(Context context, ArrayList<SkyFlightObject> array,
                             FragmentManager manager, FrameLayout frameLayout) {
        mContext = context;
        mList = array;
        mFragmentManager = manager;
        mFrameLayout = frameLayout;

        origin = MasterAirportList.getINSTANCE().getmMainPageObject().getmOrigin();
        destination = MasterAirportList.getINSTANCE().getmMainPageObject().getmDestination();
    }

    @Override
    public SkyFlightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sky_flights_rv, parent, false);
        return new SkyFlightsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SkyFlightsViewHolder holder, int position) {

        if(mList.size() > 0) {
            String firstTimeOut = String.valueOf(mList.get(position).getmDepartureOutbound()).substring(11, 16);
            String firstTimeIn = String.valueOf(mList.get(position).getmArrivalOutbound()).substring(11, 16);
            String secondTimeOut = String.valueOf(mList.get(position).getmDepartureInbound()).substring(11, 16);
            String secondTimeIn = String.valueOf(mList.get(position).getmArrivalInbound()).substring(11, 16);

            if (Integer.parseInt(firstTimeOut.substring(0, 2)) < 12) {
                Log.i(TAG, "onBindViewHolder: time f o " + firstTimeOut);
                String time = firstTimeOut;
                if (Integer.parseInt(time.substring(0, 2)) >= 10) {
                    time = time.substring(0, time.length()) + "am";
                } else {
                    time = time.substring(1, time.length()) + "am";
                }
                firstTimeOut = time;
            } else {
                if (Integer.parseInt(firstTimeOut.substring(0, 2).substring(0, 2)) == 12) {
                    Log.i(TAG, "onBindViewHolder: time f o " + firstTimeOut);
                    firstTimeOut = firstTimeOut + "pm";
                } else {
                    Log.i(TAG, "onBindViewHolder: time f o " + firstTimeOut);
                    firstTimeOut = Integer.parseInt(firstTimeOut.substring(0, 2)) - 12 + firstTimeOut.substring(2, 5) + "pm";
                }
            }
            if (Integer.parseInt(firstTimeIn.substring(0, 2)) < 12) {
                Log.i(TAG, "onBindViewHolder: time f i " + firstTimeIn);
                String time = firstTimeIn;
                if (Integer.parseInt(time.substring(0, 2)) >= 10) {
                    time = time.substring(0, time.length()) + "am";
                } else {
                    time = time.substring(1, time.length()) + "am";
                }
                firstTimeIn = time;
            } else {
                if (Integer.parseInt(firstTimeIn.substring(0, 2)) == 12) {
                    Log.i(TAG, "onBindViewHolder: time f i " + firstTimeIn);
                    firstTimeIn = firstTimeIn + "pm";
                } else {
                    Log.i(TAG, "onBindViewHolder: time f i " + firstTimeIn);
                    firstTimeIn = Integer.parseInt(firstTimeIn.substring(0, 2)) - 12 + firstTimeIn.substring(2, 5) + "pm";
                }
            }
            if (Integer.parseInt(secondTimeOut.substring(0, 2)) < 12) {
                Log.i(TAG, "onBindViewHolder: s o " + secondTimeOut);
                String time = secondTimeOut;
                if (Integer.parseInt(time.substring(0, 2)) >= 10) {
                    time = time.substring(0, time.length()) + "am";
                } else {
                    time = time.substring(1, time.length()) + "am";
                }
                secondTimeOut = time;
            } else {
                if (Integer.parseInt(secondTimeOut.substring(0, 2)) == 12) {
                    Log.i(TAG, "onBindViewHolder: s o " + secondTimeOut);
                    secondTimeOut = secondTimeOut + "pm";
                } else {
                    Log.i(TAG, "onBindViewHolder: s o " + secondTimeOut);
                    secondTimeOut = Integer.parseInt(secondTimeOut.substring(0, 2)) - 12 + secondTimeOut.substring(2, 5) + "pm";
                }
            }
            if (Integer.parseInt(secondTimeIn.substring(0, 2)) < 12) {
                Log.i(TAG, "onBindViewHolder: s i " + secondTimeIn);
                String time = secondTimeIn;
                if (Integer.parseInt(time.substring(0, 2)) >= 10) {
                    time = time.substring(0, time.length()) + "am";
                } else {
                    time = time.substring(1, time.length()) + "am";
                }
                secondTimeIn = time;
            } else {
                if (Integer.parseInt(secondTimeIn.substring(0, 2)) == 12) {
                    Log.i(TAG, "onBindViewHolder: s i " + secondTimeIn);
                    secondTimeIn = secondTimeIn + "pm";
                } else {
                    Log.i(TAG, "onBindViewHolder: s i " + secondTimeIn);
                    secondTimeIn = Integer.parseInt(secondTimeIn.substring(0, 2)) - 12 + secondTimeIn.substring(2, 5) + "pm";
                }
            }



            holder.mDeparture1.setText(firstTimeOut);
            holder.mDeparture2.setText(secondTimeOut);
            holder.mArrival1.setText(firstTimeIn);
            holder.mArrival2.setText(secondTimeIn);
            holder.mPrice.setText(mList.get(position).getmPrice());
            holder.mOrigin.setText(origin);
            holder.mOrigin2.setText(destination);
            holder.mDestination.setText(destination);
            holder.mDestination2.setText(origin);

            String inStops = String.valueOf(mList.get(position).getmStopsOutbound());
            String outStops = String.valueOf(mList.get(position).getmStopsInbound());

            if (inStops.equals("0")) {
                inStops = "Nonstop";
            }
            if (outStops.equals("0")) {
                outStops = "Nonstop";
            }

            holder.mStops1.setText(outStops);
            holder.mStops2.setText(inStops);


            int outHours = 0;
            int outMinutes = 0;
            int inHours = 0;
            int inMinutes = 0;
            int outDurationBefore = mList.get(position).getmDurationOutbound();
            int inDurationBefore = mList.get(position).getmDurationInbound();

            if (outDurationBefore % 60 != 0 && outDurationBefore <= 60) {
                outMinutes = outDurationBefore;
            } else if (outDurationBefore % 60 != 0 && outDurationBefore >= 60) {
                outHours = ((outDurationBefore - (outDurationBefore % 60)) / 60);
                outMinutes = outDurationBefore % 60;
            }

            if (inDurationBefore % 60 != 0 && inDurationBefore <= 60) {
                inMinutes = inDurationBefore;
            } else if (inDurationBefore % 60 != 0 && inDurationBefore >= 60) {
                inHours = ((inDurationBefore - (inDurationBefore % 60)) / 60);
                inMinutes = inDurationBefore % 60;
            }

            String outTime = outHours + "h  " + outMinutes + "m";
            String inTime = inHours + "h  " + inMinutes + "m";

            holder.mDuration1.setText(String.valueOf(outTime));
            holder.mDuration2.setText(String.valueOf(inTime));

            if (MasterAirportList.getINSTANCE().getSkyFlightObject().get(position) != null) {
                if (MasterAirportList.getINSTANCE().getSkyFlightObject().get(position).getAllBookingObjs().size() != 0) {
                    if (MasterAirportList.getINSTANCE().getSkyFlightObject().get(position).getAllBookingObjs().get(0) != null) {
                        String pic = MasterAirportList.getINSTANCE().getSkyFlightObject().get(position).getAllBookingObjs().get(0).getmAgentPic();
                        Picasso.with(mContext).load(pic).fit().into(holder.mAgent);
                    }
                }
            }
        }
    }

    public class SkyFlightsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mPrice,
                mDeparture1, mDeparture2, mArrival1, mArrival2,
                mDuration1, mDuration2,
                mStops1, mStops2,
                mOrigin, mOrigin2, mDestination, mDestination2;
        ImageView mAgent;

        public SkyFlightsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mStops1 = (TextView) itemView.findViewById(R.id.stops1);
            mStops2 = (TextView) itemView.findViewById(R.id.stops2);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            mDeparture1 = (TextView) itemView.findViewById(R.id.departure1);
            mDeparture2 = (TextView) itemView.findViewById(R.id.departure2);
            mArrival1 = (TextView) itemView.findViewById(R.id.arrival1);
            mArrival2 = (TextView) itemView.findViewById(R.id.arrival2);
            mDuration1 = (TextView) itemView.findViewById(R.id.duration1);
            mDuration2 = (TextView) itemView.findViewById(R.id.duration2);
            mOrigin = (TextView) itemView.findViewById(R.id.origin);
            mDestination = (TextView) itemView.findViewById(R.id.destination);
            mOrigin2 = (TextView) itemView.findViewById(R.id.origin2);
            mDestination2 = (TextView) itemView.findViewById(R.id.destination2);
            mAgent = (ImageView) itemView.findViewById(R.id.carrierImage);
        }

        @Override
        public void onClick(View view) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SkyFlightFragment skyFlightFragment = new SkyFlightFragment();
                    mFrameLayout.setVisibility(View.VISIBLE);

                    Log.i(TAG, "onClick: position: " + getAdapterPosition());

                    int position = getAdapterPosition();
                    Bundle bundle = new Bundle();
                    bundle.putInt("position",position);

                    skyFlightFragment.setArguments(bundle);
                    mFragmentManager.beginTransaction()
                            .add(R.id.flight_agents_fragment,skyFlightFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }
}
