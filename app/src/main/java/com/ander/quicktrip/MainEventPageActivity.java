package com.ander.quicktrip;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class MainEventPageActivity extends AppCompatActivity implements PriceFragment.OnDataPass,
        CustomRegionAdapter.RegionInterface, CabinClassFragment.CabinClass,
        NavigationView.OnNavigationItemSelectedListener, OutboundFragment.OutDateChange,
        ReturnDateFragment.InDateChange, LocationViewFragAdapter.OriginAirport {

    private static final String TAG = "MainEventPageActivity";
    LinearLayout mWikiPopup;
    Context mContext;
    FrameLayout mFragmentView;
    HttpURLConnection conn, connection2;
    ObjectAnimator anim;
    RequestQueue queue;
    AlphaAnimation mClickAnim;
    Fragment pf, cf, rf, of, dout, din;
    Gson gsonStep2, gsonStep4, wikiGson, toDoGson;

    String outyear, outmonth, outday, outnewDay, year2, month2, day2, newDay2,
            idInboundObj, outBoundObj, arrivalObj, departureObj,
            woeId,
            mApiKey,
            oCountry, dCountry,
            origincity, destinedCity,
            originAirporstname, destinedAirportName,
            lastValue, currentValue;

    protected String mLocale, mAdults, mMarket, mCurrency, mRegion, mUrlBooking, mPreCabinClass, mCabinClass,
            mDestinationLocation, mDestinationCountry, mDestAirportCode, mInboundDate,
            mWikiExtract, innerKey,
            mOriginAirportCode, mOutboundDate, mOutboundLegId, mInboundLegId,
            mSessionID, mBookingSession;

    protected int mErrorCount, mNoFlightCount, mWikiParCount;
    protected double mMaxPrice, mAirportCount;

    TextView mRegionTextView, mOutCountry, mInCountry,
            mOutDate, mInDate, mOutAirport, mDestinationAirport, lowest_price, para1View, para2View;
    ImageView token, city_photo,
            mNavBOne, mNavBThree, mNavBFour, mNavBFive;
    DrawerLayout drawer;
    MasterAirportList mSingletonInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event_page2);

        mApiKey = ApiClass.skyscannerApi;

        // *********************************************************************

        // Below: Setting class variables

        // *********************************************************************

        // setting defaults
        mContext = MainEventPageActivity.this;
        mErrorCount = 0;
        mNoFlightCount = 0;
        mMaxPrice = 10000;
        conn = null;
        mMarket = "US";
        mCurrency = "USD";
        mLocale = "en-US";
        mAdults = "1";
        mPreCabinClass = "Economy";

        mDestAirportCode = "";

        mFragmentView = (FrameLayout) findViewById(R.id.wiki_info_fragment);
        mAirportCount = 0;

        mDestinationAirport = (TextView) findViewById(R.id.airport_return);
        mRegionTextView = (TextView) findViewById(R.id.region);
        mOutCountry = (TextView) findViewById(R.id.outCityCountry);

        city_photo = (ImageView) findViewById(R.id.region_photo);

        mInCountry = (TextView) findViewById(R.id.inCityCountry);
        mWikiPopup = (LinearLayout) findViewById(R.id.information_popup);
        mOutDate = (TextView) findViewById(R.id.out_date);
        mInDate = (TextView) findViewById(R.id.in_date);
        mOutAirport = (TextView) findViewById(R.id.airport);
        lowest_price = (TextView) findViewById(R.id.lowest_price);


        mSingletonInstance = MasterAirportList.getINSTANCE();

        mClickAnim = new AlphaAnimation(1f, 0.8f);
        mClickAnim.setDuration(100);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.filter);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ImageView filter = (ImageView) findViewById(R.id.filter_icon);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(mClickAnim);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentView = (FrameLayout) findViewById(R.id.wiki_info_fragment);
        mAirportCount = 0;


        // *********************************************************************

        // Below: Setting the Listeners

        // *********************************************************************

        navBarListeners();
        getWikiText();

        mWikiPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(mClickAnim);
                mFragmentView.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmenTransaction = fragmentManager.beginTransaction();

                InfoActivity landingPage = new InfoActivity();
                fragmenTransaction.add(R.id.wiki_info_fragment, landingPage);
                fragmenTransaction.commit();
            }
        });

        // *********************************************************************

        // Below: Setting Post OnCreate values

        // *********************************************************************

        if (mSingletonInstance.getmMainPageObject() != null) {
            mOutCountry.setText(mSingletonInstance.getmMainPageObject().getmOutCityCountry());
            mInCountry.setText(mSingletonInstance.getmMainPageObject().getmInCityCountry());

            Log.i(TAG, "onCreate: " + mSingletonInstance.getmMainPageObject().getmOutDate());
            if (mSingletonInstance.getmMainPageObject().getmOutDate().length() > 0) {
                String outDate = mSingletonInstance.getmMainPageObject().getmOutDate();
                String year = outDate.substring(0, 4);
                String month = outDate.substring(5, 6);
                String day = outDate.substring(8, 9);
                String newOutDay = month + "-" + day + "-" + year;
                mOutDate.setText(newOutDay);

                String inDate = mSingletonInstance.getmMainPageObject().getmInDate();
                String inYear = inDate.substring(0, 4);
                String inMonth = inDate.substring(5, 6);
                String inDay = inDate.substring(8, 9);
                String newInDay = inMonth + "-" + inDay + "-" + inYear;
                mInDate.setText(newInDay);
            }
            if (mSingletonInstance.getmMainPageObject().getmUrl() != null) {
                String url = mSingletonInstance.getmMainPageObject().getmUrl();
                if (!url.equals("") && url != null) {
                    Picasso.with(mContext)
                            .load(url)
                            .into(city_photo, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                }
                            });
                }
            }
            mOutAirport.setText(mSingletonInstance.getmMainPageObject().getmOutboundAirport());
            mDestinationAirport.setText(mSingletonInstance.getmMainPageObject().getmInboundAirport());
            mRegionTextView.setText(mSingletonInstance.getmMainPageObject().getmRegion1());
            Log.i(TAG, "onCreate: region " + mSingletonInstance.getmMainPageObject().getmRegion1());
            lowest_price.setText(mSingletonInstance.getmMainPageObject().getMlocationPrice());
            mDestinationLocation = mSingletonInstance.getmMainPageObject().getmDestinationLocation();
            mDestinationCountry = mSingletonInstance.getmMainPageObject().getmDestinationCountry();
        }

        // *********************************************************************

        // Below: Setting Pre OnCreate values

        // *********************************************************************


        InitialValuesObj initialValuesObj = mSingletonInstance.getmInitialValuesObj();
        if (mSingletonInstance.getmInitialValuesObj() != null) {
            String temp = initialValuesObj.getOutAirportCode();
            mOriginAirportCode = temp + "-sky";
            mOutboundDate = initialValuesObj.getOutboundDate();
            mInboundDate = initialValuesObj.getInboundDate();
        }

        InitialValues();

        if (mRegion == null) {
            mRegion = "";
        }


        // *********************************************************************

        // Below: Setting Token

        // *********************************************************************

        // randomize button
        token = (ImageView) findViewById(R.id.token);
