package com.ander.quicktrip;


public class AirportObject {

    String mCode, mName, mCity, mCountry;

    public AirportObject(String code, String name, String city, String country) {
        mCode = code;
        mName = name;
        mCity = city;
        mCountry = country;
    }

    public String getmCode() {
        return mCode;
    }

    public String getmName() {
        return mName;
    }

    public String getmCity() {return mCity;}

    public String getmCountry() {
        return mCountry;
    }
}
