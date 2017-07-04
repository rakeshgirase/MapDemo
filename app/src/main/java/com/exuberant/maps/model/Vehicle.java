package com.exuberant.maps.model;

/**
 * Created by rakesh on 10-Jun-2017.
 */

public class Vehicle {

    private String number;

    public Vehicle(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }
}