//        anim = ObjectAnimator.ofFloat(token, "rotation", 360);
        anim = ObjectAnimator.ofFloat(token, "rotationY", 0.0f, 360f);
        token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(mClickAnim);
                if (anim.isRunning()) {
                    queue.cancelAll(this);
                    anim.end();
//                    Intent intent = new Intent(mContext, MainEventPageActivity.class);
//                    startActivity(intent);
                } else {
                    mNoFlightCount = 0;
                    callSetup();
                }
            }
        });
        anim.setDuration(300);
    }

    // *********************************************************************

    // Finished OnCreate

    // *********************************************************************

    public void InitialValues() {

        String country = mSingletonInstance.getmInitialValuesObj().getCountry();
        String airport = mSingletonInstance.getmInitialValuesObj().getOutAirportName();
        String code = mSingletonInstance.getmInitialValuesObj().getOutAirportCode();
        String city = mSingletonInstance.getmInitialValuesObj().getCity();

        mOutAirport.setText(airport + "(" + code + ")");
        mSingletonInstance.getmMainPageObject().setmOutCityCountry(city + "/" + country);
        mSingletonInstance.getmMainPageObject().setmOutboundAirport(airport + "(" + code + ")");

        if (mSingletonInstance.getmMainPageObject().getmOutDate().equals("")) {
            String outDate = mSingletonInstance.getmInitialValuesObj().getOutboundDate();
            String inDate = mSingletonInstance.getmInitialValuesObj().getInboundDate();

            mOutCountry.setText(city + "/" + country);

            if (outDate != null) {
                Log.i(TAG, "InitialValues: outDate " + outDate);
                String outyear = outDate.substring(0, 4);
                Log.i(TAG, "InitialValues: year " + outyear);
                String outmonth = outDate.substring(5, 7);
                Log.i(TAG, "InitialValues: month " + outmonth);
                String outday = outDate.substring(8, 10);
                Log.i(TAG, "InitialValues: day " + outday);
                String outnewDay = outmonth + "-" + outday + "-" + outyear;
                Log.i(TAG, "InitialValues: day " + outnewDay);
                mOutDate.setText(outnewDay);
            }
            if (inDate != null) {
                Log.i(TAG, "InitialValues: inDate " + inDate);
                String year = inDate.substring(0, 4);
                Log.i(TAG, "InitialValues: year " + year);
                String month = inDate.substring(5, 7);
                Log.i(TAG, "InitialValues: month " + month);
                String day = inDate.substring(8, 10);
                Log.i(TAG, "InitialValues: day " + day);
                String newDay = month + "-" + day + "-" + year;
                Log.i(TAG, "InitialValues: day " + newDay);
                mInDate.setText(newDay);
            }

            mSingletonInstance.getmMainPageObject().setmOutDate(outDate);
            mSingletonInstance.getmMainPageObject().setmInDate(inDate);
        } else {

            String out = mSingletonInstance.getmMainPageObject().getmOutDate();
            String in = mSingletonInstance.getmMainPageObject().getmInDate();
            if (out.length() > 0) {

                String outyear = out.substring(0, 4);
                String outmonth = out.substring(5, 7);
                String outday = out.substring(8, 10);
                String outnewDay = outmonth + "-" + outday + "-" + outyear;
                mOutDate.setText(outnewDay);

                String year2 = in.substring(0, 4);
                String month2 = in.substring(5, 7);
                String day2 = in.substring(8, 10);
                String newDay2 = month2 + "-" + day2 + "-" + year2;
                mInDate.setText(newDay2);
            }
        }

    }

    public void MainPageInfo() {
        if (mSingletonInstance.getmMainPageObject().getmOutDate() == null) {
            String outCityCountry = mSingletonInstance.getmMainPageObject().getmOutCityCountry();
            String outDate = mSingletonInstance.getmMainPageObject().getmOutDate();
            String inDate = mSingletonInstance.getmMainPageObject().getmInDate();
            String airport = mSingletonInstance.getmMainPageObject().getmOutboundAirport();
            String code = mSingletonInstance.getmMainPageObject().getmOrigin();

            String url = "";
            url = mSingletonInstance.getmMainPageObject().getmUrl();
            if (!url.equals("")) {
                Picasso.with(MainEventPageActivity.this)
                        .load(url)
                        .into(city_photo, new Callback() {
                            @Override
                            public void onSuccess() {
//                                Log.i(TAG, "onSuccess: success 1");
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }

            mOutCountry.setText(outCityCountry);

            if (outDate != null && outDate.length() > 0) {
                Log.i(TAG, "MainPageInfo: outDate " + outDate);
                String outyear = outDate.substring(0, 4);
                Log.i(TAG, "MainPageInfo: year " + outyear);
                String outmonth = outDate.substring(5, 7);
                Log.i(TAG, "MainPageInfo: month " + outmonth);
                String outday = outDate.substring(8, 10);
                Log.i(TAG, "MainPageInfo: day " + outday);
                String outnewDay = outmonth + "-" + outday + "-" + outyear;
                Log.i(TAG, "MainPageInfo: day " + outnewDay);
                mOutDate.setText(outnewDay);

                Log.i(TAG, "MainPageInfo: inDate " + inDate);
                String year = inDate.substring(0, 4);
                Log.i(TAG, "MainPageInfo: year " + year);
                String month = inDate.substring(5, 7);
                Log.i(TAG, "MainPageInfo: month " + month);
                String day = inDate.substring(8, 10);
                Log.i(TAG, "MainPageInfo: day " + day);
                String newDay = month + "-" + day + "-" + year;
                Log.i(TAG, "MainPageInfo: day " + newDay);
                mInDate.setText(newDay);
            }

            mOutAirport.setText(airport);

            if (mSingletonInstance.getmMainPageObject() != null) {
                mSingletonInstance.getmMainPageObject().setmOutCityCountry(outCityCountry);
                mSingletonInstance.getmMainPageObject().setmOutDate(outDate);
                mSingletonInstance.getmMainPageObject().setmInDate(inDate);
                mSingletonInstance.getmMainPageObject().setmOutboundAirport(airport);
            }
        }
    }

    // *********************************************************************

    // Below: call method

    // *********************************************************************


    public void callSetup() {
        if (mOriginAirportCode == null) {
            rePopulateOriginAndInOutDate();
        }
        if (!mOriginAirportCode.equals("") && !mInboundDate.equals("") && !mDestAirportCode.equals(null)) {
            destinationRandomizer();
            clearBookingObjects();
            // starting the session connection, which will be called multiple times
            SessionCreator sessionCreator = new SessionCreator();
            sessionCreator.execute();

            setAnimation();
        }
    }


    // *********************************************************************

    // Below: data interface listeners from filter fragments

    // *********************************************************************

    @Override
    public void region(String data) {
        mRegion = data;
        mSingletonInstance.getmMainPageObject().setmRegion1(data);
        Log.i(TAG, "region: " + data);
        mRegionTextView.setText(mSingletonInstance.getmMainPageObject().getmRegion1());
        if (queue != null) {
            queue.stop();
        }
    }

    @Override
    public void cabinClassData(String data) {
        mCabinClass = data;
        mPreCabinClass = data;
        mSingletonInstance.getmMainPageObject().setmCabin(data);
        Log.i(TAG, "cabin: " + data);
        if (queue != null) {
            queue.stop();
        }
    }

    @Override
    public void onDataPass(double data) {
        mMaxPrice = data;
        mSingletonInstance.getmMainPageObject().setmMaxPrice(data);
        if (queue != null) {
            queue.stop();
        }
    }

    @Override
    public void onOutDateChange(String date, String textViewInput) {
        mOutboundDate = date;

        Log.i(TAG, "onOutDateChange: outDate " + date);
        String outyear = date.substring(0, 4);
        Log.i(TAG, "onOutDateChange: year " + outyear);
        String outmonth = date.substring(5, 7);
        Log.i(TAG, "onOutDateChange: month " + outmonth);
        String outday = date.substring(8, 10);
        Log.i(TAG, "onOutDateChange: day " + outday);
        String outnewDay = outmonth + "-" + outday + "-" + outyear;
        Log.i(TAG, "onOutDateChange: day " + outnewDay);
        mOutDate.setText(outnewDay);

        Log.i(TAG, "onFragmentInteraction: new outbound date = " + date);
        mSingletonInstance.getmMainPageObject().setmOutDate(date);
    }

    @Override
    public void onInDateChange(String date, String textViewInput, String normalDayLayout) {
        mInboundDate = date;

        Log.i(TAG, "InitialValues: inDate " + date);
        String year = date.substring(0, 4);
        Log.i(TAG, "InitialValues: year " + year);
        String month = date.substring(5, 7);
        Log.i(TAG, "InitialValues: month " + month);
        String day = date.substring(8, 10);
        Log.i(TAG, "InitialValues: day " + day);
        String newDay = month + "-" + day + "-" + year;
        Log.i(TAG, "InitialValues: day " + newDay);
        mInDate.setText(newDay);
//        mInDate.setText(normalDayLayout);
        Log.i(TAG, "onFragmentInteraction: new inbound date = " + date);
        mSingletonInstance.getmMainPageObject().setmInDate(date);

    }

    @Override
    public void origionAirport(String code, String name, String city, String country) {
        mOriginAirportCode = code;
        oCountry = country;
        origincity = city;
        originAirporstname = name;
        Log.i(TAG, "origionAirport: code " + code);
        Log.i(TAG, "origionAirport: country " + country);
        Log.i(TAG, "origionAirport: city " + city);
        Log.i(TAG, "origionAirport: name " + name);
        mSingletonInstance.getmMainPageObject().setmOutCityCountry(country);
        mSingletonInstance.getmMainPageObject().setmOutboundAirport(name + "(" + code + ")");
        mOutCountry.setText(country);
        mOutAirport.setText(mSingletonInstance.getmMainPageObject().getmOutboundAirport());
        lowest_price.setText("0.00");
        mOutCountry.setText(city + "/" + country);
    }

    // *********************************************************************

    // Below: step 1 of Skyscanner

    // *********************************************************************


    public class SessionCreator extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            Log.i(TAG, "onResponse: jnum1 ");
            String request = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0/";
            URL url = null;
            try {
                url = new URL(request);
//                Log.i(TAG, "doInBackground: url requested: " + url);
//                Log.i(TAG, "onCreate: connection open");
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert conn != null;
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);

//            Log.i(TAG, "onCreate: Post requested");
            try {
                conn.setRequestMethod("POST");
//                Log.i(TAG, "doInBackground: Posted");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

//            Log.i(TAG, "doInBackground: max price " + mMaxPrice);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("charset", "utf-8");

            if (mDestAirportCode == null) {
                callSetup();
            }

            if (!mOriginAirportCode.contains("sky")) {
                mOriginAirportCode = mOriginAirportCode + "-sky";
            }
            if (!mDestAirportCode.contains("sky")) {
                mDestAirportCode = mDestAirportCode + "-sky";
            }

            String urlParameters = mApiKey +
                    "&country=" + mMarket +
                    "&currency=" + mCurrency +
                    "&locale=" + mLocale +
                    "&originplace=" + mOriginAirportCode +
                    "&destinationplace=" + mDestAirportCode +
                    "&outbounddate=" + mOutboundDate +
                    "&inbounddate=" + mInboundDate +
                    "&locationschema=" + "Iata" +
                    "&cabinclass=" + mPreCabinClass +
                    "&adults=" + mAdults;

//            Log.i(TAG, "doInBackground: parameters " + urlParameters);

            DataOutputStream outputStream = null;
            try {
//                Log.i(TAG, "doInBackground: Output stream");
                outputStream = new DataOutputStream(conn.getOutputStream());
                outputStream.writeBytes(urlParameters);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mSessionID = conn.getHeaderField("Location");
//            Log.i(TAG, "doInBackground: sessionID " + mSessionID);

            return mSessionID;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(TAG, "onPostExecute: The session ID: " + s);
            Log.i(TAG, "onPostExecute: The session ID: " + mSessionID);
            sessionPolling();
        }

        @Override
        protected void onCancelled() {
            Log.i(TAG, "onCancelled: anim cancelled");
            super.onCancelled();
        }
    }

    // *********************************************************************

    // Below: step 2 of Skyscanner

    // *********************************************************************


    public void sessionPolling() {
//        Log.i(TAG, "onResponse: jnum2 ");
//        Log.i(TAG, "doInBackground: inside doInBackground with key: " + mSessionID + "?" + mApiKey);
//        Log.i(TAG, "doInBackground: check key above follows this key: http://partners.api.skyscanner.net/apiservices/pricing/v1.0/{sessionKey}?apiKey={apiKey}");

        queue = Volley.newRequestQueue(mContext);

        if (mSessionID == null) {
            if (mAirportCount < 10) {
                callSetup();
                mAirportCount += 1;
            } else {
                LinearLayout layout = (LinearLayout) findViewById(R.id.content_container);
                Snackbar.make(layout, "Sorry there are no flights for your dates", 1000);
//                anim.pause();
            }
        } else {
            JsonObjectRequest objectReq = new JsonObjectRequest(Request.Method.GET, mSessionID + "?" + mApiKey, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
//                        Log.i(TAG, "onResponse: session key " + response.getString("SessionKey"));
                        if (response.getString("SessionKey") != null) {
                            gsonStep2 = new Gson();
                            ItineraryClassGson itinerary = gsonStep2.fromJson(response.toString(), ItineraryClassGson.class);
                            if (itinerary.getItineraries().isEmpty()) {
                                if (mAirportCount < 10) {
                                    callSetup();
                                    mAirportCount += 1;
                                } else {
                                    LinearLayout layout = (LinearLayout) findViewById(R.id.content_container);
                                    Snackbar.make(layout, "Sorry there are no flights for your dates", 1000);
//                                    anim.pause();
                                }
                            } else {
                                mUrlBooking = itinerary.getItineraries().get(0).getBookingDetailsLink().getUri();
                                BookingSessionCreator bSC = new BookingSessionCreator();
                                bSC.execute();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Log.i(TAG, "onErrorResponse: api call stopped working again first poll");

                    if (conn != null) {
                        conn.disconnect();
//                        Log.i(TAG, "onCancelled: anim cancelled");
                        if (mAirportCount < 10) {
                            callSetup();
                            mAirportCount += 1;
                        } else {
                            LinearLayout layout = (LinearLayout) findViewById(R.id.content_container);
                            Snackbar.make(layout, "Sorry there are no flights for your dates", 1000);
//                            anim.pause();
                            queue.stop();
                        }
                    }
                }
            });

            queue.add(objectReq);
        }
    }

    // *********************************************************************

    // Below: step 3 of Skyscanner

    // *********************************************************************

    public class BookingSessionCreator extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            Log.i(TAG, "onResponse: jnum3 ");
            if (mUrlBooking != null) {
//                URL url = null;
                String request = "http://partners.api.skyscanner.net" + mUrlBooking + "?" + mApiKey;
                try {
                    URL url = new URL(request);
//                    Log.i(TAG, "doInBackground: url requested: " + url);
//                    Log.i(TAG, "onCreate: connection open");
                    assert url != null;
                    connection2 = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert conn != null;
                connection2.setDoOutput(true);
                connection2.setInstanceFollowRedirects(false);

                Log.i(TAG, "onCreate: Post requested");
                try {
                    connection2.setRequestMethod("PUT");
//                    Log.i(TAG, "doInBackground: Posted");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }

                String urlParameters = mApiKey +
                        "&outboundlegid=" + mOutboundLegId +
                        "&inboundlegid=" + mInboundLegId;

                DataOutputStream outputStream = null;
                try {
//                    Log.i(TAG, "doInBackground: Output stream");
                    outputStream = new DataOutputStream(connection2.getOutputStream());
                    outputStream.writeBytes(urlParameters);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mBookingSession = conn.getHeaderField("Location");
//                Log.i(TAG, "doInBackground: Booking Session ID = " + mBookingSession);
            }
            return mBookingSession;
        }

        @Override
        protected void onCancelled() {
            if (mAirportCount < 10) {
                callSetup();
                mAirportCount += 1;
            } else {
                LinearLayout layout = (LinearLayout) findViewById(R.id.content_container);
                Snackbar.make(layout, "Sorry there are no flights for your dates", 1000);
//                anim.pause();
            }
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (mBookingSession == null) {
                if (mNoFlightCount < 10) {
                    callSetup();
                    mNoFlightCount += 1;
                } else {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.content_container);
                    Snackbar.make(layout, "Sorry there are no flights for your dates", 1000);
//                    anim.pause();
                    cancel(true);
                }
            } else {
                Log.i(TAG, "onPostExecute: The session ID: " + s);
                bookingPollingSession();
                if (connection2 != null) {
                    connection2.disconnect();
                }
            }
        }
    }

    // *********************************************************************

    // Below: Skyscanner step 4

    // *********************************************************************

    public void bookingPollingSession() {

        originAirporstname = mSingletonInstance.getmInitialValuesObj().getOutAirportName();
        destinedAirportName = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(0);

        oCountry = mSingletonInstance.getmInitialValuesObj().getCountry();
        dCountry = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(1);

        origincity = mSingletonInstance.getmInitialValuesObj().getCity();

        if (DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).size() == 3) {
            // city column if exists
            destinedCity = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(2);
            getThingsToDo(destinedCity, dCountry);
        } else {
            // airport name is that of the city
            destinedCity = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(0);
            getThingsToDo(destinedCity, dCountry);
        }

        Log.i(TAG, "bookingPollingSession: check this for duration " + mBookingSession + "?" + mApiKey);

        JsonObjectRequest objectReq = new JsonObjectRequest(Request.Method.GET, mBookingSession + "?" + mApiKey, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                gsonStep4 = new Gson();
                ItineraryClassGson itinerary;
                itinerary = gsonStep4.fromJson(response.toString(), ItineraryClassGson.class);
                AgentItemsObjGson agentObjClass = gsonStep4.fromJson(response.toString(), AgentItemsObjGson.class);
                LegItemsObjGson legs = gsonStep4.fromJson(response.toString(), LegItemsObjGson.class);
                for (int k = 0; k < legs.getLegs().size(); k++) {
                    String item1 = legs.getLegs().get(k).getArrival();
                    String item2 = legs.getLegs().get(k).getDeparture();
                    String item3 = legs.getLegs().get(k).getId();
                    int item4 = legs.getLegs().get(k).getDuration();
                    int item5 = legs.getLegs().get(k).getStops().size();
//                    Log.i(TAG, "onResponse: duration " + item4);
                    mSingletonInstance.addlegObj(new LegObject(item1, item2, item3, item4, item5));
                }

                if (itinerary.getItineraries().isEmpty()) {
                } else {
                    mDestinationLocation = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(0);
                    mDestinationCountry = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(1);

                    mSingletonInstance.clearSkyFlightObj();
                    for (int j = 0; j < itinerary.getItineraries().size(); j++) {
                        double num = Double.parseDouble(itinerary.getItineraries().get(j).getPricingOptions().get(0).getPrice());
                        if (num > mMaxPrice) {
                        } else {

                            mUrlBooking = itinerary.getItineraries().get(j).getBookingDetailsLink().getUri();

                            idInboundObj = itinerary.getItineraries().get(j).getInboundLegId();
                            outBoundObj = itinerary.getItineraries().get(j).getOutboundLegId();

                            SkyFlightObject skyFlightObject = new SkyFlightObject();
                            skyFlightObject.setmOutId(outBoundObj);
                            skyFlightObject.setmInId(idInboundObj);

                            for (int m = 0; m < mSingletonInstance.getLegObj().size(); m++) {
                                String in, out, legid;
                                in = itinerary.getItineraries().get(j).getInboundLegId();
                                out = itinerary.getItineraries().get(j).getOutboundLegId();
                                legid = mSingletonInstance.getLegObj().get(m).getmId();
                                if (in.equals(legid)) {
                                    arrivalObj = mSingletonInstance.getLegObj().get(m).getmArrival();
                                    departureObj = mSingletonInstance.getLegObj().get(m).getmDeparture();
                                    int duration = mSingletonInstance.getLegObj().get(m).getmDuration();
                                    int stops = mSingletonInstance.getLegObj().get(m).getmStops();

                                    skyFlightObject.setmArrivalInbound(arrivalObj);
                                    skyFlightObject.setmDepartureInbound(departureObj);
                                    skyFlightObject.setmStopsInbound(stops);
                                    skyFlightObject.setmDurationInbound(duration);
                                }
                                if (out.equals(legid)) {
                                    String arrival = mSingletonInstance.getLegObj().get(m).getmArrival();
                                    String departure = mSingletonInstance.getLegObj().get(m).getmDeparture();
                                    int duration = mSingletonInstance.getLegObj().get(m).getmDuration();
                                    int stops = mSingletonInstance.getLegObj().get(m).getmStops();

                                    skyFlightObject.setmArrivalOutbound(arrival);
                                    skyFlightObject.setmDepartureOutbound(departure);
                                    skyFlightObject.setmDurationOutbound(duration);
                                    skyFlightObject.setmStopsOutbound(stops);
                                }
                            }


                            try {
                                if (itinerary.getItineraries().get(j).getPricingOptions().size() != 0) {
                                    for (int l = 0; l < itinerary.getItineraries().get(j).getPricingOptions().size(); l++) {
                                        String agentName = "";
                                        String agentpic = "";
                                        String price = itinerary.getItineraries().get(j).getPricingOptions().get(l).getPrice();

                                        String bookingurl = itinerary.getItineraries().get(j).getPricingOptions().get(l).getDeeplinkUrl();

                                        for (int i = 0; i < price.length(); i++) {
                                            if (price.substring(i, i + 1).equals(".")) {
                                                price = price.substring(0, i + 2) + 0;
                                            }
                                        }

                                        int agentNumber = itinerary.getItineraries().get(j).getPricingOptions().get(l).getAgents().get(0);

                                        for (int a = 0; a < agentObjClass.getAgents().size(); a++) {
                                            if (agentNumber == agentObjClass.getAgents().get(a).getId()) {
                                                agentName = agentObjClass.getAgents().get(a).getName();
                                                agentpic = agentObjClass.getAgents().get(a).getImageUrl();
                                            }
                                        }

                                        JSONObject mQuery = response.getJSONObject("Query");
                                        mOutboundDate = mQuery.optString("OutboundDate");
                                        mInboundDate = mQuery.getString("InboundDate");
                                        mCabinClass = mQuery.getString("CabinClass");

                                        if (j == 0) {

                                            // resetting wiki object
                                            mSingletonInstance.clearWikiObject();

                                            DBHelper.getInstance(mContext).removeCardVItems();
                                            DBHelper.getInstance(mContext).insertCardVItems(price, mOutboundDate, mInboundDate, mDestinationCountry);

                                            if (l == 0) {
                                                lowest_price = (TextView) findViewById(R.id.lowest_price);
                                                lowest_price.setText(price);
                                            }

//                                            Log.i(TAG, "InitialValues: outDate " + mOutboundDate);
                                            outyear = mOutboundDate.substring(0, 4);
//                                            Log.i(TAG, "InitialValues: year " + outyear);
                                            outmonth = mOutboundDate.substring(5, 7);
//                                            Log.i(TAG, "InitialValues: month " + outmonth);
                                            outday = mOutboundDate.substring(8, 10);
//                                            Log.i(TAG, "InitialValues: day " + outday);
                                            outnewDay = outmonth + "-" + outday + "-" + outyear;
//                                            Log.i(TAG, "InitialValues: day " + outnewDay);
                                            mOutDate.setText(outnewDay);

//                                            Log.i(TAG, "InitialValues: inDate " + mInboundDate);
                                            year2 = mInboundDate.substring(0, 4);
//                                            Log.i(TAG, "InitialValues: year " + year);
                                            month2 = mInboundDate.substring(5, 7);
//                                            Log.i(TAG, "InitialValues: month " + month);
                                            day2 = mInboundDate.substring(8, 10);
//                                            Log.i(TAG, "InitialValues: day " + day);
                                            newDay2 = month2 + "-" + day2 + "-" + year2;
//                                            Log.i(TAG, "InitialValues: day " + newDay);
                                            mInDate.setText(newDay2);

                                            mOutCountry.setText(origincity + "/" + oCountry);
                                            mInCountry.setText(destinedCity + "/" + dCountry);
                                            mDestinationAirport.setText(destinedAirportName);
                                            mRegionTextView.setText(mRegion);

                                            WikipediaInformation();
                                            flickrImages(mDestAirportCode, mDestinationLocation, mDestinationCountry);

                                            mSingletonInstance.getmMainPageObject().setmOutboundAirport(originAirporstname);
                                            mSingletonInstance.getmMainPageObject().setmInboundAirport(destinedAirportName);
                                            mSingletonInstance.getmMainPageObject().setmOrigin(mOriginAirportCode);
                                            mSingletonInstance.getmMainPageObject().setmDestination(mDestAirportCode);
                                            mSingletonInstance.getmMainPageObject().setmRegion1(mRegion);
                                            mSingletonInstance.getmMainPageObject().setmOutCityCountry(origincity + "/" + oCountry);
                                            mSingletonInstance.getmMainPageObject().setmInCityCountry(destinedCity + "/" + dCountry);
                                            mSingletonInstance.getmMainPageObject().setmMaxPrice(mMaxPrice);
                                            mSingletonInstance.getmMainPageObject().setmDestinationLocation(mDestinationLocation);
                                            mSingletonInstance.getmMainPageObject().setmInDate(mInboundDate);
                                            mSingletonInstance.getmMainPageObject().setmOutDate(mOutboundDate);
                                            mSingletonInstance.getmMainPageObject().setmCabin(mPreCabinClass);
                                            mSingletonInstance.getmMainPageObject().setMlocationPrice(price);

                                        }

                                        if (l == 0) {
                                            skyFlightObject.setmPrice(price);
                                        }

                                        BookingObj bookingObj = new BookingObj(
                                                mDestAirportCode, mOriginAirportCode, price, mOutboundDate,
                                                mInboundDate, mDestinationCountry, bookingurl, agentName,
                                                agentpic, origincity, destinedCity, originAirporstname,
                                                destinedAirportName, oCountry, dCountry);
                                        skyFlightObject.addBookingObjToList(bookingObj);
//                                        Log.i(TAG, " Agent Name " +
//                                                skyFlightObject.getAllBookingObjs().get(l).getmAgentName());
                                        anim.end();

                                    }
                                    mSingletonInstance.addmSkyFlightObject(skyFlightObject);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.i(TAG, "onErrorResponse: api call stopped working again second poll");
                bookingPollingSession();
                mErrorCount = mErrorCount + 1;
//                Log.i(TAG, "doInBackground: location booking post execute checkpoint " + mDestinationLocation);
//                Log.i(TAG, "onCancelled: anim cancelled");
                anim.end();
            }
        });
        queue.add(objectReq);
    }

    // *********************************************************************

    // Below: setting the new destination

    // *********************************************************************

    public void destinationRandomizer() {
        mDestAirportCode = null;
        if (mRegion.contains("South") && mRegion.contains("America")) {
            String dest = DBHelper.getInstance(mContext).getRandSAmericaAirportCode();
            mDestAirportCode = dest + "-sky";
            Log.i(TAG, "destinationRandomizer: South America ");
        } else if (mRegion.contains("South") && mRegion.contains("Asia")) {
            String dest = DBHelper.getInstance(mContext).getRandSAsiaAirportCode();
            mDestAirportCode = dest + "-sky";
            Log.i(TAG, "destinationRandomizer: South East Asia ");
        } else if (mRegion.contains("North") && mRegion.contains("America")) {
            String dest = DBHelper.getInstance(mContext).getRandNAmericaAirportCode();
            mDestAirportCode = dest + "-sky";
            Log.i(TAG, "destinationRandomizer: North America");
        } else if (mRegion.contains("Europe")) {
            String dest = DBHelper.getInstance(mContext).getRandEUAirportCode();
            mDestAirportCode = dest + "-sky";
            Log.i(TAG, "destinationRandomizer: new Europe");
        } else if (mRegion.contains("Anywhere") || mRegion.equals("")) {
            String dest = DBHelper.getInstance(mContext).getRandomAirportCode();
            mDestAirportCode = dest + "-sky";
        } else {
            String dest = DBHelper.getInstance(mContext).getRandomAirportCode();
            mDestAirportCode = dest + "-sky";
        }
    }

    // *********************************************************************

    // Below: retrieving json objects from wikipedia

    // *********************************************************************

    public void WikipediaInformation() {
        mWikiExtract = "";
        String city = "";

        if (DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).size() == 3) {
            // city column if exists
            city = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(2);
        } else {
            // airport name is that of the city
            city = DBHelper.getInstance(mContext).getCityAndCountry(mDestAirportCode).get(0);
        }

        String encodedCity = null;
        try {
            encodedCity = URLEncoder.encode(city, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        Log.i(TAG, "doInBackground: encoded city: " + encodedCity);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://en.wikipedia.org/w/api.php?action=query&format=json&meta=&continue=&prop=extracts&exintro=&explaintext=&titles=" +
                        encodedCity, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                wikiGson = new Gson();

                try {
                    innerKey = null;
                    JSONObject query = response.getJSONObject("query");
                    JSONObject pages = query.getJSONObject("pages");

                    Type mapType = new TypeToken<Map<String, Map<String, String>>>() {
                    }.getType();
                    Map<String, Map<String, String>> map = wikiGson.fromJson(pages.toString(), mapType);

                    for (String key : map.keySet()) {
                        innerKey = key;
                    }

                    JSONObject numberKeyObj = pages.getJSONObject(innerKey);
                    mWikiExtract = numberKeyObj.getString("extract");

                    lastValue = "";
                    currentValue = "";
                    int lastNum = 0;
                    mWikiParCount = 1;
                    for (int i = 0; i < mWikiExtract.length() - 3; i++) {
                        if (mWikiExtract.length() > 3 && mWikiParCount < 3) {
                            if (i > 0) {
                                currentValue = String.valueOf(mWikiExtract.charAt(i));
                                if (lastValue.equals(".") && (currentValue.equals(" "))) {
                                    if (mWikiParCount == 1) {
                                        String para1 = mWikiExtract.substring(0, i);
                                        mSingletonInstance.setmWikiObject(new WikiObject(para1, ""));
                                        lastNum = i;
                                    } else {
                                        String para2 = mWikiExtract.substring(lastNum + 1, i);
                                        mSingletonInstance.getmWikiObject().setmBlurb2(para2);
                                    }
                                    mWikiParCount = mWikiParCount + 1;
                                }
                            }
                        }

                        lastValue = currentValue;
                    }
                    getWikiText();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }


    // *********************************************************************

    // Below: Getting Flickr Images

    // *********************************************************************


    public void flickrImages(String destination, String theCity, final String country) {


        Log.i(TAG, "flickrImages: I'm in");

        String city = "";
        woeId = "";

        if (DBHelper.getInstance(mContext).getCityAndCountry(destination).size() == 3) {
            // city column if exists
            city = DBHelper.getInstance(mContext).getCityAndCountry(destination).get(2);
        } else {
            // airport name is that of the city
            city = DBHelper.getInstance(mContext).getCityAndCountry(destination).get(0);
        }


        String encodedCity = city.replaceAll(" ", "_");

        mSingletonInstance.setmCity(encodedCity);
        mSingletonInstance.setmCountry(country);


        Log.i(TAG, "onResponse: woe_id " + "https://api.flickr.com/services/rest/?method=flickr.places.find" +
                "&api_key=47516865b27a7ee1b70dfbf1a6d45cb0&query=" +
                country +
                "+" +
                encodedCity +
                "&format=json");

        woeId = "00000";

        StringRequest woeIdRequest = new StringRequest(Request.Method.GET,
                "https://api.flickr.com/services/rest/?method=flickr.places.find" +
                        "&api_key=47516865b27a7ee1b70dfbf1a6d45cb0&query=" + country + "+" + encodedCity +
                        "&format=json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        Log.i(TAG, "Response is: " + response.substring(14, response.length() - 1));
                        try {
                            JSONObject response2 = new JSONObject(response.substring(14, response.length() - 1));
                            JSONObject container = response2.getJSONObject("places");
                            JSONArray array = container.getJSONArray("place");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(0);
                                woeId = object.getString("woeid");
                                Log.i(TAG, "onResponse: " + woeId);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JsonObjectRequest photoGalleryRequest = new JsonObjectRequest(Request.Method.GET,
                                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" +
                                        ApiClass.flickrApi + "&woe_id=" + woeId + "&format=json&nojsoncallback=1",
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {

                                            Log.i(TAG, "onResponse: gallery photos https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" +
                                                    ApiClass.flickrApi + "&woe_id=" + woeId + "&format=json&nojsoncallback=1");
                                            JSONObject container = response.getJSONObject("photos");
                                            JSONArray photos = container.getJSONArray("photo");

//                          populating the Image Gallery Activity
                                            mSingletonInstance.clearGalleryObject();
                                            for (int j = 0; j < photos.length() && j < 50; j++) {
                                                JSONObject items = photos.getJSONObject(j);
                                                String id = items.getString("id");
                                                String secret = items.getString("secret");
                                                int farm = items.getInt("farm");
                                                String server = items.getString("server");
                                                String title = items.getString("title");
                                                mSingletonInstance.addGalleryObject(new ImageGalleryObj(id, secret, server, title, farm));
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        queue.add(photoGalleryRequest);


                        JsonObjectRequest mainPagePhotoRequest = new JsonObjectRequest(Request.Method.GET,
                                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" +
                                        ApiClass.flickrApi + "&woe_id=" + woeId + "&tags=landmark&format=json&nojsoncallback=1",
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {

                                            Log.i(TAG, "onResponse: main photo https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" +
                                                    ApiClass.flickrApi + "&woe_id=" + woeId + "&format=json&nojsoncallback=1");

                                            JSONObject container = response.getJSONObject("photos");
                                            JSONArray photos = container.getJSONArray("photo");

                                            mSingletonInstance.getmMainPageObject().setmUrl("");

//      needed for photo formatting: id; secret; server; farm
//      https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
                                            if (photos.getJSONObject(0) != null) {
                                                JSONObject items = photos.getJSONObject(0);
                                                String id = items.getString("id");
                                                String secret = items.getString("secret");
                                                int farm = items.getInt("farm");
                                                String server = items.getString("server");

                                                Picasso.with(mContext)
                                                        .load("http://farm" +
                                                                farm +
                                                                ".staticflickr.com/" +
                                                                server + "/" +
                                                                id + "_" +
                                                                secret + ".jpg")
                                                        .into(city_photo, new Callback() {
                                                            @Override
                                                            public void onSuccess() {

                                                            }

                                                            @Override
                                                            public void onError() {
                                                                mSingletonInstance.getmMainPageObject().setmUrl("");
                                                            }
                                                        });
                                                String url = "http://farm" +
                                                        farm +
                                                        ".staticflickr.com/" +
                                                        server + "/" +
                                                        id + "_" +
                                                        secret + ".jpg";
                                                mSingletonInstance.getmMainPageObject().setmUrl(url);
                                            } else {
                                                mSingletonInstance.getmMainPageObject().setmUrl("");
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        queue.add(mainPagePhotoRequest);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "That didn't work!");
            }
        });

        queue.add(woeIdRequest);


    }

    // *********************************************************************

    // Below: Setting Variables for 'Things To Do' Gallery

    // *********************************************************************


    public void getThingsToDo(String city, String country) {
        mSingletonInstance.removeAllThingsToDo();

        StringRequest jsonObjectRequest4 = new StringRequest(Request.Method.GET, "https://maps.googleapis.com/maps/api/place/textsearch/json?" +
                "query=point_of_interest+in+" + city + country +
                "&" + ApiClass.googleApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        toDoGson = new Gson();
                        // clearing the list in the singleton
                        ThingsToDoGson itinerary = toDoGson.fromJson(response, ThingsToDoGson.class);
                        for (int i = 0; i < itinerary.getResults().size(); i++) {
                            String openClosed = "hours: unknown";
                            String photoRef = "";
                            String attribution = "";
                            String name = itinerary.getResults().get(i).getmName();
                            String address = itinerary.getResults().get(i).getFormatted_address();
                            if (itinerary.getResults().get(i).getOpening_class() != null) {
                                openClosed = itinerary.getResults().get(i).getOpening_class().getOpen_now();
                            }
                            if (itinerary.getResults().get(i).getPhoto() != null) {
                                photoRef = itinerary.getResults().get(i).getPhoto().get(0).getPhoto_reference();
                                attribution = itinerary.getResults().get(i).getPhoto().get(0).getHtml_attribution();
                            }
                            mSingletonInstance.addToToDoList(new ThingsToDoObject(name, address, photoRef, attribution, openClosed));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(jsonObjectRequest4);
    }


    // *********************************************************************

    // Below: Setting Bottom Bar

    // *********************************************************************


    public void navBarListeners() {

        mNavBOne = (ImageView) findViewById(R.id.home);
        mNavBThree = (ImageView) findViewById(R.id.todo_item);
        mNavBFour = (ImageView) findViewById(R.id.gallery_item);
        mNavBFive = (ImageView) findViewById(R.id.booking_items);

        TextView mTextBOne = (TextView) findViewById(R.id.home_text);
        TextView mTextBThree = (TextView) findViewById(R.id.todo_text);
        TextView mTextBFour = (TextView) findViewById(R.id.gallery_text);
        TextView mTextBFive = (TextView) findViewById(R.id.booking_text);

        mTextBOne.setTextColor(getResources().getColor(R.color.white));
        mTextBThree.setTextColor(getResources().getColor(R.color.black));
        mTextBFour.setTextColor(getResources().getColor(R.color.black));
        mTextBFive.setTextColor(getResources().getColor(R.color.black));

        mNavBOne.setBackgroundResource(R.drawable.home_on);
        mNavBThree.setBackgroundResource(R.drawable.to_see_off);
        mNavBFour.setBackgroundResource(R.drawable.gallery_off);
        mNavBFive.setBackgroundResource(R.drawable.agent_off);

        mNavBThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i(TAG, "onClick: three");
                Intent intent = new Intent(mContext, TodoActivity.class);
                startActivity(intent);
            }
        });

        mNavBFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i(TAG, "onClick: four");
                Intent intent = new Intent(mContext, GalleryActivity.class);
                startActivity(intent);
            }
        });

        mNavBFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i(TAG, "onClick: five");
                Intent intent = new Intent(mContext, AgentsObjRVActivity.class);
                startActivity(intent);
            }
        });
    }

    // *********************************************************************

    // Below: Setting Wiki Text

    // *********************************************************************

    public void getWikiText() {
        para1View = (TextView) findViewById(R.id.city_blurbA);
        para2View = (TextView) findViewById(R.id.city_blurbB);
        if (mSingletonInstance.getmWikiObject() != null) {
            String para1Text = mSingletonInstance.getmWikiObject().getmBulrb1();
            String para2Text = mSingletonInstance.getmWikiObject().getmBlurb2();
            if (para1Text.contains("This is a redirect")) {
                para1Text = "Sorry no information was found";
                para2Text = "There is nothing more to be done";
            }
            para1View.setText(para1Text);
            para2View.setText(para2Text);
        } else {
            View view = findViewById(android.R.id.content);
            // set custom background for an error
            view.setBackgroundResource(R.drawable.atlantis_map);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // *********************************************************************

    // Below: Setting Filter Fragments

    // *********************************************************************


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }

        pf = new PriceFragment();
        cf = new CabinClassFragment();
        rf = new RegionFragment();
        of = new OriginFragment();
        dout = new OutboundFragment();
        din = new ReturnDateFragment();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            if (pf.isVisible() || cf.isVisible() || rf.isVisible() || of.isVisible() || dout.isVisible() || din.isVisible()) {
                fragmentManager.beginTransaction()
                        .replace(R.id.filter_fragment, pf)
                        .addToBackStack(null)
                        .commit();
            } else {
//                pf = new PriceFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.filter_fragment, pf)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.nav_gallery) {
            if (pf.isVisible() || cf.isVisible() || rf.isVisible() || of.isVisible() || dout.isVisible() || din.isVisible()) {
//                cf = new CabinClassFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.filter_fragment, cf)
                        .addToBackStack(null)
                        .commit();
            } else {
//                cf = new CabinClassFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.filter_fragment, cf)
                        .addToBackStack(null)
                        .commit();
            }
        } else if (id == R.id.nav_slideshow) {
            if (pf.isVisible() || cf.isVisible() || rf.isVisible() || of.isVisible() || dout.isVisible() || din.isVisible()) {
//                rf = new RegionFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.filter_fragment, rf)
                        .addToBackStack(null)
                        .commit();
            } else {
//                rf = new RegionFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.filter_fragment, rf)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.nav_manage) {
            if (pf.isVisible() || cf.isVisible() || rf.isVisible() || of.isVisible() || dout.isVisible() || din.isVisible()) {
//                of = new OriginFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.filter_fragment, of)
                        .addToBackStack(null)
                        .commit();
            } else {
//                of = new OriginFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.filter_fragment, of)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.date_out) {
            if (pf.isVisible() || cf.isVisible() || rf.isVisible() || of.isVisible() || din.isVisible()) {
//                dout = new OutboundFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.filter_fragment, dout)
                        .addToBackStack(null)
                        .commit();
            } else {
//                dout = new OutboundFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.filter_fragment, dout)
                        .addToBackStack(null)
                        .commit();
            }
        } else if (id == R.id.date_in) {
            if (pf.isVisible() || cf.isVisible() || rf.isVisible() || of.isVisible() || dout.isVisible()) {
//                din = new OutboundFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.filter_fragment, din)
                        .addToBackStack(null)
                        .commit();
            } else {
//                din = new OutboundFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.filter_fragment, din)
                        .addToBackStack(null)
                        .commit();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);
        if (mInboundDate != null) {
            outState.putString("dayin", mInboundDate);
        }
        if (mOutboundDate != null) {
            outState.putString("dayout", mOutboundDate);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        String outDate = savedInstanceState.getString("dayin");
        String inDate = savedInstanceState.getString("dayout");

        mOutDate.setText(outDate);
        mInDate.setText(inDate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainPageInfo();
        rePopulateOriginAndInOutDate();
        mApiKey = ApiClass.skyscannerApi;
    }

    public void rePopulateOriginAndInOutDate() {
        String temp = mSingletonInstance.getmInitialValuesObj().getOutAirportCode();
        mOriginAirportCode = temp + "-sky";
        mOutboundDate = mSingletonInstance.getmInitialValuesObj().getOutboundDate();
        mInboundDate = mSingletonInstance.getmInitialValuesObj().getInboundDate();
        if (mSingletonInstance.getmMainPageObject().getmOutDate() != null && !mSingletonInstance.getmMainPageObject().getmOutDate().equals("")) {
            mOutDate.setText(mSingletonInstance.getmMainPageObject().getmOutDate());
        }
        if (mSingletonInstance.getmMainPageObject().getmInDate() != null && !mSingletonInstance.getmMainPageObject().getmInDate().equals("")) {
            mInDate.setText(mSingletonInstance.getmMainPageObject().getmInDate());
        }
    }

    public void setAnimation() {
        if (anim.isPaused()) {
            anim.setDuration(2000);
            anim.setRepeatCount(ObjectAnimator.INFINITE);
            anim.setRepeatMode(ObjectAnimator.RESTART);
            anim.resume();
        } else {
            anim.setDuration(2000);
            anim.setRepeatCount(ObjectAnimator.INFINITE);
            anim.setRepeatMode(ObjectAnimator.RESTART);
            anim.start();
        }
    }

    public void clearBookingObjects() {
        if (mSingletonInstance.getSkyFlightObject() != null) {
            for (int i = 0; i < mSingletonInstance.getSkyFlightObject().size(); i++) {
                mSingletonInstance.getSkyFlightObject().get(i).clearBookingObjects();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null) {
//            anim.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (queue != null) {
            queue.stop();
        }
        if (anim != null) {
            anim.end();
        }
    }


}
