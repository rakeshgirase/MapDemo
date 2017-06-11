package com.exuberant.maps.service;

import com.exuberant.maps.pinner.FakeLocationSimulator;
import com.exuberant.maps.pinner.LocationSimulator;
import com.exuberant.maps.service.persistence.CachePersistenceService;
import com.exuberant.maps.service.persistence.PersistenceService;

public class ServiceFactory {

    private ServiceFactory(){}

    public static TrackerService trackerService(){
        return new FakeTrackerService(persistenceService());
    }

    private static PersistenceService persistenceService() {
        return new CachePersistenceService(locationSimulator());
    }

    private static LocationSimulator locationSimulator() {
        return new FakeLocationSimulator();
    }

}
