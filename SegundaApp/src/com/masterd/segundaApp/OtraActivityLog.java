package com.masterd.segundaApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class OtraActivityLog extends Activity {
	private static final int CODIGO_RETORNO_OK = 1;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// cargamos la vista
		setContentView(R.layout.otra);
		// Localizamos los componentes de la vista
		TextView texto = (TextView) findViewById(R.id.texto);
		Button boton = (Button) findViewById(R.id.boton);
		// Cambiamos el texto del campo de texto
		texto.setText(R.string.otra);
		// Cambiamos el texto del boton
		boton.setText(R.string.salir);
		// Asignamos el comportamiento al boton:
		// cuando se pulse se cerrar� para volver a la anterior
		boton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Log.d("Cambio de Estado", "OtraActivityLog est� trabajando");
				devuelveParametros();
			}
		});
	}
	protected void devuelveParametros() {
		Intent data = new Intent();
		data.putExtra("resultado", "texto devuelto");
		setResult(CODIGO_RETORNO_OK, data);
		finish();
		Log.d("Cambio de Estado", "OtraActivityLog ha Finalizado");
	}
}