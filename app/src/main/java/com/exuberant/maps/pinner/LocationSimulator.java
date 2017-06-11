package com.exuberant.maps.pinner;

import com.exuberant.maps.model.Vehicle;
import com.google.android.gms.maps.model.LatLng;

public interface LocationSimulator {

    LatLng locate();

    LatLng locate(Vehicle vehicle);
}
