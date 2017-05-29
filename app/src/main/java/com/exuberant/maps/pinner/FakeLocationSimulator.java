package com.exuberant.maps.pinner;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FakeLocationSimulator implements LocationSimulator {

    private final Map<City, LatLng> locations = new HashMap<>();
    private List<LatLng> address = new ArrayList<>();
    private Random random = new Random();
    private int count = 0;

    public FakeLocationSimulator() {
        locations.put(City.MUMBAI, new LatLng(19.0760, 72.8777));
        locations.put(City.LONDON, new LatLng(51.5074, 0.1278));
        locations.put(City.NEW_YORK, new LatLng(40.7128, 74.0059));
        address.addAll(locations.values());
    }

    enum City{
        MUMBAI, LONDON, NEW_YORK
    }

    @Override
    public LatLng locate() {
        if(count>2){
            count = 0;
        }
        return address.get(count++);
    }
}
