package com.ander.quicktrip;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anders on 10/13/2016.
 */
public class ReturnDateFragment extends Fragment{

    private static final String TAG = "OutboundFragment";

    private InDateChange mListener;
    DatePicker mDatePicker;
    public String mOutboundDate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InDateChange) {
            mListener = (InDateChange) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.return_date_fragment, container, false);

        mDatePicker = (DatePicker) root.findViewById(R.id.inDatePicker);

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
                String normalDay = adjMonth + "-" + adjDay + "-" + year;
                addItemsToActivity(mOutboundDate, textViewDate, normalDay);

                Log.i(TAG, "onClick: " + mDatePicker.getDayOfMonth());
                getActivity().onBackPressed();
            }
        });
    }

    public void addItemsToActivity(String date, String textview, String viewableDay){
        mListener.onInDateChange(date,textview, viewableDay);
    }

    public interface InDateChange {
        void onInDateChange(String date, String textview, String normalDay);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
