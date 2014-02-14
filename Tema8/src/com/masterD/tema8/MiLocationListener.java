package com.masterD.tema8;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MiLocationListener implements LocationListener {
	private Context context;
	private MapController mapController;

	public MiLocationListener(Context context, MapController mapController) {
		this.context = context;
		this.mapController = mapController;
	}

	public void onLocationChanged(Location location) {
		int lat = (int) (location.getLatitude() * 1E6);
		int lon = (int) (location.getLongitude() * 1E6);
		Toast.makeText(context, lat + " " + lon, Toast.LENGTH_LONG).show();
		GeoPoint miPunto = new GeoPoint(lat, lon);
		mapController.animateTo(miPunto);
	}

	public void onProviderDisabled(String provider) {
		Toast.makeText(context, "GPS desactivado", Toast.LENGTH_LONG).show();
	}

	public void onProviderEnabled(String provider) {
		Toast.makeText(context, "GPS activado", Toast.LENGTH_LONG).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
}
