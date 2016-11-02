package com.ander.quicktrip;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class LandingPage extends AppCompatActivity {


    Context mContext;
    public String mInboundDate, mOutboundDate, mOutboundAirport;
    private static final String TAG = "LandingPageActivity";
    Button mLocalAirportQuery;
    ImageView mInDate, mOutDate, mBackgroundImage;
    DatePicker inDatepicker, outDatepicker;
    String woeId;
    AlphaAnimation mAlphaClickAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.landing);
        mContext = LandingPage.this;

        mAlphaClickAnim = new AlphaAnimation(1f, 0.8f);

//        String a = "abcdefg";
//
//        Log.i(TAG, "onCreate: " + a.substring(0,0));
//        Log.i(TAG, "onCreate: " + a.substring(0,1));
//        Log.i(TAG, "onCreate: " + a.substring(0,2));
//        Log.i(TAG, "onCreate: " + a.substring(0,3));
//        Log.i(TAG, "onCreate: " + a.substring(0,4));
//        Log.i(TAG, "onCreate: " + a.substring(0,5));
//

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        mBackgroundImage = (ImageView) findViewById(R.id.landing_page);

        mBackgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inDatepicker.setVisibility(View.GONE);
                outDatepicker.setVisibility(View.GONE);
                String inAdjDay;
                String inAdjMonth;

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                int inMonth = inDatepicker.getMonth();
                int tempMonth = inMonth + 1;
                inAdjMonth = String.valueOf(tempMonth);
                if (tempMonth < 10) {
                    inAdjMonth = "0" + inAdjMonth;
                }

                int inDay = inDatepicker.getDayOfMonth();
                inAdjDay = String.valueOf(inDay);
                if (inDay < 10) {
                    inAdjDay = "0" + inDay;
                }
                int inYear = inDatepicker.getYear();
                mInboundDate = inYear + "-" + inAdjMonth + "-" + inAdjDay;

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                String outAdjDay;
                String outAdjMonth;
                int outMonth = outDatepicker.getMonth();
                int tempMonth2 = outMonth + 1;
                outAdjMonth = String.valueOf(tempMonth2);
                if (tempMonth2 < 10) {
                    outAdjMonth = "0" + tempMonth2;
                }

                int outDay = outDatepicker.getDayOfMonth();
                outAdjDay = String.valueOf(outDay);
                if (outDay < 10) {
                    outAdjDay = "0" + outDay;
                }
                int outYear = outDatepicker.getYear();
                mOutboundDate = outYear + "-" + outAdjMonth + "-" + outAdjDay;

            }
        });

        mInDate = (ImageView) findViewById(R.id.in_date);
        mOutDate = (ImageView) findViewById(R.id.out_date);

        outDatepicker = (DatePicker) findViewById(R.id.outDatePicker);
        inDatepicker = (DatePicker) findViewById(R.id.inDatePicker);
//
//        final Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datepickerdialog = new DatePickerDialog(LandingPage.this,
//                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,this,year,month,day);

        mInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(mAlphaClickAnim);
                inDatepicker.setVisibility(View.VISIBLE);
                Log.i(TAG, "onClick: inList" + inDatepicker.getYear() + "-" + inDatepicker.getMonth() + "-" + inDatepicker.getDayOfMonth());
            }
        });

        mOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(mAlphaClickAnim);
                outDatepicker.setVisibility(View.VISIBLE);
                Log.i(TAG, "onClick: outList" + outDatepicker.getYear() + "-" + outDatepicker.getMonth() + "-" + outDatepicker.getDayOfMonth());
            }
        });


        mLocalAirportQuery = (Button) findViewById(R.id.toLocalAirport);
        mLocalAirportQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(mAlphaClickAnim);
                if (mInboundDate != null && mOutboundDate != null) {
                    Log.i(TAG, "onClick: " + mOutboundAirport);
                    Log.i(TAG, "onClick: " + mOutboundDate + " " + mInboundDate);

                    MasterAirportList.getINSTANCE().setmInitialValuesObj(new InitialValuesObj());
                    MasterAirportList.getINSTANCE().getmInitialValuesObj().setOutboundDate(mOutboundDate);
                    MasterAirportList.getINSTANCE().getmInitialValuesObj().setInboundDate(mInboundDate);

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    FrameLayout f = (FrameLayout) findViewById(R.id.location_fragment_placeholder);
                    f.setVisibility(View.VISIBLE);
                    LocationFragment fragment = new LocationFragment();
                    fragmentTransaction.add(R.id.location_fragment_placeholder, fragment);
                    fragmentTransaction.commit();

                } else {
                    Snackbar.make(view,"Please choose some dates, you may change them again later.",Snackbar.LENGTH_SHORT).setActionTextColor(Color.RED).setAction("Action",null).show();
                }
            }
        });

    }

}
