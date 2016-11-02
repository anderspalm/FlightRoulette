package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TodoActivity extends AppCompatActivity {

    ImageView mNavBOne, mNavBTwo, mNavBThree, mNavBFour, mNavBFive;
    Context mContext;
    TodoRecViewAdapter mAdapter;
    private static final String TAG = "TodoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        mContext = TodoActivity.this;

        navBarListeners();

        if (MasterAirportList.getINSTANCE().getThingsToDo() != null) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.toDoGalleryRV);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, true);
            recyclerView.setLayoutManager(linearLayoutManager);
            mAdapter = new TodoRecViewAdapter(MasterAirportList.getINSTANCE().getThingsToDo(), mContext);
            recyclerView.setAdapter(mAdapter);
            Log.i(TAG, "onCreate: " + MasterAirportList.getINSTANCE().getThingsToDo().size());
        }
    }


    public void navBarListeners() {

        mNavBOne = (ImageView) findViewById(R.id.home);
//        mNavBTwo = (ImageView) findViewById(R.id.information);
        mNavBThree = (ImageView) findViewById(R.id.todo_item);
        mNavBFour = (ImageView) findViewById(R.id.gallery_item);
        mNavBFive = (ImageView) findViewById(R.id.booking_items);

        mNavBOne.setBackgroundResource(R.drawable.home_off);
//        mNavBTwo.setBackgroundResource(R.drawable.agent_black);
        mNavBThree.setBackgroundResource(R.drawable.to_see_on);
        mNavBFour.setBackgroundResource(R.drawable.gallery_off);
        mNavBFive.setBackgroundResource(R.drawable.agent_off);

        TextView mTextBOne = (TextView) findViewById(R.id.home_text);
        TextView mTextBThree = (TextView) findViewById(R.id.todo_text);
        TextView mTextBFour = (TextView) findViewById(R.id.gallery_text);
        TextView mTextBFive = (TextView) findViewById(R.id.booking_text);

        mTextBOne.setTextColor(getResources().getColor(R.color.black));
        mTextBThree.setTextColor(getResources().getColor(R.color.white));
        mTextBFour.setTextColor(getResources().getColor(R.color.black));
        mTextBFive.setTextColor(getResources().getColor(R.color.black));

        mNavBOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: one ");
                Intent intent = new Intent(mContext, MainEventPageActivity.class);
                startActivity(intent);
            }
        });

        mNavBThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: three");
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
                Intent intent = new Intent(mContext, AgentsObjRVActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        if (MasterAirportList.getINSTANCE().getThingsToDo() != null && mAdapter != null) {
            mAdapter.updateAdapterList(MasterAirportList.getINSTANCE().getThingsToDo());
            mAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }
}
