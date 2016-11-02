package com.ander.quicktrip;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.GalleryViewHolder> {

    private static final String TAG = "RegionRecyclerViewAd";
    List<ImageGalleryObj> mPhotoSet;
    Context mContext;
    int mSetterPosition;
    int mGetterPosition;
    FragmentManager mManager;

    public PhotosRecyclerViewAdapter(Context context, List<ImageGalleryObj> list, FragmentManager manager) {
        mContext = context;
//        mPhotoSet = MasterListSingleton.getInstance().getmImageGalleryObjs();
        mPhotoSet = list;
        mManager = manager;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_rv_container, parent, false);
        GalleryViewHolder recyclerView = new GalleryViewHolder(view);
        return recyclerView;
    }
    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {

        mSetterPosition = position;

        Picasso.with(mContext)
                .load("https://farm" +
                        mPhotoSet.get(mSetterPosition).getFarm() +
                        ".staticflickr.com/" +
                        mPhotoSet.get(mSetterPosition).getServer() + "/" +
                        mPhotoSet.get(mSetterPosition).getId() + "_" +
                        mPhotoSet.get(mSetterPosition).getSecret() + ".jpg")
                .into(holder.mPhoto);
    }

    public void addMoreItems(List<ImageGalleryObj> list) {
        for (int i = 0; i < list.size(); i++) {
            mPhotoSet.add(list.get(i));
        }
    }

    @Override
    public int getItemCount() {
        if (mPhotoSet == null || mPhotoSet.size() == 0) {
            return 0;
        } else {
            return mPhotoSet.size();
        }
    }


    public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mPhoto;
//    TextView mPhotoTitle;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mPhoto = (ImageView) itemView.findViewById(R.id.rec_view_photo);
//        mPhotoTitle = (TextView) itemView.findViewById(R.id.photo_title);
        }

        @Override
        public void onClick(View view) {
            mGetterPosition = getAdapterPosition();
            Log.i(TAG, "onClick: mGetterPosition " + mGetterPosition);
            if (mManager.getBackStackEntryCount() > 0) {
                mManager.popBackStack();
            }

            if (mGetterPosition < mPhotoSet.size()) {
                Bundle bundle = new Bundle();
                bundle.putInt("Farm", mPhotoSet.get(mGetterPosition).getFarm());
                bundle.putString("Server", mPhotoSet.get(mGetterPosition).getServer());
                bundle.putString("Id", mPhotoSet.get(mGetterPosition).getId());
                bundle.putString("Secret", mPhotoSet.get(mGetterPosition).getSecret());

                Log.i(TAG, "onClick: position " + mGetterPosition);
                GalleryItemExpandedFragment gf = new GalleryItemExpandedFragment();
                                    gf.setArguments(bundle);
                    mManager.beginTransaction().add(R.id.gallery_item_inflated_fragment, gf)
                            .addToBackStack(null)
                            .commit();
            }
        }
    }
}