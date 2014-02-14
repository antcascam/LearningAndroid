package com.masterD.practica20;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RemoteViews;

public class ConfigActivity extends Activity implements OnClickListener {

	private SharedPreferences prefs;
	private int mAppWidgetId;
	private DatePicker fechaSel;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);

		prefs = getSharedPreferences("cuentaAtrasPrefs", Activity.MODE_PRIVATE);

		Button boton = (Button) findViewById(R.id.listo);
		boton.setOnClickListener(this);
		fechaSel = (DatePicker) findViewById(R.id.Fecha);

		String curFech = prefs.getString("pref_cuenta_atras_fecha","2012-10-01 12:00");
		String[] p = curFech.split("-");
		int año = Integer.parseInt(p[0]);
		int mes = Integer.parseInt(p[1]);
		int dia = Integer.parseInt(p[2]);

		fechaSel.init(año, mes, dia, null);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
		}
	}

	public void onClick(View v) {

		String fecha = fechaSel.getYear() + "-" + (fechaSel.getMonth() + 1)+ "-" + fechaSel.getDayOfMonth();

		Editor editor = prefs.edit();
		editor.putString("pref_cuenta_atras_fecha", fecha);
		editor.commit();

		Context context = ConfigActivity.this;
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

		RemoteViews views = Dias.getRemoteView(context);
		appWidgetManager.updateAppWidget(mAppWidgetId, views);

		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, resultValue);

		finish();
	}
}
