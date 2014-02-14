package com.masterD.tema12;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

public class RelojWidget extends AppWidgetProvider {

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
		context.startService(new Intent(context, ActualizarRelojService.class));
	}
	public static class ActualizarRelojService extends Service {
		/**
		* Servicio de actualizacion del widget
		*/
		public void onStart(Intent intent, int startId) {
			// Build the widget update for today
			RemoteViews updateViews = new RemoteViews(this.getPackageName(),R.layout.relojwidget);
			final java.text.DateFormat dateformat = DateFormat.getTimeFormat(this);
			String ahora = dateformat.format(System.currentTimeMillis());
			updateViews.setTextViewText(R.id.hora, ahora);
			// Push update for this widget to the home screen
			ComponentName thisWidget = new ComponentName(this, RelojWidget.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(this);
			manager.updateAppWidget(thisWidget, updateViews);
		}

		public IBinder onBind(Intent intent) {
			// No permitimos conectar con este servicio
			return null;
		}
	}
}
