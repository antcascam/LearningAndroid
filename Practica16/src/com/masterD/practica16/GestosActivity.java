package com.masterD.practica16;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class GestosActivity extends Activity implements OnGesturePerformedListener {

	private GestureOverlayView gestureView;
	private EditText nombreGesto;
	private GestureLibrary gestLib;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		gestureView = (GestureOverlayView) findViewById(R.id.gestures);
		nombreGesto = (EditText) findViewById(R.id.nombreGesto);
		gestLib = GestureLibraries.fromRawResource(this, R.raw.gestures);

		if (!gestLib.load()) {
			Toast.makeText(this, "Error al cargar gestos predefinidos",Toast.LENGTH_LONG).show();
			finish();
		}
	}

	protected void onResume() {
		super.onResume();
		gestureView.addOnGesturePerformedListener(this);
	}

	protected void onStop() {
		gestureView.removeOnGesturePerformedListener(this);
		super.onStop();
	}

	public void onGesturePerformed(GestureOverlayView gOverlayView,Gesture gesture) {

		ArrayList<Prediction> predicciones = gestLib.recognize(gesture);

		if (predicciones.size() > 0) {
			Prediction prediccion = predicciones.get(0);

			if (prediccion.score > 1.0) {

				String texto = nombreGesto.getText().toString()+ prediccion.name;

				nombreGesto.setText(texto);
				return;
			} else {
				nombreGesto.setText("¿" + prediccion.name + "?");
			}
		}
	}
}
