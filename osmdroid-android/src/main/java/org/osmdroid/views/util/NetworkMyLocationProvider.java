package org.osmdroid.views.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import org.osmdroid.util.CoordinateConverter;
import org.osmdroid.views.overlay.mylocation.IMyLocationConsumer;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 高信朋 on 2018/1/18.
 */
@SuppressLint("MissingPermission")
public class NetworkMyLocationProvider implements IMyLocationProvider, LocationListener {
    private LocationManager mLocationManager;
    private Location mLocation;
    private Context context;

    private IMyLocationConsumer mMyLocationConsumer;
    private long mLocationUpdateMinTime = 0;
    private float mLocationUpdateMinDistance = 0.0f;
    private final Set<String> locationSources = new HashSet<>();

    private MyLocationNewOverlay.OnLocationChangedListener locationChangedListener;


    public NetworkMyLocationProvider(Activity context) {
        this.context = context;
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> list = mLocationManager.getProviders(true);
        if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            locationSources.add(LocationManager.NETWORK_PROVIDER);
        } else if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            locationSources.add(LocationManager.GPS_PROVIDER);
        } else if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
            // 返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivityForResult(intent, 0);
            context.finish();
        }
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * removes all sources, again, only useful before startLocationProvider is called
     */
    public void clearLocationSources() {
        locationSources.clear();
    }

    /**
     * adds a new source to listen for location data. Has no effect after startLocationProvider has been called
     * unless startLocationProvider is called again
     */
    public void addLocationSource(String source) {
        locationSources.add(source);
    }

    /**
     * returns the live list of NETWORK sources that we accept, changing this list after startLocationProvider
     * has no effect unless startLocationProvider is called again
     *
     * @return
     */
    public Set<String> getLocationSources() {
        return locationSources;
    }

    public long getLocationUpdateMinTime() {
        return mLocationUpdateMinTime;
    }

    /**
     * Set the minimum interval for location updates. See
     * {@link LocationManager#requestLocationUpdates(String, long, float, LocationListener)}. Note
     * that you should call this before calling {@link MyLocationNewOverlay#enableMyLocation()}.
     *
     * @param milliSeconds
     */
    public void setLocationUpdateMinTime(final long milliSeconds) {
        mLocationUpdateMinTime = milliSeconds;
    }

    public float getLocationUpdateMinDistance() {
        return mLocationUpdateMinDistance;
    }

    /**
     * Set the minimum distance for location updates. See
     * {@link LocationManager#requestLocationUpdates(String, long, float, LocationListener)}. Note
     * that you should call this before calling {@link MyLocationNewOverlay#enableMyLocation()}.
     *
     * @param meters
     */
    public void setLocationUpdateMinDistance(final float meters) {
        mLocationUpdateMinDistance = meters;
    }

    //
    // IMyLocationProvider
    //

    /**
     * Enable location updates and show your current location on the map. By default this will
     * request location updates as frequently as possible, but you can change the frequency and/or
     * distance by calling {@link #setLocationUpdateMinTime} and/or {@link
     * #setLocationUpdateMinDistance} before calling this method.
     */
    @Override
    public boolean startLocationProvider(IMyLocationConsumer myLocationConsumer) {
        mMyLocationConsumer = myLocationConsumer;
        boolean result = false;
        for (final String provider : mLocationManager.getProviders(true)) {
            if (locationSources.contains(provider)) {
                result = true;
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return false;
                }
                mLocationManager.requestLocationUpdates(provider, mLocationUpdateMinTime,
                        mLocationUpdateMinDistance, this);
            }
        }
        return result;
    }

    @Override
    public void stopLocationProvider() {
        mMyLocationConsumer = null;
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
    }

    @Override
    public Location getLastKnownLocation() {
        return mLocation;
    }

    @Override
    public void destroy() {
        stopLocationProvider();
        mLocation = null;
        mLocationManager = null;
        mMyLocationConsumer = null;
    }

    //
    // LocationListener
    //

    @Override
    public void onLocationChanged(final Location location) {
        Log.e(TAG, "onLocationChanged: 位置改变：location=" + location);
        double[] lat_lng =
                CoordinateConverter.transformWgs2Gcj(location.getLatitude(), location.getLongitude());
        location.setLatitude(lat_lng[0]);
        location.setLongitude(lat_lng[1]);

        if (locationChangedListener != null) {
            if (locationChangedListener.getEnable()) {
                locationChangedListener.onLocationChanged(location);
            }
        }
        if (location == null || location.getProvider() == null)
            return;
        mLocation = location;
        if (mMyLocationConsumer != null && mLocation != null)
            mMyLocationConsumer.onLocationChanged(mLocation, this);
    }

    private String TAG = "bril";

    /**
     * Called when the location is closed.
     *
     * @param provider
     */
    @Override
    public void onProviderDisabled(final String provider) {
        Log.e(TAG, "onProviderDisabled: provider=" + provider);
    }

    /**
     * Called when the location is available.
     *
     * @param provider
     */
    @Override
    public void onProviderEnabled(final String provider) {
        Log.e(TAG, "onProviderEnabled: provider=" + provider);
    }

    /**
     * Called when the positioning state changes.
     *
     * @param provider
     * @param status
     * @param extras
     */
    @Override
    public void onStatusChanged(final String provider, final int status, final Bundle extras) {
        Log.e(TAG, "onStatusChanged: 定位状态改变 provider = " + provider + "\nstatus=" + status);
    }

    @Override
    public void setOnLocationChangedListener(MyLocationNewOverlay.OnLocationChangedListener listener) {
        this.locationChangedListener = listener;
    }


}
