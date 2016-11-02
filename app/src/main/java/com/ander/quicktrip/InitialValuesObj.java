package com.ander.quicktrip;

/**
 * Created by ander on 9/2/2016.
 */
public class InitialValuesObj {

    double Price;
    String outboundDate;
    String inboundDate;
    String outAirportName;
    String country;
    String city;
    String outAirportCode;


    public InitialValuesObj(){
    }

    public InitialValuesObj(String outboundDate, String inboundDate) {
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
    }

    public InitialValuesObj(double price, String outboundDate, String inboundDate, String outAirportName, String country, String city, String outAirportCode) {
        Price = price;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.outAirportName = outAirportName;
        this.country = country;
        this.city = city;
        this.outAirportCode = outAirportCode;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public String getOutAirportName() {
        return outAirportName;
    }

    public void setOutAirportName(String outAirportName) {
        this.outAirportName = outAirportName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOutAirportCode() {
        return outAirportCode;
    }

    public void setOutAirportCode(String outAirportCode) {
        this.outAirportCode = outAirportCode;
    }
}
