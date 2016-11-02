package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    ImageView mNavBOne, mNavBThree, mNavBFour, mNavBFive;
    Context mContext;
    PhotosRecyclerViewAdapter mAdapter;
    List<ImageGalleryObj> mImageGalleryObjList;
    int mLastElement;
    FragmentManager fragmentManager;

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mContext = GalleryActivity.this;


        fragmentManager = getSupportFragmentManager();

        LinearLayout pageLayout = (LinearLayout) findViewById(R.id.gallery_layout);
        pageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }
            }
        });

        mImageGalleryObjList = new ArrayList<>();

        navBarListeners();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.peopleGalleryRV);
        StaggeredGridLayoutManager staggeredGridLayout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayout);
        recyclerView.addOnScrollListener(new EndlessScrollingClass(staggeredGridLayout) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                morePhotos(totalItemsCount);
            }
        });
        mAdapter = new PhotosRecyclerViewAdapter(mContext, MasterAirportList.getINSTANCE().getGalleryObject(), fragmentManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void morePhotos(final int lastelement) {

        String city = MasterAirportList.getINSTANCE().getmCity();
        String country = MasterAirportList.getINSTANCE().getmCountry();

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest job = new JsonObjectRequest(Request.Method.GET,
                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" +
                        ApiClass.flickrApi + "&tags=" + country + "&text=" + city + "&format=json&nojsoncallback=1",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.i(TAG, "onFlickrResponse response: " + response);
                        try {
                            JSONObject container = response.getJSONObject("photos");
                            JSONArray photos = container.getJSONArray("photo");
                            Log.i(TAG, "onFlickrResponse size: " + photos.length());
                            if (lastelement < photos.length()) {
                                mLastElement = lastelement;

//                          populating the Image Gallery Activity


                                for (int j = mLastElement; j < (mLastElement + 50) && j < photos.length(); j++) {
                                    JSONObject items = photos.getJSONObject(j);
                                    String id = null;
//                                    Log.i(TAG, "onResponse: index " + j);
                                    try {
                                        id = items.getString("id");
                                        String secret = items.getString("secret");
                                        int farm = items.getInt("farm");
                                        String server = items.getString("server");
                                        String title = items.getString("title");
                                        MasterAirportList.getINSTANCE().addGalleryObject(new ImageGalleryObj(id, secret, server, title, farm));
                                        mImageGalleryObjList.add(new ImageGalleryObj(id, secret, server, title, farm));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                mAdapter.addMoreItems(mImageGalleryObjList);

//                                GalleryAsyncTask galleryAsyncTask = new GalleryAsyncTask();
//                                galleryAsyncTask.execute(response);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: ");
                error.printStackTrace();
            }
        });
        requestQueue.add(job);
    }

    public void navBarListeners() {

        mNavBOne = (ImageView) findViewById(R.id.home);
        mNavBThree = (ImageView) findViewById(R.id.todo_item);
        mNavBFour = (ImageView) findViewById(R.id.gallery_item);
        mNavBFive = (ImageView) findViewById(R.id.booking_items);

        mNavBOne.setBackgroundResource(R.drawable.home_off);
        mNavBThree.setBackgroundResource(R.drawable.to_see_off);
        mNavBFour.setBackgroundResource(R.drawable.gallery_on);
        mNavBFive.setBackgroundResource(R.drawable.gallery_off);

        TextView mTextBOne = (TextView) findViewById(R.id.home_text);
        TextView mTextBThree = (TextView) findViewById(R.id.todo_text);
        TextView mTextBFour = (TextView) findViewById(R.id.gallery_text);
        TextView mTextBFive = (TextView) findViewById(R.id.booking_text);

        mTextBOne.setTextColor(getResources().getColor(R.color.black));
        mTextBThree.setTextColor(getResources().getColor(R.color.black));
        mTextBFour.setTextColor(getResources().getColor(R.color.white));
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
                Intent intent = new Intent(mContext, TodoActivity.class);
                startActivity(intent);
            }
        });

        mNavBFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: four");
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
}
