package com.ander.quicktrip;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TodoViewHolder extends RecyclerView.ViewHolder{

    ImageView mImage;
    TextView mName, mAddress;
    RelativeLayout mOpenOrClosed;

    public TodoViewHolder(View itemView) {
        super(itemView);

        mImage = (ImageView) itemView.findViewById(R.id.gallery_photo);
        mName = (TextView) itemView.findViewById(R.id.gallery_name);
        mAddress = (TextView) itemView.findViewById(R.id.gallery_address);
        mOpenOrClosed = (RelativeLayout) itemView.findViewById(R.id.openOrClosed);
    }
}
