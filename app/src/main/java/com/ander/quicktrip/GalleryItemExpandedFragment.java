package com.ander.quicktrip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by anders on 10/18/2016.
 */
public class GalleryItemExpandedFragment extends Fragment{

    Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.gallery_expanded_item_fragment,container,false);

        mContext = getContext();

        Bundle results = getArguments();
        int farm = results.getInt("Farm");
        String server = results.getString("Server");
        String id = results.getString("Id");
        String secret = results.getString("Secret");

        ImageView imageView = (ImageView) root.findViewById(R.id.gallery_expanded_image);
        Picasso.with(mContext)
                .load("https://farm" +
                        farm +
                        ".staticflickr.com/" +
                        server + "/" +
                        id + "_" +
                        secret + ".jpg")
                .into(imageView);

        RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.gallery_background);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        ImageView imageView1 = (ImageView) root.findViewById(R.id.back_press);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
//                Intent intent = new Intent(mContext,AgentsObjRVActivity.class);
//                mContext.startActivity(intent);
            }
        });

        return root;
    }


}
