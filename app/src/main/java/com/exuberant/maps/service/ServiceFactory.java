package com.exuberant.maps.service;

import com.exuberant.maps.pinner.FakeLocationSimulator;
import com.exuberant.maps.pinner.LocationSimulator;
import com.exuberant.maps.service.persistence.CachePersistenceService;
import com.exuberant.maps.service.persistence.PersistenceService;

public class ServiceFactory {

    private static FakeTrackerService fakeTrackerService;
    private static CachePersistenceService cachePersistenceService;
    private static FakeLocationSimulator fakeLocationSimulator;
    private static DashboardService dashboardService;

    private ServiceFactory(){}

    public static TrackerService trackerService(){
        if(fakeTrackerService==null){
            fakeTrackerService = new FakeTrackerService(persistenceService());
        }
        return fakeTrackerService;
    }

    public static PersistenceService persistenceService() {
        if (cachePersistenceService==null) {
            cachePersistenceService = new CachePersistenceService(locationSimulator());
        }
        return cachePersistenceService;
    }

    public static LocationSimulator locationSimulator() {
        if (fakeLocationSimulator==null) {
            fakeLocationSimulator = new FakeLocationSimulator();
        }
        return fakeLocationSimulator;
    }

    public static DashboardService dashboardService(){
        if(dashboardService==null){
            dashboardService = new FakeDashboardService();
        }
        return dashboardService;
    }

}
