package com.exuberant.maps.pinner;

import android.support.annotation.NonNull;

import com.exuberant.maps.model.Vehicle;
import com.google.android.gms.maps.model.LatLng;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FakeLocationSimulator implements LocationSimulator {

    private final Map<City, LatLng> locations = new HashMap<>();
    private List<LatLng> address = new ArrayList<>();
    private Random random = new Random();

    public FakeLocationSimulator() {
        locations.put(City.SURAT, new LatLng(25.1702, 72.8311));
        locations.put(City.MUMBAI, new LatLng(19.0760, 72.8777));
        locations.put(City.LONDON, new LatLng(52.5074, 0.1278));
        locations.put(City.NEW_YORK, new LatLng(46.7128, 74.0059));
        address.addAll(locations.values());
    }

    enum City{
        MUMBAI, LONDON, SURAT, NEW_YORK
    }

    @Override
    public LatLng locate() {
        return getRandomLocation();
    }

    @Override
    public LatLng locate(Vehicle vehicle) {
        return getRandomLocation();
    }

    @NonNull
    private LatLng getRandomLocation() {
        BigDecimal lat = new BigDecimal(Math.abs(random.nextDouble()) * 100).setScale(4, RoundingMode.DOWN);
        BigDecimal lng = new BigDecimal(Math.abs(random.nextDouble()) * 100).setScale(4, RoundingMode.DOWN);
        return new LatLng(lat.doubleValue(), lng.doubleValue());
    }
}
