package com.ander.quicktrip;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Region;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by anders on 10/14/2016.
 */
public class CustomRegionAdapter extends RecyclerView.Adapter<CustomRegionAdapter.RegionViewHolder> {

    ArrayList<RegionObj> array;
    Context mContext;
    RegionInterface mInterface;

    public CustomRegionAdapter(ArrayList<RegionObj> objectArray, Context context, RegionInterface givenInterface) {
        array = objectArray;
        mInterface = givenInterface;
        mContext = context;
    }

    @Override
    public RegionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.region_listview_items,parent,false);
        RegionViewHolder RVH = new RegionViewHolder(root);
        return RVH;
    }

    @Override
    public void onBindViewHolder(RegionViewHolder holder, final int position) {
        holder.title.setText(array.get(position).getmName());
        Picasso.with(mContext).load(array.get(position).getmURL()).into(holder.image);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionMethod(array.get(position).getmName());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(array != null || array.size() > 0) {
        return array.size();
        }
        return 0;
    }

    public void regionMethod(String data){
        mInterface.region(data);
    }

    public interface RegionInterface{
        public void region(String data);
    }

    public class RegionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView image;
        ObjectAnimator mObjAnimator;
        AlphaAnimation mAlphaAnimator;
        private static final String TAG = "RegionViewHolder";

        public RegionViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.region_text);
            image = (ImageView) itemView.findViewById(R.id.region_image);
            mObjAnimator = ObjectAnimator.ofFloat(image, "rotationY", 0.0f, 180f);
            mAlphaAnimator = new AlphaAnimation(0.8f, 1f);
        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mObjAnimator.start();
                    v.setAnimation(mAlphaAnimator);
                    Log.i(TAG, "onClick: ");
                }
            });
        }
    }

}
