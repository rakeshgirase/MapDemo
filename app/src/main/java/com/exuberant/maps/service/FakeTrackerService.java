package com.exuberant.maps.service;

import com.exuberant.maps.model.User;
import com.exuberant.maps.model.Vehicle;
import com.exuberant.maps.service.persistence.PersistenceService;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by rakesh on 10-Jun-2017.
 */

public class FakeTrackerService implements TrackerService {

    private PersistenceService persistenceService;

    public FakeTrackerService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Override
    public Set<Vehicle> getVehicles(User user) {
        return persistenceService.getVehicles(user);
    }

    @Override
    public LatLng getLocation(User user, Vehicle vehicle) {
        return persistenceService.getLocation(user, vehicle);
    }

    @Override
    public List<LatLng> getPathForDuration(User user, Vehicle vehicle, Date startDate, Date endDate) {
        return null;
    }
}
