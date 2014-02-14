package com.masterD.practica20;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class Dias extends AppWidgetProvider {

	static GregorianCalendar calendario;
	static SimpleDateFormat formato;

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {

		for (int x = 0; x < appWidgetIds.length; x++) {

			int id = appWidgetIds[x];
			appWidgetManager.updateAppWidget(id, getRemoteView(context));
		}
	}

	public static RemoteViews getRemoteView(Context context) {
		calendario = new GregorianCalendar();
		formato = new SimpleDateFormat("yyyy-MM-dd");
		formato.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		SharedPreferences prefs = context.getSharedPreferences("cuentaAtrasPrefs", Activity.MODE_PRIVATE);
		String fecha = prefs.getString("pref_cuenta_atras_fecha", "1970-01-01 12:00");

		calendario.setTime(formato.parse(fecha, new ParsePosition(0)));

		long cumple = calendario.getTime().getTime();
		long actual = new Date().getTime();

		long dias = (cumple - actual) / (24 * 60 * 60 * 1000);

		RemoteViews rView = new RemoteViews(context.getPackageName(),R.layout.main);
		rView.setTextViewText(R.id.inferior,""+dias);

		return rView;
	}
}
