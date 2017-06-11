package com.exuberant.maps.service;

import com.exuberant.maps.model.User;
import com.exuberant.maps.model.Vehicle;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TrackerService {

    //For a given user give me all List of vehicles
    //For a given user give me status of vehicles
    //For a given user give me Vehicles location
    //For a given user give me the path for the specific period

    Set<Vehicle> getVehicles(User user);

    LatLng getLocation(User user, Vehicle vehicle);

    List<LatLng> getPathForDuration(User user, Vehicle vehicle, Date startDate, Date endDate);



}
