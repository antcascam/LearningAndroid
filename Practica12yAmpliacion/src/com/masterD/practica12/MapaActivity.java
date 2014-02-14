package com.masterD.practica12;


import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class MapaActivity extends MapActivity {
	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private MiItemizedOverlay marcaRoja;
	private MiItemizedOverlay marcaVerde;
	private MiItemizedOverlay marcaAzul;
	private LocationManager locManager;
	private LocationListener locListener;
	private Double minLAT;
	private Double maxLAT;
	private Double minLON;
	private Double maxLON;
	private int latp;
	private int lonp;
	
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
		
		//Asignamos y creamos los elementos
		Drawable drawableRed = this.getResources().getDrawable(R.drawable.markerred);
		Drawable drawableGreen = this.getResources().getDrawable(R.drawable.markergreen);
		Drawable drawableBlue = this.getResources().getDrawable(R.drawable.markerblue);
		marcaRoja = new MiItemizedOverlay(this, drawableRed);
		marcaVerde = new MiItemizedOverlay(this, drawableGreen);
		marcaAzul = new MiItemizedOverlay(this, drawableBlue);
		mapOverlays = mapa.getOverlays();
		
		//Coordenadas Plaza del Pilar
        latp = (int)(41.656389*1E6);
        lonp = (int)(-0.8786*1E6);
        minLAT = 41.65;
        maxLAT = 41.66;
        minLON = -0.87;
        maxLON = -0.88;
        
        //Colocamos un hito rojo en la plaza
        marcaRoja.addLocalizacion(latp, lonp, "Plaza del Pilar");
        mapOverlays.clear();
		mapOverlays.add(marcaRoja);
		
		//Obtenemos una referencia al LocationManager
    	locManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	
    	//Nos registramos para recibir actualizaciones de la posición
    	locListener = new LocationListener() {
	    	public void onLocationChanged(Location location) {
	    		probarPosicion(location);
	    	}
	    	public void onProviderDisabled(String provider){
	    		Toast.makeText(mapa.getContext(), "GPS desactivado", Toast.LENGTH_LONG).show();
	    	}
	    	public void onProviderEnabled(String provider){
	    		Toast.makeText(mapa.getContext(), "GPS activado", Toast.LENGTH_LONG).show();
	    	}
	    	public void onStatusChanged(String provider, int status, Bundle extras){}
    	};
    	locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
    }
     
    private void probarPosicion(Location loc) {
    	
    	Double lat = loc.getLatitude();     
        Double lon = loc.getLongitude();
    	int lati = (int) (lat * 1E6);
		int loni = (int) (lon * 1E6);
		Toast.makeText(mapa.getContext(), lati + "," + loni, Toast.LENGTH_LONG).show();
		GeoPoint miPunto = new GeoPoint(lati, loni);
		mapController.animateTo(miPunto);
		
		//Colocamos un hito azul en lugar
        marcaAzul.addLocalizacion(lati, loni, "He estado aquí");
        mapOverlays.clear();
		mapOverlays.add(marcaAzul);
		
    	if(loc != null && lat>=minLAT && lat<=maxLAT && lon<=minLON && lon>=maxLON){
    		//Colocamos un hito verde en la plaza
            marcaVerde.addLocalizacion(latp, lonp, "Plaza del Pilar");
    		mapOverlays.add(marcaVerde);
    	}else{
    		//Colocamos un hito rojo en la plaza
            marcaRoja.addLocalizacion(latp, lonp, "Plaza del Pilar");
    		mapOverlays.add(marcaRoja);
    	}
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}