package com.exuberant.maps.service;

import com.exuberant.maps.model.User;
import com.exuberant.maps.model.Vehicle;
import com.exuberant.maps.model.VehicleTracker;
import com.exuberant.maps.service.persistence.PersistenceService;
import com.google.android.gms.maps.model.LatLng;

import java.util.Collection;
import java.util.Date;

/**
 * Created by rakesh on 10-Jun-2017.
 */

public class FakeTrackerService implements TrackerService {

    private PersistenceService persistenceService;

    public FakeTrackerService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Override
    public Collection<Vehicle> getVehicles(User user) {
        return persistenceService.getVehicles(user);
    }

    @Override
    public VehicleTracker getLocation(User user, Vehicle vehicle) {
        return persistenceService.trackVehicle(user, vehicle);
    }

    @Override
    public Collection<VehicleTracker> trackVehicles(User user) {
        return persistenceService.trackVehicles(user);
    }

    @Override
    public Collection<LatLng> getPathForDuration(User user, Vehicle vehicle, Date startDate, Date endDate) {
        return null;
    }
}
