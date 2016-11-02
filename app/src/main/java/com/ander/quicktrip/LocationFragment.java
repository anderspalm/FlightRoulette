package com.ander.quicktrip;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ander on 9/30/2016.
 */
public class LocationFragment extends Fragment {

    Context mContext;
    RecyclerView mRecView;
    EditText mEditText;
    LocationViewFragAdapter mAdapter;
    String mResponse;
    int mNum, before, now;
    private static final String TAG = "LocationFragment";
    AlphaAnimation mClickAnim;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.location_fragment, container, false);
        mRecView = (RecyclerView) root.findViewById(R.id.location_RView);
        mEditText = (EditText) root.findViewById(R.id.city_editText);
        mContext = getActivity();

        GridLayoutManager lm = new GridLayoutManager(mContext, 1);
        mRecView.setLayoutManager(lm);
        mAdapter = new LocationViewFragAdapter(MasterAirportList.getINSTANCE().getAirportList(), mContext);
        mRecView.setAdapter(mAdapter);

//        FrameLayout fl = (FrameLayout) root.findViewById(R.id.frame);

        mRecView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText.clearFocus();
                Log.i(TAG, "onClick: clicked");
                getActivity().getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String city = mEditText.getText().toString();
                if (city.length() == 1) {
                    before = 1;
                }
                if (city.length() > 1) {
                    now = city.length();
                }
                if (((city.length() % 2) == 0 || city.length() == 5) && city.length() >= 3 && now > before) {
                    Log.i(TAG, "afterTextChanged: " + city);
                    RequestQueue queue = Volley.newRequestQueue(mContext);
                    StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.GET,
                            "https://airport.api.aero/airport/match/" + city + "?user_key=430d32e198551e31a7f6b4c8e9d78208",
                            new com.android.volley.Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    mResponse = response;

                                    MasterAirportList.getINSTANCE().clearAirportList();

                                    LocationAsyncTask asyncTask = new LocationAsyncTask();
                                    asyncTask.execute();

                                }
                            }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(TAG, "onErrorResponse: " + error.toString());
                            error.printStackTrace();
                        }
                    });
                    queue.add(jsonObjectRequest);
                }
                before = city.length();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        return root;
    }

    public class LocationAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            Log.i(TAG, "onResponse: " + mResponse.substring(9, mResponse.length()));
            Log.i(TAG, "onResponse: " + mResponse.substring(9, mResponse.length() - 1));
            JSONObject jsonResponse = null;
            try {
                jsonResponse = new JSONObject(mResponse.substring(9, mResponse.length()));
                JSONArray array = jsonResponse.getJSONArray("airports");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String code = object.getString("code");
                    String name = object.getString("name");
                    String city = object.getString("city");
                    String country = object.getString("country");
                    MasterAirportList.getINSTANCE().addAirportToList(new AirportObject(code, name, city, country));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAdapter.updateCurrentList(MasterAirportList.getINSTANCE().getAirportList());
        }
    }
}
