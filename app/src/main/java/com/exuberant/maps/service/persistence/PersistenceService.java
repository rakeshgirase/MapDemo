package com.exuberant.maps.service.persistence;

import com.exuberant.maps.model.User;
import com.exuberant.maps.model.Vehicle;
import com.exuberant.maps.model.VehicleTracker;
import com.google.android.gms.maps.model.LatLng;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by rakesh on 10-Jun-2017.
 */

public interface PersistenceService {
    Set<Vehicle> getVehicles(User user);

    VehicleTracker trackVehicle(User user, Vehicle vehicle);

    Collection<VehicleTracker> trackVehicles(User user);
}
