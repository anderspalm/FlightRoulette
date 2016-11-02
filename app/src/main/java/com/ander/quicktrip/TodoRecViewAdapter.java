package com.ander.quicktrip;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by anders on 10/5/2016.
 */
public class TodoRecViewAdapter extends RecyclerView.Adapter<TodoViewHolder>{

    List<ThingsToDoObject> mList;
    Context mContext;
    TodoViewHolder TDVH;
    private static final String TAG = "TodoRecViewAdapter";

    public TodoRecViewAdapter(List<ThingsToDoObject> list, Context context) {
        if(list.size() != 0) {
            mList = list;
            Log.i(TAG, "TodoRecViewAdapter: " + list.size());
            mContext = context;
            Log.i(TAG, "TodoRecViewAdapter: " + list.get(0).getmPhotoReference());
        }
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.todo_rec_view_item,parent,false);
        TDVH = new TodoViewHolder(view);
        return TDVH;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        if(mList != null) {
            if(mList.get(position).getmOpen_now() != null) {
                if (mList.get(position).getmOpen_now().equals("true")) {
                    TDVH.mOpenOrClosed.setBackgroundColor(Color.GREEN);
                }
                else if (mList.get(position).getmOpen_now().contains("unknown")){
                    TDVH.mOpenOrClosed.setBackgroundColor(Color.TRANSPARENT);
                }
                else {
                    TDVH.mOpenOrClosed.setBackgroundColor(Color.RED);
                }
            }
            else {
            }
            Picasso.with(mContext).load("https://maps.googleapis.com/maps/api/place/photo?" +
                    "maxwidth=400&photoreference=" + mList.get(position).getmPhotoReference() +
                    "&sensor=false&key=AIzaSyB1W66gkdI58wBS2xQECpQQjzbfRXXDGok").into(TDVH.mImage);
            TDVH.mName.setText(mList.get(position).getmSiteName());

//            if (mList.get(position).getmPhotoReference().contains("sensor=false") ||
//                    (!mList.get(position).getmSiteName().contains("otel") ||
//                            !mList.get(position).getmSiteName().contains("irport"))){
//                mList.remove(position);
//            }
        }
    }

    public void updateAdapterList(List<ThingsToDoObject> list){
        mList = list;
        notifyDataSetChanged();
        Log.i(TAG, "updateAdapterList: " + list.size());
    }

    @Override
    public int getItemCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }
}
