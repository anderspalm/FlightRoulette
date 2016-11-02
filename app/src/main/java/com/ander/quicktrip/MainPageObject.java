package com.ander.quicktrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anders on 10/6/2016.
 */
public class MainPageObject {

    String mOutboundAirport, mInboundAirport, mOrigin, mDestination, mRegion1;
    String mOutCityCountry, mInCityCountry, mDestinationLocation, mDestinationCountry;
    String mInDate, mOutDate;
    String mCabin, mlocationPrice;
    String mUrl;
    double mMaxPrice;

    public MainPageObject(String mOutboundAirport, String mInboundAirport, String mOrigin,
                          String mDestination, String mRegion1, String mOutCityCountry,
                          String mInCityCountry, double mMaxPrice, String mDestinationLocation,
                          String mDestinationCountry, String mInDate, String mOutDate,
                          String mCabin, String mlocationPrice, String url) {
        this.mOutboundAirport = mOutboundAirport;
        this.mInboundAirport = mInboundAirport;
        this.mOrigin = mOrigin;
        this.mDestination = mDestination;
        this.mRegion1 = mRegion1;
        this.mOutCityCountry = mOutCityCountry;
        this.mInCityCountry = mInCityCountry;
        this.mMaxPrice = mMaxPrice;
        this.mDestinationLocation = mDestinationLocation;
        this.mDestinationCountry = mDestinationCountry;
        this.mInDate = mInDate;
        this.mOutDate = mOutDate;
        this.mCabin = mCabin;
        this.mlocationPrice = mlocationPrice;
        mUrl = url;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmOutboundAirport() {
        return mOutboundAirport;
    }

    public void setmOutboundAirport(String mOutboundAirport) {
        this.mOutboundAirport = mOutboundAirport;
    }

    public String getmInboundAirport() {
        return mInboundAirport;
    }

    public void setmInboundAirport(String mInboundAirport) {
        this.mInboundAirport = mInboundAirport;
    }

    public String getmOrigin() {
        return mOrigin;
    }

    public void setmOrigin(String mOrigin) {
        this.mOrigin = mOrigin;
    }

    public String getmDestination() {
        return mDestination;
    }

    public void setmDestination(String mDestination) {
        this.mDestination = mDestination;
    }

    public String getmRegion1() {
        return mRegion1;
    }

    public void setmRegion1(String mRegion1) {
        this.mRegion1 = mRegion1;
    }

    public String getmOutCityCountry() {
        return mOutCityCountry;
    }

    public void setmOutCityCountry(String mOutCityCountry) {
        this.mOutCityCountry = mOutCityCountry;
    }

    public String getmInCityCountry() {
        return mInCityCountry;
    }

    public void setmInCityCountry(String mInCityCountry) {
        this.mInCityCountry = mInCityCountry;
    }

    public double getmMaxPrice() {
        return mMaxPrice;
    }

    public void setmMaxPrice(double mMaxPrice) {
        this.mMaxPrice = mMaxPrice;
    }

    public String getmDestinationLocation() {
        return mDestinationLocation;
    }

    public void setmDestinationLocation(String mDestinationLocation) {
        this.mDestinationLocation = mDestinationLocation;
    }

    public String getmDestinationCountry() {
        return mDestinationCountry;
    }

    public void setmDestinationCountry(String mDestinationCountry) {
        this.mDestinationCountry = mDestinationCountry;
    }

    public String getmInDate() {
        return mInDate;
    }

    public void setmInDate(String mInDate) {
        this.mInDate = mInDate;
    }

    public String getmOutDate() {
        return mOutDate;
    }

    public void setmOutDate(String mOutDate) {
        this.mOutDate = mOutDate;
    }

    public String getmCabin() {
        return mCabin;
    }

    public void setmCabin(String mCabin) {
        this.mCabin = mCabin;
    }

    public String getMlocationPrice() {
        return mlocationPrice;
    }

    public void setMlocationPrice(String mlocationPrice) {
        this.mlocationPrice = mlocationPrice;
    }
}