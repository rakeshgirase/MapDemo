package com.exuberant.maps.service.persistence;

import com.exuberant.maps.lib.MultiMap;
import com.exuberant.maps.model.User;
import com.exuberant.maps.model.Vehicle;
import com.exuberant.maps.pinner.LocationSimulator;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rakesh on 10-Jun-2017.
 */

public class CachePersistenceService implements PersistenceService {

    MultiMap<User, Vehicle> userVehicles = new MultiMap<>();
    LocationSimulator locationSimulator;

    public CachePersistenceService(LocationSimulator locationSimulator) {
        this();
        this.locationSimulator = locationSimulator;
    }

    private CachePersistenceService() {
        User user1 = new User("Rakesh");
        Set<Vehicle> vehicles = new HashSet<>();
        vehicles.add(new Vehicle("MH05CM749"));
        vehicles.add(new Vehicle("MH05CM650"));
        vehicles.add(new Vehicle("MH05CM123"));
        vehicles.add(new Vehicle("GA09CM456"));
        this.userVehicles = new MultiMap<>();
        userVehicles.add(user1, vehicles);
    }

    @Override
    public Set<Vehicle> getVehicles(User user) {
        return userVehicles.get(user);
    }

    @Override
    public LatLng getLocation(User user, Vehicle vehicle) {
        return locationSimulator.locate();
    }
}
