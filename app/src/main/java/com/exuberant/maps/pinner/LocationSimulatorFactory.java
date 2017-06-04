package com.exuberant.maps.pinner;

public class LocationSimulatorFactory {

    private static FakeLocationSimulator locationSimulator;

    public static LocationSimulator getSimulator() {
        if(locationSimulator==null){
            locationSimulator = new FakeLocationSimulator();
        }
        return locationSimulator;
    }
}
