package com.masterD.tema12;

import java.util.Timer;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

public class RelojWidget2 extends AppWidgetProvider {

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new ActualizarRelojTimerTask(context,appWidgetManager), 1, 30 * 1000);
	}

	/**
	 * Clase timer que se encargará de actualizar el widget cada cierto tiempo
	 */
	class ActualizarRelojTimerTask extends TimerTask {
		private Context context;

		public ActualizarRelojTimerTask(Context context,AppWidgetManager appWidgetManager) {
			this.context = context;
		}

		public void run() {
			RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.relojwidget2);
			final java.text.DateFormat dateformat = DateFormat.getTimeFormat(context);
			String ahora = dateformat.format(System.currentTimeMillis());
			updateViews.setTextViewText(R.id.hora, ahora);
			ComponentName thisWidget = new ComponentName(context,RelojWidget2.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			manager.updateAppWidget(thisWidget, updateViews);
		}
	}
}