package com.masterD.tema8;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapaActivity extends MapActivity {
	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private MiItemizedOverlay itemizedoverlay;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		// Configure the map
		mapa = (MapView) findViewById(R.id.mapa);
		mapa.displayZoomControls(true);
		mapa.setBuiltInZoomControls(true);
		mapController = mapa.getController();
		mapController.setZoom(14); // Zoom x14
		mapa.setSatellite(true); // Activamos la vista satelite
		
		// Marcamos unos puntos en el mapa
		mapOverlays = mapa.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
		itemizedoverlay = new MiItemizedOverlay(this, drawable);
		itemizedoverlay.addLocalizacion(41.669770, -0.812602, "Punto 1");
		itemizedoverlay.addLocalizacion(41.666597, -0.907145, "Punto 2");
		itemizedoverlay.addLocalizacion(41.658657, -0.886501, "Punto 3");
		mapOverlays.clear();
		mapOverlays.add(itemizedoverlay);

		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MiLocationListener mlistener = new MiLocationListener(mapa.getContext(),mapController);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlistener);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
