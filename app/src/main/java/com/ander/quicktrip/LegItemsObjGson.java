package com.ander.quicktrip;

import java.util.ArrayList;

/**
 * Created by anders on 10/21/2016.
 */
public class LegItemsObjGson {

    public ArrayList<LegClass> Legs;

    public ArrayList<LegClass> getLegs() {
        return Legs;
    }

    public class LegClass{

        public String Id;
        public String Departure;
        public String Arrival;
        public int Duration;
        public ArrayList<Integer> Stops;

        public String getId() {
            return Id;
        }

        public String getDeparture() {
            return Departure;
        }

        public String getArrival() {
            return Arrival;
        }

        public int getDuration() {
            return Duration;
        }

        public ArrayList<Integer> getStops() {
            return Stops;
        }
    }

}
