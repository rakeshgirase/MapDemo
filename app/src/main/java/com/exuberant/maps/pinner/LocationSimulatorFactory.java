package com.exuberant.maps.pinner;

public class LocationSimulatorFactory {

    public static LocationSimulator getSimulator() {
        return new FakeLocationSimulator();
    }
}
