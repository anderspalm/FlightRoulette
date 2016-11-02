package com.ander.quicktrip;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ander on 9/1/2016.
 */
public class PhotosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mPhoto;
//    TextView mPhotoTitle;

    public PhotosViewHolder(View itemView) {
        super(itemView);
        mPhoto = (ImageView) itemView.findViewById(R.id.rec_view_photo);
//        mPhotoTitle = (TextView) itemView.findViewById(R.id.photo_title);
    }

    @Override
    public void onClick(View view) {
//        if (mManager.getBackStackEntryCount() > 0) {
//            mManager.popBackStack();
//        }
//
//        if(mPosition < mPhotoSet.size()) {
//            Bundle bundle = new Bundle();
//            bundle.putInt("Farm", mPhotoSet.get(mPosition).getFarm());
//            bundle.putString("Server", mPhotoSet.get(mPosition).getServer());
//            bundle.putString("Id", mPhotoSet.get(mPosition).getId());
//            bundle.putString("Secret", mPhotoSet.get(mPosition).getSecret());
//
//            Log.i(TAG, "onClick: position " + mPosition);
//            GalleryItemExpandedFragment gf = new GalleryItemExpandedFragment();
//
////                    gf.setArguments(bundle);
////                    mManager.beginTransaction().add(R.id.gallery_item_inflated_fragment, gf)
////                            .addToBackStack(null)
////                            .commit();
//
//        }
    }
}
