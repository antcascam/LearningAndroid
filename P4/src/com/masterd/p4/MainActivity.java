package com.masterd.p4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	// Buscamos los componentes de boton
		Button boton1 = (Button)
		findViewById(R.id.boton1);
		Button boton2 = (Button)
		findViewById(R.id.boton2);
	// El boton1 al pulsarse abrirá una actividad
		boton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Boton1Activity.class);
				startActivity(intent);
			}
		});
		// El boton2 al pulsarse abrirá otra actividad
		boton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Boton2Activity.class);
				startActivity(intent);
			}
		});

	}
}