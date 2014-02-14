package com.masterD.tema10;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListaSensoresActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setTitle("Listado de sensores");
		SensorManager sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensores = sManager.getSensorList(Sensor.TYPE_ALL);
		SensorAdapter adapter = new SensorAdapter(this,android.R.layout.simple_list_item_1, sensores);
		setListAdapter(adapter);
	}

	class SensorAdapter extends ArrayAdapter<Sensor> {
		private int tResId;

		public SensorAdapter(Context context, int textViewResourceId,List<Sensor> objects) {
			super(context, textViewResourceId, objects);
			this.tResId = textViewResourceId;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			if (view == null) {
				view = getLayoutInflater().inflate(tResId, null);
			}
			Sensor s = getItem(position);
			TextView text = (TextView) view.findViewById(android.R.id.text1);
			text.setText(s.getName());
			return view;
		}
	}
}
