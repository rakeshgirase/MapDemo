package com.exuberant.maps.service.persistence;

import com.exuberant.maps.model.User;
import com.exuberant.maps.model.Vehicle;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Set;

/**
 * Created by rakesh on 10-Jun-2017.
 */

public interface PersistenceService {
    Set<Vehicle> getVehicles(User user);

    LatLng getLocation(User user, Vehicle vehicle);
}
