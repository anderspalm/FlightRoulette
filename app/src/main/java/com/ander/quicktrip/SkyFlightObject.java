package com.ander.quicktrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anders on 10/21/2016.
 */
public class SkyFlightObject {

    String mOutId, mInId, mPrice, mDepartureInbound, mArrivalInbound;
    String mDepartureOutbound, mArrivalOutbound;
    int mStopsInbound, mStopsOutbound, mDurationOutbound, mDurationInbound;
    ArrayList<BookingObj> mBookingObject;

    public SkyFlightObject(){

    }

    public SkyFlightObject(String mInId, String mOutId) {
        this.mInId = mInId;
        this.mOutId = mOutId;
    }

    public SkyFlightObject(String mOutId, String mInId, String mPrice, String mDepartureInbound,
                           String mArrivalInbound, int mDurationInbound,
                           String mDepartureOutbound, String mArrivalOutbound,
                           int mDurationOutbound, int mStopsInbound, int mStopsOutbound,
                           ArrayList<BookingObj> mBookingObject) {
        this.mOutId = mOutId;
        this.mInId = mInId;
        this.mPrice = mPrice;
        this.mDepartureInbound = mDepartureInbound;
        this.mArrivalInbound = mArrivalInbound;
        this.mDurationInbound = mDurationInbound;
        this.mDepartureOutbound = mDepartureOutbound;
        this.mArrivalOutbound = mArrivalOutbound;
        this.mDurationOutbound = mDurationOutbound;
        this.mStopsInbound = mStopsInbound;
        this.mStopsOutbound = mStopsOutbound;
        this.mBookingObject = mBookingObject;
    }

    public String getmOutId() {
        return mOutId;
    }

    public void setmOutId(String mOutId) {
        this.mOutId = mOutId;
    }

    public String getmInId() {
        return mInId;
    }

    public void setmInId(String mInId) {
        this.mInId = mInId;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmDepartureInbound() {
        return mDepartureInbound;
    }

    public void setmDepartureInbound(String mDepartureInbound) {
        this.mDepartureInbound = mDepartureInbound;
    }

    public String getmArrivalInbound() {
        return mArrivalInbound;
    }

    public void setmArrivalInbound(String mArrivalInbound) {
        this.mArrivalInbound = mArrivalInbound;
    }

    public String getmDepartureOutbound() {
        return mDepartureOutbound;
    }

    public void setmDepartureOutbound(String mDepartureOutbound) {
        this.mDepartureOutbound = mDepartureOutbound;
    }

    public String getmArrivalOutbound() {
        return mArrivalOutbound;
    }

    public void setmArrivalOutbound(String mArrivalOutbound) {
        this.mArrivalOutbound = mArrivalOutbound;
    }

    public int getmStopsInbound() {
        return mStopsInbound;
    }

    public void setmStopsInbound(int mStopsInbound) {
        this.mStopsInbound = mStopsInbound;
    }

    public int getmStopsOutbound() {
        return mStopsOutbound;
    }

    public void setmStopsOutbound(int mStopsOutbound) {
        this.mStopsOutbound = mStopsOutbound;
    }

    public int getmDurationOutbound() {
        return mDurationOutbound;
    }

    public void setmDurationOutbound(int mDurationOutbound) {
        this.mDurationOutbound = mDurationOutbound;
    }

    public int getmDurationInbound() {
        return mDurationInbound;
    }

    public void setmDurationInbound(int mDurationInbound) {
        this.mDurationInbound = mDurationInbound;
    }




    public ArrayList<BookingObj> getAllBookingObjs() {
        return mBookingObject;
    }

    public void addBookingObjToList(BookingObj bookingObj) {
        if(mBookingObject == null){
            mBookingObject = new ArrayList<>();
        }
        mBookingObject.add(bookingObj);
    }

    public void clearBookingObjects(){
        if(mBookingObject == null){
            mBookingObject = new ArrayList<>();
        }
        if(mBookingObject != null){
            mBookingObject.clear();
        }
    }
}
