package com.masterd.p4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Boton2Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// cargamos la vista
		setContentView(R.layout.menu2);
		// Localizamos los componentes de la vista
		TextView texto = (TextView) findViewById(R.id.menu2);
		Button boton = (Button) findViewById(R.id.boton3);
		// Cambiamos el texto del campo de texto
		texto.setText(R.string.menu2);
		// Cambiamos el texto del boton
		boton.setText(R.string.boton3);
		// Asignamos el comportamiento al boton:
		// cuando se pulse se cerrar� para volver a la anterior
		boton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}