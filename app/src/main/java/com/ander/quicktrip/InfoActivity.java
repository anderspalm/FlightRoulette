package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class InfoActivity extends Fragment {

    Context mContext;
    ImageView mNavBOne, mNavBThree, mNavBFour, mNavBFive;

    private static final String TAG = "InfoActivity";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();

        View view = inflater.inflate(R.layout.activity_info, container, false);
        backPressListener(view);
        getWikiText(view);
        navBarListeners(view);

        return view;
    }

    public void backPressListener(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.back_press);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                Intent intent = new Intent(getActivity(),MainEventPageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getWikiText(View view) {
        TextView para1View = (TextView) view.findViewById(R.id.city_blurb1);
        TextView para2View = (TextView) view.findViewById(R.id.city_blurb2);
        if (MasterAirportList.getINSTANCE().getmWikiObject() != null) {
            Log.i(TAG, "getWikiText: wiki text " + MasterAirportList.getINSTANCE().getmWikiObject().getmBulrb1());
            String para1Text = MasterAirportList.getINSTANCE().getmWikiObject().getmBulrb1();
            String para2Text = MasterAirportList.getINSTANCE().getmWikiObject().getmBlurb2();
            para1View.setText(para1Text);
            para2View.setText(para2Text);
        } else {
//            Todo: something on error
        }
    }


    // *********************************************************************

    // Below: Setting Bottom Bar

    // *********************************************************************

    public void navBarListeners(View view) {

        mNavBOne = (ImageView) view.findViewById(R.id.home);
        mNavBThree = (ImageView) view.findViewById(R.id.todo_item);
        mNavBFour = (ImageView) view.findViewById(R.id.gallery_item);
        mNavBFive = (ImageView) view.findViewById(R.id.booking_items);

        mNavBOne.setBackgroundResource(R.drawable.home_on);
        mNavBThree.setBackgroundResource(R.drawable.to_see_off);
        mNavBFour.setBackgroundResource(R.drawable.gallery_off);
        mNavBFive.setBackgroundResource(R.drawable.agent_off);

        mNavBOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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
}
