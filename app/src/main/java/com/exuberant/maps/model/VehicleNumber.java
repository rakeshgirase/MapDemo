package com.exuberant.maps.model;

/**
 * Created by rakesh on 10-Jun-2017.
 */

class VehicleNumber {

    private String regex = "[A-Z]{2}[0-9]]{2}";

    private String number;

    public VehicleNumber(String number) {
        this.number = number;
    }
}
