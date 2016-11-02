package com.ander.quicktrip;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by anders on 10/13/2016.
 */
public class PriceFragment extends Fragment {

    SeekBar mPriceBar;
    TextView mTextView;
    public double mMaxPrice;
    OnDataPass dataPasser;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dataPasser = (OnDataPass) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.price_fragment,container,false);
        mTextView = (TextView) root.findViewById(R.id.seek_bar_value);

        ImageView fab = (ImageView) root.findViewById(R.id.back_press);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        getActivity().setTitle("Price Filter");

        mPriceBar = (SeekBar) root.findViewById(R.id.price_seek_bar);
        mPriceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int price = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                price = i;
                mTextView.setText("$" + price);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMaxPrice = price;
                passData(price);
            }
        });

        return root;
    }

    public void passData(double data) {
        dataPasser.onDataPass(data);
    }

    public interface OnDataPass {
        public void onDataPass(double data);
    }
}
