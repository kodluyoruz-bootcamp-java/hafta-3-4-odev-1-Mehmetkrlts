package org.kodluyoruz.week3and4.hw;

import org.kodluyoruz.week3and4.hw.interfaces.IClock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;



public class City implements IClock, Comparable<City> , Runnable {

    private String name;
    private String cityCode;
    private String gmt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) &&
                Objects.equals(cityCode, city.cityCode) &&
                Objects.equals(gmt, city.gmt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityCode, gmt);
    }

    @Override
    public String toString() {
        return ("City Code: " + cityCode + " - " +
                "City Name: " + name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getGmt() {
        return gmt;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }

    public City(String name, String cityCode, String gmt) {
        this.name = name;
        this.cityCode = cityCode;
        this.gmt = gmt;
    }

    @Override
    public synchronized void showTime(String name , String gmt) {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone(gmt));

        for (; ; ) { // loop forever

            TimeZone time = TimeZone.getTimeZone(gmt);
            System.out.print(name + ": " + df.format(date) + "\r");
            break;

    }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showTime(name,gmt);
    }


    @Override
    public int compareTo(City city) {

        return this.getName().compareTo(city.getName());
    }

    @Override
    public void run() {

        showTime(getName(),getGmt());

    }

}




