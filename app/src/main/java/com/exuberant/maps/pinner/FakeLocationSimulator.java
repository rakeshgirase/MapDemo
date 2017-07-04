package com.exuberant.maps.pinner;

import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;

import com.exuberant.maps.model.Vehicle;
import com.exuberant.maps.service.ServiceFactory;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.R.attr.start;

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

    enum City {
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
        BigDecimal lat = new BigDecimal(getLattitude()).setScale(10, RoundingMode.DOWN);
        BigDecimal lng = new BigDecimal(getLongitude()).setScale(10, RoundingMode.DOWN);
        return new LatLng(lat.doubleValue(), lng.doubleValue());
    }

    public Double getLattitude() {
        return getRandomFromRange(9, 31);
    }

    public Double getLongitude() {
        return getRandomFromRange(74, 83);
    }

    public Double getRandomFromRange(double start, double end) {
        return start + (random.nextDouble() * (end - start));
    }

    private LatLng getIndianLocation() {
        LatLng location = null;
        do {
            location = getRandomLocation();
        } while (!isIndianLocation(location));
        return location;
    }

    private boolean isIndianLocation(LatLng location) {
        boolean isIndianLocation = false;
        if (ServiceFactory.isGeoCoderInitialized()) {
            Geocoder geocoder = ServiceFactory.geoCoder(null);
            try {
                List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 10);
                for (Address address : addresses) {
                    System.err.println("Address: " + address.getCountryCode());
                    System.err.println("Address: " + address);
                    if (address.getCountryCode().equalsIgnoreCase("IN")) {
                        isIndianLocation = true;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isIndianLocation;
    }
}
