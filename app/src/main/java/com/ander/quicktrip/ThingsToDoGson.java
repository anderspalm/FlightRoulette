package com.ander.quicktrip;

import java.util.ArrayList;

/**
 * Created by ander on 10/1/2016.
 */
public class ThingsToDoGson {

    public ArrayList<GoogleResults> results;

    public ArrayList<GoogleResults> getResults() {
        return results;
    }

    public class GoogleResults{

        public String name;
        public String formatted_address;
        public ArrayList<PhotoClass> photos;
        public HoursClass opening_hours;

        public ArrayList<PhotoClass> getPhoto() {
            return photos;
        }

        public HoursClass getOpening_class() {
            return opening_hours;
        }

        public String getmName() {
            return name;
        }

        public String getFormatted_address() {
            return formatted_address;
        }
    }

    public class PhotoClass{
        public String photo_reference;
        public String html_attribution;

        public String getPhoto_reference() {
            return photo_reference;
        }

        public String getHtml_attribution() {
            return html_attribution;
        }
    }

    public class HoursClass{
        public String open_now;

        public String getOpen_now() {
            return open_now;
        }
    }

}
