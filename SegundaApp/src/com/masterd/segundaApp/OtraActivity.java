package com.masterd.segundaApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class OtraActivity extends Activity {
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
		// cuando se pulse se cerrará para volver a la anterior
		boton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}