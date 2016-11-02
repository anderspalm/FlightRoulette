package com.ander.quicktrip;

/**
 * Created by anders on 10/21/2016.
 */
public class LegObject {

    String mArrival, mDeparture, mId;
    int mDuration, mStops;

    public LegObject(String mArrival, String mDeparture, String mId, int mDuration, int mStops) {
        this.mArrival = mArrival;
        this.mDeparture = mDeparture;
        this.mId = mId;
        this.mDuration = mDuration;
        this.mStops = mStops;
    }

    public String getmArrival() {
        return mArrival;
    }

    public void setmArrival(String mArrival) {
        this.mArrival = mArrival;
    }

    public String getmDeparture() {
        return mDeparture;
    }

    public void setmDeparture(String mDeparture) {
        this.mDeparture = mDeparture;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public int getmStops() {
        return mStops;
    }

    public void setmStops(int mStops) {
        this.mStops = mStops;
    }
}
