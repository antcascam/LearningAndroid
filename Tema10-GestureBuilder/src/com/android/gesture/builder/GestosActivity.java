package com.android.gesture.builder;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "NewApi", "NewApi" })
public class GestosActivity extends Activity implements OnGesturePerformedListener {
	
	private GestureOverlayView gestureView;
	private TextView nombreGesto;
	private GestureLibrary gestLib;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gestureView = (GestureOverlayView) findViewById(R.id.gestures);
		nombreGesto = (TextView) findViewById(R.id.NombreGesto);
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
				nombreGesto.setText(prediccion.name);
				return;
			} else {
				nombreGesto.setText("¿" + prediccion.name + "?");
			}
		} else {
			nombreGesto.setText("gesto desconocido");
		}
	}

}
