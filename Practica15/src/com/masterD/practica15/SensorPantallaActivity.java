package com.masterD.practica15;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class SensorPantallaActivity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private View proximidad;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		proximidad = findViewById(R.id.fondo);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),SensorManager.SENSOR_DELAY_NORMAL);

	}

	protected void onStop() {
		super.onStop();
		mSensorManager.unregisterListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));

	}

	public void onAccuracyChanged(Sensor sensor, int accuary) {

	}

	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
				Boolean enabled = (event.values[0] > 0);
				proximidad.setEnabled(enabled);
			}
		}
	}
}
