package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AgentsObjRVActivity extends AppCompatActivity {

    private static final String TAG = "AgentsObjRVActivity";
    Context mContext;
    TextView mOutDate, mInDate;
    ImageView mNavBOne,mNavBTwo,mNavBThree,mNavBFour,mNavBFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agents);

        navBarListeners();

        mContext = this;

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.flight_agents_fragment);

        ArrayList<SkyFlightObject> arrayList = MasterAirportList.getINSTANCE().getSkyFlightObject();

        FragmentManager fragmentManager = getSupportFragmentManager();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sky_flights);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SkyFlightsAdapter adapter = new SkyFlightsAdapter(mContext,arrayList,fragmentManager,frameLayout);
        recyclerView.setAdapter(adapter);

    }

    public void navBarListeners(){

        mNavBOne = (ImageView) findViewById(R.id.home);
        mNavBThree = (ImageView) findViewById(R.id.todo_item);
        mNavBFour = (ImageView) findViewById(R.id.gallery_item);
        mNavBFive = (ImageView) findViewById(R.id.booking_items);

        mNavBOne.setBackgroundResource(R.drawable.home_off);
        mNavBThree.setBackgroundResource(R.drawable.to_see_off);
        mNavBFour.setBackgroundResource(R.drawable.gallery_off);
        mNavBFive.setBackgroundResource(R.drawable.agent_on);

        TextView mTextBOne = (TextView) findViewById(R.id.home_text);
        TextView mTextBThree = (TextView) findViewById(R.id.todo_text);
        TextView mTextBFour = (TextView) findViewById(R.id.gallery_text);
        TextView mTextBFive = (TextView) findViewById(R.id.booking_text);

        mTextBOne.setTextColor(getResources().getColor(R.color.black));
        mTextBThree.setTextColor(getResources().getColor(R.color.black));
        mTextBFour.setTextColor(getResources().getColor(R.color.black));
        mTextBFive.setTextColor(getResources().getColor(R.color.white));

        mNavBOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: one ");
                                Intent intent = new Intent(mContext, MainEventPageActivity.class);
                startActivity(intent);
            }
        });

//        mNavBTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i(TAG, "onClick: two");
//                                Intent intent = new Intent(mContext, InfoActivity.class);
//                startActivity(intent);
//            }
//        });

        mNavBThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: three");
                                Intent intent = new Intent(mContext, TodoActivity.class);
                startActivity(intent);
            }
        });

        mNavBFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: four");
                                Intent intent = new Intent(mContext, GalleryActivity.class);
                startActivity(intent);
            }
        });

        mNavBFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: five");
            }
        });
    }

}
