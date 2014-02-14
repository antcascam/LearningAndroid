package com.masterD.practica11;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class MapaActivity extends MapActivity {
	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private MiItemizedOverlay itemizedoverlay;
	private EditText texto;
	private Button boton;
	private Geocoder posicionador;
	private Address direccion;
	private List<Address> lista;
	
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
		texto = (EditText) findViewById(R.id.texto);
		boton = (Button) findViewById(R.id.boton);
		Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
		itemizedoverlay = new MiItemizedOverlay(this, drawable);
		mapOverlays = mapa.getOverlays();
		posicionador = new Geocoder(getBaseContext(),Locale.getDefault());
		
		//Creamos el listener
		boton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {				
				try {
					// Obtenemos la dirección
					lista = (List<Address>) posicionador.getFromLocationName(texto.getText().toString(), 5);
					
					// Añadimos la dirección al Mapa si se ha encontrado
					
					if (lista != null && lista.size() > 0) {

						// Obtenemos la dirección
						direccion = lista.get(0);

						//Obtenemos las coordenadas
						int lat = (int) (direccion.getLatitude() * 1E6);
						int lng = (int) (direccion.getLongitude() * 1E6);

						//Añadimos una Etiqueta
						itemizedoverlay.addLocalizacion(lat, lng,direccion.getLocality());
						mapOverlays.clear();
						mapOverlays.add(itemizedoverlay);
						
						//Geoposicionamos
						GeoPoint punto = new GeoPoint(lat, lng);
						mapController.setCenter(punto);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
