package com.exuberant.maps.pinner;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rakesh on 26-May-2017.
 */
public class LocationSimulatorTest {
    @Test
    public void locate() throws Exception {
        LocationSimulator simulator = new FakeLocationSimulator();
        System.err.println("Loc:" + simulator.locate());
        System.err.println("Loc:" + simulator.locate());
        System.err.println("Loc:" + simulator.locate());
        System.err.println("Loc:" + simulator.locate());
    }

}