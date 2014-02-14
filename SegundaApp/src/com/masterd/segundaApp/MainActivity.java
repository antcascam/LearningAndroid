package com.masterd.segundaApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends Activity {
	protected static final int CODIGO_PETICION = 100;
	protected static final int CODIGO_PETICION2 = 200;
	protected static final int CODIGO_PETICION3 = 300;
	protected static final int CODIGO_PETICION4 = 400;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d("Cambio de Estado", "La aplicación ha iniciado correctamente");
	// Buscamos los componentes de boton
		Button boton1 = (Button)
		findViewById(R.id.boton1);
		Button boton2 = (Button)
		findViewById(R.id.boton2);
		Button boton3 = (Button)
		findViewById(R.id.boton3);
		Button boton4 = (Button)
		findViewById(R.id.boton4);
		Button boton5 = (Button)
		findViewById(R.id.boton5);
	// El boton1 al pulsarse abrirá una actividad
		boton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), OtraActivity.class);
				startActivity(intent);
			}
		});
		// El boton2 al pulsarse pasará parámetros a otra actividad
		// para que nos los vuelva modificados
		boton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),OtraParametrosActivity.class);
				// Pasamos un parámetro
				intent.putExtra("parametro1", "esto es un parámetro");
				// Llamamos a la nueva actividad indicando que esperamosque nos
				// devuelva un valor
				// asociado a la constante CODIGO_RETORNO
				startActivityForResult(intent, CODIGO_PETICION);
			}
		});
		// El boton3 al pulsarse sumará 2 números
				boton3.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(),SumaActivity.class);
						// Pasamos un parámetro
						intent.putExtra("parametro2", 5);
						// Pasamos otro parámetro
						intent.putExtra("parametro3", 0);
						// Llamamos a la nueva actividad indicando que esperamosque nos
						// devuelva un valor
						// asociado a la constante CODIGO_RETORNO
						startActivityForResult(intent, CODIGO_PETICION2);
					}
				});
				// El boton4 al pulsarse sumará 2 números
				boton4.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), OtraActivityLog.class);
						startActivityForResult(intent, CODIGO_PETICION3);
						Log.d("Cambio de Estado", "Pulsado Boton Log 1");
					}
				});
				// El boton5 al pulsarse sumará 2 números
				boton5.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(),SumaActivityLog.class);
						// Pasamos un parámetro
						intent.putExtra("parametro2", 5);
						// Pasamos otro parámetro
						intent.putExtra("parametro3", 0);
						// Llamamos a la nueva actividad indicando que esperamosque nos
						// devuelva un valor
						// asociado a la constante CODIGO_RETORNO
						startActivityForResult(intent, CODIGO_PETICION4);
						Log.d("Cambio de Estado", "Pulsado Boton Log 2");
					}
				});
	}

	/**
	 * Este método será llamado cuando recibamos la respuesta de la actividad
	 * llamada
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CODIGO_PETICION) {
			// Extraemos el resultado
			String resultado = data.getStringExtra("resultado");
			// Mostramos un mensaje de usuario
			Toast toast = Toast.makeText(getApplicationContext(),"Valor devuelto: " + resultado, Toast.LENGTH_LONG);
			toast.show();
		}
		if (requestCode == CODIGO_PETICION2) {
			// Extraemos el resultado
			String resultado = data.getStringExtra("resultado");
			// Mostramos un mensaje de usuario
			Toast toast = Toast.makeText(getApplicationContext(),"Resultado: " + resultado, Toast.LENGTH_LONG);
			toast.show();
		}
		if (requestCode == CODIGO_PETICION3) {
			// Mostramos un mensaje de usuario
			Toast toast = Toast.makeText(getApplicationContext(),"Finalizado", Toast.LENGTH_LONG);
			toast.show();
			Log.d("Cambio de Estado", "Finalizada la Primera Activity");
		}
		if (requestCode == CODIGO_PETICION4) {
			// Extraemos el resultado
			String resultado = data.getStringExtra("resultado");
			// Mostramos un mensaje de usuario
			Toast toast = Toast.makeText(getApplicationContext(),"Resultado: " + resultado, Toast.LENGTH_LONG);
			toast.show();
			Log.d("Cambio de Estado", "Finalizada la Segunda Activity");
		}
	}
}