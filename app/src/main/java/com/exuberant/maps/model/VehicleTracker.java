package com.exuberant.maps.model;


import com.google.android.gms.maps.model.LatLng;

public class VehicleTracker {
    private Vehicle vehicle;
    private LatLng location;

    public VehicleTracker(Vehicle vehicle, LatLng location) {
        this.vehicle = vehicle;
        this.location = location;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
