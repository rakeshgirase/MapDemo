package com.exuberant.maps;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by rakesh on 27-May-2017.
 */

public class GPSTracker extends Service implements LocationListener{

    private final Context context;

    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean canGetLocation = false;

    private Location location;
    protected LocationManager locationManager;
    public GPSTracker(Context context) {
        this.context = context;
    }


    public LatLng getLocation() {
        try {

            System.err.println("Hello!!");
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);
            System.err.println("isGPSEnabled: " + isGPSEnabled);
            System.err.println("isNetworkEnabled: " + isNetworkEnabled);
            if(hasLocationAccess()){
                if(isGPSEnabled){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,10, this);
                        if(locationManager!=null){
                            System.err.println("Fetching from GPS!!!");
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                }
                //If Location is not found thenn fetch it from network
                if(location==null && isNetworkEnabled){
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,10, this);
                        if(locationManager!=null){
                            System.err.println("Fetching from Network!!!");
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    private boolean hasLocationAccess() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
