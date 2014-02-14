package com.turtledroid.ticu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class TiCuConfigActivity extends Activity {

	private CheckBox wifiBox;
	private CheckBox blueBox;
	private CheckBox datosBox;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuracion);

		// Asignamos botones
		wifiBox = (CheckBox) findViewById(R.id.wifi);
		blueBox = (CheckBox) findViewById(R.id.blue);
		datosBox = (CheckBox) findViewById(R.id.datos);

		// Necesitamos un editor de preferencias para editarlas
		settings = getPreferences(0);
		editor = settings.edit();

		// Activar autowifi
		wifiBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				cambiaEstado("wifi", isChecked);
			}

		});

		// Activar autobluetooth
		blueBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				cambiaEstado("bluetooth", isChecked);
			}

		});

		// Activar datos
		datosBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				cambiaEstado("datos", isChecked);
			}

		});
	}

	private void cambiaEstado(String key, boolean estado) {

		if (estado == true) {
			// Cambiamos el valor de la key
			editor.putBoolean(key, true);

		} else {
			// Cambiamos el valor de la key
			editor.putBoolean(key, false);
		}
		// Validamos los cambios
		editor.commit();
	}

}
