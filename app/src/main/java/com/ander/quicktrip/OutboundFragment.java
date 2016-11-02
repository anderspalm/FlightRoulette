package com.ander.quicktrip;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

public class OutboundFragment extends Fragment {

    private static final String TAG = "OutboundFragment";

    private OutDateChange mListener;
    DatePicker mDatePicker;
    public String mOutboundDate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OutDateChange) {
            mListener = (OutDateChange) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_outbound, container, false);

        mDatePicker = (DatePicker) root.findViewById(R.id.outDatePicker);

        onBackButtonPressed(root);

        return root;
    }

    public void onBackButtonPressed(View root) {
        ImageView backArrow = (ImageView) root.findViewById(R.id.back_press);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String adjDay;
                String adjMonth;

                int month = mDatePicker.getMonth();
                int tempMonth = month + 1;
                adjMonth = String.valueOf(tempMonth);
                if (tempMonth < 10) {
                    adjMonth = "0" + adjMonth;
                }

                int day = mDatePicker.getDayOfMonth();
                adjDay = String.valueOf(day);
                if (day < 10) {
                    adjDay = "0" + day;
                }
                int year = mDatePicker.getYear();
                mOutboundDate = year + "-" + adjMonth + "-" + adjDay;
                String textViewDate = adjDay + "-" + adjMonth + "-" + year;
                addItemsToActivity(mOutboundDate, textViewDate);

                Log.i(TAG, "onClick: " + mDatePicker.getDayOfMonth());
                getActivity().onBackPressed();
            }
        });
    }

    public void addItemsToActivity(String date, String textview){
        mListener.onOutDateChange(date,textview);
    }

    public interface OutDateChange {
        void onOutDateChange(String date, String textview);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
