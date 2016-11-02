package com.ander.quicktrip;

/**
 * Created by ander on 10/1/2016.
 */
public class ThingsToDoObject {

    public String mSiteName, mFormatted_address, mPhotoReference, mHtml, mOpen_now;

    public ThingsToDoObject(String siteName, String formatted_address, String photoReference, String html_attribution, String open_now) {
        mSiteName = siteName;
        mFormatted_address = formatted_address;
        mPhotoReference = photoReference;
        mHtml = html_attribution;
        mOpen_now = open_now;
    }

    public String getmSiteName() {
        return mSiteName;
    }

    public void setmSiteName(String mSiteName) {
        this.mSiteName = mSiteName;
    }

    public String getmFormatted_address() {
        return mFormatted_address;
    }

    public void setmFormatted_address(String mFormatted_address) {
        this.mFormatted_address = mFormatted_address;
    }

    public String getmPhotoReference() {
        return mPhotoReference;
    }

    public void setmPhotoReference(String mPhotoReference) {
        this.mPhotoReference = mPhotoReference;
    }

    public String getmHtml() {
        return mHtml;
    }

    public void setmHtml(String mHtml) {
        this.mHtml = mHtml;
    }

    public String getmOpen_now() {
        return mOpen_now;
    }

    public void setmOpen_now(String mOpen_now) {
        this.mOpen_now = mOpen_now;
    }
}