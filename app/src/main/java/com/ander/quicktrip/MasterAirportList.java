package com.ander.quicktrip;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 9/30/2016.
 */
public class MasterAirportList {

    public InitialValuesObj mInitialValuesObj;
    ArrayList<AirportObject> mAirportList;
    public List<BookingObj> mBookingObject;
    public List<ThingsToDoObject> mThingsToDoList;
    public MainPageObject mMainPageObject;
    public WikiObject mWikiObject;
    public ArrayList<ImageGalleryObj> mImageGalleryArray;
    public String mCity, mCountry;

    private static final String TAG = "MasterAirportList";

    public static MasterAirportList INSTANCE;

    public static MasterAirportList getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new MasterAirportList();
        }
        return INSTANCE;
    }

    // *********************************************************************

    // Below: SkyScanner Airport Object

    // *********************************************************************



    public ArrayList<SkyFlightObject> mSkyFlightObject;

    public ArrayList<SkyFlightObject> getSkyFlightObject() {
        if(mSkyFlightObject == null){
            mSkyFlightObject = new ArrayList<>();
        }
        return mSkyFlightObject;
    }

    public void addmSkyFlightObject(SkyFlightObject skyFlightObject) {
        if(mSkyFlightObject == null){
            mSkyFlightObject = new ArrayList<>();
        }
        mSkyFlightObject.add(skyFlightObject);
    }

    public void clearSkyFlightObj(){
        if(mSkyFlightObject != null){
            mSkyFlightObject.clear();
        }
    }

    // *********************************************************************

    // Below: Temporary Leg Object

    // *********************************************************************


    public ArrayList<LegObject> mlegObj;

    public ArrayList<LegObject> getLegObj() {
        if(mlegObj == null){
            mlegObj = new ArrayList<>();
        }
        return mlegObj;
    }

    public void addlegObj(LegObject legObj) {
        if(mlegObj == null){
            mlegObj = new ArrayList<>();
        }
        mlegObj.add(legObj);
    }

    public void clearLegObj() {
        if(mlegObj != null && mlegObj.size() != 0){
            mlegObj.clear();
        }
    }




// *********************************************************************

    // Below: Local Photos Object

    // *********************************************************************

    public void cityCountryValues(String city, String country){
        mCity = city;
        mCountry = country;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public List<ImageGalleryObj> getGalleryObject() {
        if(mImageGalleryArray == null) {
            mImageGalleryArray = new ArrayList<>();
            return mImageGalleryArray;
        }
        else{
            return mImageGalleryArray;
        }
    }

    public void addGalleryObject(ImageGalleryObj imageGalleryObj) {
        if(mImageGalleryArray == null){
            mImageGalleryArray = new ArrayList<>();
        }
        else {
            mImageGalleryArray.add(imageGalleryObj);
        }
    }

    public void clearGalleryObject() {
        if(mImageGalleryArray != null) {
            mImageGalleryArray.clear();
        }
    }

    // *********************************************************************

    // Below: Wikipedia Object

    // *********************************************************************

    public void setmWikiObject(WikiObject object){
        mWikiObject = object;
    }

    public WikiObject getmWikiObject(){
        return mWikiObject;
    }

    public void clearWikiObject() {
        if(mWikiObject != null) {
            mWikiObject.setmBulrb1("");
            mWikiObject.setmBlurb2("");
        }
    }

    // *********************************************************************

    // Below: Main Page Persistence Object

    // *********************************************************************

    public MainPageObject getmMainPageObject() {
        if (mMainPageObject == null){
            mMainPageObject = new MainPageObject("","","","","","","",0.00,"","","","","","","");
        }
        return mMainPageObject;
    }


    // *********************************************************************

    // Below: Things ToDo Object

    // *********************************************************************

    public List<ThingsToDoObject> getThingsToDo() {return mThingsToDoList;}

    public void addToToDoList(ThingsToDoObject object) {
        if(mThingsToDoList == null){
            mThingsToDoList = new ArrayList<ThingsToDoObject>();
        }
        mThingsToDoList.add(object);
    }

    public void removeAllThingsToDo() {
        if(mThingsToDoList != null) {
            mThingsToDoList.clear();
        }
    }

    // *********************************************************************

    // Below: Origin Airport List

    // *********************************************************************

    public ArrayList<AirportObject> getAirportList() {
        return mAirportList;
    }

    public void addAirportToList(AirportObject arrayList) {
        if(mAirportList == null){
            mAirportList = new ArrayList<>();
        }
        mAirportList.add(arrayList);
        Log.i(TAG, "addAirportToList: " + mAirportList.get(mAirportList.size()-1));
    }

    public void clearAirportList() {
        if(mAirportList != null){
            mAirportList.clear();
            Log.i(TAG, "clearAirportList: cleared");
        }
    }

    // *********************************************************************

    // Below: Initial Values Landing Page

    // *********************************************************************

    public void setmInitialValuesObj(InitialValuesObj initialValuesObjs) {
        if(mInitialValuesObj == null){
            mInitialValuesObj = new InitialValuesObj();
        }
        else {}
        mInitialValuesObj = initialValuesObjs;
    }

    public InitialValuesObj getmInitialValuesObj() {
        if(mInitialValuesObj == null){
            mInitialValuesObj = new InitialValuesObj();
        }
        return mInitialValuesObj;
    }


    // *********************************************************************

    // Below: Booking Objects Skyscanner

    // *********************************************************************

//    public List<BookingObj> getAllBookingObjs() {
//        return mBookingObject;
//    }
//
//    public void addBookingObjToList(BookingObj bookingObj) {
//        if(mBookingObject == null){
//            mBookingObject = new ArrayList<>();
//        }
//        mBookingObject.add(bookingObj);
//    }
//
//    public void clearBookingObjects(){
//        if(mBookingObject == null){
//            mBookingObject = new ArrayList<>();
//        }
//        if(mBookingObject != null){
//            mBookingObject.clear();
//        }
//    }

}
