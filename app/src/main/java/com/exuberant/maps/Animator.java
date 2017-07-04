package com.exuberant.maps;

import android.location.Location;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class Animator implements Runnable {
    private static final int ANIMATE_SPEEED = 2000;
    private static final int ANIMATE_SPEEED_TURN = 2000;
    private static final int BEARING_OFFSET = 20;
    private final Interpolator interpolator = new LinearInterpolator();
    int currentIndex = 0;
    float tilt = 90;
    float zoom = 15.5f;
    boolean upward = true;
    long start = SystemClock.uptimeMillis();
    LatLng endLatLng = null;
    LatLng beginLatLng = null;
    boolean showPolyline = false;
    private Marker trackingMarker;
    private List<Marker> markers;
    private GoogleMap mGoogleMap;
    private Handler mHandler = new Handler();
    private Marker selectedMarker;

    public Animator(GoogleMap mGoogleMap, List<Marker> markers) {
        this.mGoogleMap = mGoogleMap;
        this.markers = markers;
    }

    public void reset() {
        resetMarkers();
        start = SystemClock.uptimeMillis();
        currentIndex = 0;
        endLatLng = getEndLatLng();
        beginLatLng = getBeginLatLng();

    }

    private void resetMarkers() {
        for (Marker marker : markers) {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }
    }

    public void stop() {
        trackingMarker.remove();
        mHandler.removeCallbacks(this);
    }

    private void highLightMarker(int index) {
        highLightMarker(markers.get(index));
    }

    private void highLightMarker(Marker marker) {
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        marker.showInfoWindow();
        selectedMarker = marker;
    }

    public void initialize(boolean showPolyLine) {
        reset();
        this.showPolyline = showPolyLine;
        highLightMarker(0);
        if (showPolyLine) {
            polyLine = initializePolyLine();
        }
        // We first need to put the camera in the correct position for the first run (we need 2 markers for this).....
        LatLng markerPos = markers.get(0).getPosition();
        LatLng secondPos = markers.get(1).getPosition();
        setupCameraPositionForMovement(markerPos, secondPos);
    }

    private void setupCameraPositionForMovement(LatLng markerPos, LatLng secondPos) {
        float bearing = bearingBetweenLatLngs(markerPos, secondPos);
        trackingMarker = mGoogleMap.addMarker(new MarkerOptions().position(markerPos)
                .title("title")
                .snippet("snippet"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(markerPos)
                .bearing(bearing + BEARING_OFFSET)
                .tilt(90)
                .zoom(mGoogleMap.getCameraPosition().zoom >= 16 ? mGoogleMap.getCameraPosition().zoom : 16)
                .build();

        mGoogleMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition),
                ANIMATE_SPEEED_TURN,
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        System.out.println("finished camera");
                        Log.e("animator before reset", this + "");
                        reset();
                        Log.e("animator after reset", this + "");
                        Handler handler = new Handler();
                        post(handler);
                        //handler.post();
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("cancelling camera");
                    }
                });
    }

    private void post(Handler handler) {
        handler.post(this);
    }

    public void navigateToPoint(LatLng latLng, boolean animate) {
        CameraPosition position = new CameraPosition.Builder().target(latLng).build();
        changeCameraPosition(position, animate);
    }

    private void changeCameraPosition(CameraPosition cameraPosition, boolean animate) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        if (animate) {
            mGoogleMap.animateCamera(cameraUpdate);
        } else {
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }

    private Location convertLatLngToLocation(LatLng latLng) {
        Location loc = new Location("someLoc");
        loc.setLatitude(latLng.latitude);
        loc.setLongitude(latLng.longitude);
        return loc;
    }

    private float bearingBetweenLatLngs(LatLng begin, LatLng end) {
        Location beginL = convertLatLngToLocation(begin);
        Location endL = convertLatLngToLocation(end);
        return beginL.bearingTo(endL);
    }

    public void toggleStyle() {
        if (GoogleMap.MAP_TYPE_NORMAL == mGoogleMap.getMapType()) {
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    private Polyline polyLine;
    private PolylineOptions rectOptions = new PolylineOptions();

    private Polyline initializePolyLine() {
        rectOptions.add(markers.get(0).getPosition());
        return mGoogleMap.addPolyline(rectOptions);
    }

    /**
     * Add the marker to the polyline.
     */
    private void updatePolyLine(LatLng latLng) {
        List<LatLng> points = polyLine.getPoints();
        points.add(latLng);
        polyLine.setPoints(points);
    }

    public void stopAnimation() {
        this.stop();
    }

    public void startAnimation(boolean showPolyLine) {
        if (markers.size() > 2) {
            this.initialize(showPolyLine);
        }
    }

    @Override
    public void run() {
        long elapsed = SystemClock.uptimeMillis() - start;
        double t = interpolator.getInterpolation((float) elapsed / ANIMATE_SPEEED);
        Log.w("interpolator", t + "");
        double lat = t * endLatLng.latitude + (1 - t) * beginLatLng.latitude;
        double lng = t * endLatLng.longitude + (1 - t) * beginLatLng.longitude;
        Log.w("lat. lng", lat + "," + lng + "");
        LatLng newPosition = new LatLng(lat, lng);
        Log.w("newPosition", newPosition + "");

        trackingMarker.setPosition(newPosition);
        if (showPolyline) {
            updatePolyLine(newPosition);
        }

        // It's not possible to move the marker + center it through a cameraposition update while another camerapostioning was already happening.
        //navigateToPoint(newPosition,tilt,bearing,currentZoom,false);
        //navigateToPoint(newPosition,false);

        if (t < 1) {
            mHandler.postDelayed(this, 16);
        } else {
            System.out.println("Move to next marker.... current = " + currentIndex + " and size = " + markers.size());
            // imagine 5 elements -  0|1|2|3|4 currentindex must be smaller than 4
            if (currentIndex < markers.size() - 2) {
                currentIndex++;
                endLatLng = getEndLatLng();
                beginLatLng = getBeginLatLng();
                start = SystemClock.uptimeMillis();
                LatLng begin = getBeginLatLng();
                LatLng end = getEndLatLng();
                float bearingL = bearingBetweenLatLngs(begin, end);
                highLightMarker(currentIndex);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(end) // changed this...
                        .bearing(bearingL + BEARING_OFFSET)
                        .tilt(tilt)
                        .zoom(mGoogleMap.getCameraPosition().zoom)
                        .build();
                mGoogleMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(cameraPosition),
                        ANIMATE_SPEEED_TURN,
                        null
                );
                start = SystemClock.uptimeMillis();
                mHandler.postDelayed(this, 16);
            } else {
                currentIndex++;
                highLightMarker(currentIndex);
                stopAnimation();
            }
        }
    }

    private LatLng getEndLatLng() {
        return markers.get(currentIndex + 1).getPosition();
    }

    private LatLng getBeginLatLng() {
        return markers.get(currentIndex).getPosition();
    }

    private void adjustCameraPosition() {
        if (upward) {
            if (tilt < 90) {
                tilt++;
                zoom -= 0.01f;
            } else {
                upward = false;
            }
        } else {
            if (tilt > 0) {
                tilt--;
                zoom += 0.01f;
            } else {
                upward = true;
            }
        }
    }
}