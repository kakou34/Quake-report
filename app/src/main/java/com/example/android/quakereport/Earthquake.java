package com.example.android.quakereport;


import java.util.Date;

public class Earthquake {

    private double magnitude;
    private String place;
    private long time;
    private String url;

    public Earthquake(double mag, String place, long time, String url){
        this.magnitude=mag;
        this.place=place;
        this.time=time;
        this.url=url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }
}
