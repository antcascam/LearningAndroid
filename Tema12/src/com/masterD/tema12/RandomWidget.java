package com.masterD.tema12;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class RandomWidget extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int x = 0; x < appWidgetIds.length; x++) {
			// Calculamos el número aleatorio a mostrar
			int numAleatorio = (new Random()).nextInt(100);
			// Lo colocamos en una vista remota
			RemoteViews rView = new RemoteViews(context.getPackageName(),
					R.layout.randomwidget);
			rView.setTextViewText(R.id.aleatorio, "" + numAleatorio);
			// Mandamos la vista al widget
			int id = appWidgetIds[x];
			appWidgetManager.updateAppWidget(id, rView);
		}
	}
}
