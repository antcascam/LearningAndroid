package com.masterD.tema10;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private TextView orientacion;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		orientacion = (TextView) findViewById(R.id.orientacion);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		mSensorManager.unregisterListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
		super.onStop();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		String txt = "\n\nSensor: ";
		synchronized (this) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ORIENTATION:
				txt += "orientation\n";
				txt += "\n azimut: " + getDireccion(event.values[0]);
				txt += "\n y: " + event.values[1] + "º";
				txt += "\n z: " + event.values[2] + "º";
				orientacion.setText(txt);
				break;
			}
		}
	}

	private String getDireccion(float values) {
		String txtDirection = "";
		if (values < 22)
			txtDirection = "N";
		else if (values >= 22 && values < 67)
			txtDirection = "NE";
		else if (values >= 67 && values < 112)
			txtDirection = "E";
		else if (values >= 112 && values < 157)
			txtDirection = "SE";
		else if (values >= 157 && values < 202)
			txtDirection = "S";
		else if (values >= 202 && values < 247)
			txtDirection = "SO";
		else if (values >= 247 && values < 292)
			txtDirection = "O";
		else if (values >= 292 && values < 337)
			txtDirection = "NO";
		else if (values >= 337)
			txtDirection = "N";
		return txtDirection;
	}
}