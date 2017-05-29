package com.exuberant.maps.mapdemo;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.exuberant.maps.GPSTracker;
import com.exuberant.maps.pinner.LocationSimulator;
import com.exuberant.maps.pinner.LocationSimulatorFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class FirstMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private GPSTracker gpsTracker;
    private Location mLocation;
    private double latitude, longitude;
    private ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    private LocationSimulator simulator = LocationSimulatorFactory.getSimulator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_maps);
        gpsTracker = new GPSTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();
        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("I am here!!!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        service.scheduleAtFixedRate(this::showOnMap, 2, 2, TimeUnit.SECONDS);
    }



    public void showOnMap(){
        LatLng location = simulator.locate();
        try{
            System.err.println("Locating: "  + location);
            mMap.addMarker(new MarkerOptions().position(location).title("Marker : " + location));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        }catch (Exception e){
            System.err.println("Got Exception locating: " + e.getMessage() + location.latitude + "," + location.longitude);
        }
    }
}
