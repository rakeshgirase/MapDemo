package com.exuberant.maps.model;

import com.exuberant.maps.mapdemo.R;

public class DashboardRow {

    private Long id;
    private String number;
    private String city;
    private String pic;
    private int picAsResource;

    public DashboardRow(Long id, String number, String city, String pic) {
        this.id = id;
        this.number = number;
        this.city = city;
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPicAsResource() {
        return R.mipmap.ic_launcher;
    }
}