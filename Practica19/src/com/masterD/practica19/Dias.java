package com.masterD.practica19;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class Dias extends AppWidgetProvider {

	private GregorianCalendar calendario;
	private SimpleDateFormat formato;

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int x = 0; x < appWidgetIds.length; x++) {
			RemoteViews rView = new RemoteViews(context.getPackageName(),R.layout.main);
			rView.setTextViewText(R.id.inferior, "" + getDias());

			int id = appWidgetIds[x];
			appWidgetManager.updateAppWidget(id, rView);
		}
	}

	private long getDias() {
		calendario = new GregorianCalendar();
		formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		formato.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		calendario.setTime(formato.parse("2013-03-11 12:00", new ParsePosition(0)));

		long cumple = calendario.getTime().getTime();
		long actual = new Date().getTime();

		return (cumple - actual) / (24 * 60 * 60 * 1000);

	}

}
