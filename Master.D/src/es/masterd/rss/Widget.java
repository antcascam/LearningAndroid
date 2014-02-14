package es.masterd.rss;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import es.masterd.rss.db.FeedsDB.Posts;

public class Widget extends AppWidgetProvider {
public static Uri uri = Uri.parse("content://es.masterd.blog/post");
	
	
public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
				
for(int x=0 ; x < appWidgetIds.length ; x++) {
				
	int id = appWidgetIds[x];
			
	appWidgetManager.updateAppWidget(id, getRemoteView(context));
		
}
}
	
	








public static RemoteViews getRemoteView(Context context) {
		
String titular = "";
		
final String[] columnas = new String[] { 
	Posts._ID, 
	Posts.TITLE, 
	};
		
Cursor cursor = context.getContentResolver().query(uri, columnas, null, null, Posts.PUB_DATE + " DESC");
		
	if((cursor != null) && cursor.moveToFirst()) {
		titular = cursor.getString(1);
		}
		cursor.close();
		
Intent intent = new Intent(context, TitularesActivity.class);
PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
		
RemoteViews rView = new RemoteViews(context.getPackageName(), R.layout.widget);
rView.setTextViewText(R.id.titular	, titular);
rView.setOnClickPendingIntent(R.id.titular, pendingIntent);
	
return rView;
	
}
}

