package com.exuberant.maps.service.persistence;

import com.exuberant.maps.exception.InvalidInputException;
import com.exuberant.maps.lib.MultiMap;
import com.exuberant.maps.model.User;
import com.exuberant.maps.model.Vehicle;
import com.exuberant.maps.model.VehicleTracker;
import com.exuberant.maps.pinner.LocationSimulator;
import com.google.android.gms.maps.model.LatLng;

import java.util.Collection;
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
        vehicles.add(new Vehicle("FIRST"));
        vehicles.add(new Vehicle("SECOND"));
        vehicles.add(new Vehicle("THIRD"));
        vehicles.add(new Vehicle("FOURTH"));
        this.userVehicles = new MultiMap<>();
        userVehicles.add(user1, vehicles);
    }

    @Override
    public Set<Vehicle> getVehicles(User user) {
        return userVehicles.get(user);
    }

    @Override
    public VehicleTracker trackVehicle(User user, Vehicle vehicle) {
        VehicleTracker tracker;
        if (getVehicles(user).contains(vehicle)) {
            LatLng location = locationSimulator.locate(vehicle);
            tracker = new VehicleTracker(vehicle, location);
        } else {
            throw new InvalidInputException("Register vehicle for the user!!!");
        }
        return tracker;
    }

    @Override
    public Collection<VehicleTracker> trackVehicles(final User user) {
        Collection<VehicleTracker> result = new HashSet<>();
        for (Vehicle vehicle : getVehicles(user)) {
            result.add(trackVehicle(user, vehicle));
        }
        return result;
    }
}
