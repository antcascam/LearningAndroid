package es.masterd.rss;

import es.masterd.rss.R;
import es.masterd.rss.db.FeedsDB.Posts;
import es.masterd.rssparser.RssDownloadHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class TitularesActivity extends ListActivity {
	
	private static final long FRECUENCIA_ACTUALIZACION = 60*60*1000;
	private static final int DIALOG_ABOUT = 0;
	private ActualizarPostAsyncTask tarea;
	
	

	@Override
		public void onCreate(Bundle savedInstanceState) {
		
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_PROGRESS);
			setContentView(R.layout.feeds);
			setTitle(R.string.titulo_noticias);
			configurarAdapter();
		}
	
	@Override
		public void onResume() {
		
			super.onResume();
			cargarNoticias();
	}
	

	public void configurarAdapter() {
		// Obtenemos todos los artículos de la BD
			final String[] columnas = new String[] { Posts._ID, // 0
			Posts.TITLE, // 1
			Posts.PUB_DATE, // 2
			Posts.DESCRIPTION 
			};
			
			
		Uri uri = Uri.parse("content://es.masterd.blog/post");
		Cursor cursor = managedQuery(uri, columnas, null, null, Posts.PUB_DATE + " DESC");

		cursor.setNotificationUri(getContentResolver(), uri);

		startManagingCursor(cursor);

		String[] camposDb = new String[] { Posts.TITLE, Posts.PUB_DATE, Posts.DESCRIPTION };
		int[] camposView = new int[] { R.id.feedTitulo, R.id.feedFecha, R.id.feedTexto };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.feeds_item,
		cursor,camposDb, camposView);

		final java.text.DateFormat dateFormat = DateFormat.getLongDateFormat(TitularesActivity.this);
		
		adapter.setViewBinder(new ViewBinder() {
			
			@Override
			public boolean setViewValue(View view, Cursor cursor,int columnIndex) {
				
				int viewId= view.getId();
				
				switch(viewId) {
					case R.id.feedFecha:
					long timestamp = cursor.getLong(columnIndex);
					
				((TextView) view).setText(dateFormat.format(timestamp));

					return true;
					case R.id.feedTexto:
					String source = cursor.getString(columnIndex);
					int index = source.indexOf(".");
									
					String texto = source.substring(0, index) + "...";
									
					((TextView) view).setText(texto);
									
					return true;
					default:
					return false; 

				}
			}
		});

		setListAdapter(adapter);
	}

class ActualizarPostAsyncTask extends AsyncTask<Void, Void, Void> {
	
	@Override
		protected void onPreExecute() {
		
			setBarraProgresoVisible(true);
			super.onPreExecute();
		}
	
	@Override
		protected Void doInBackground(Void... params) {
		
			MasterdApplication app = (MasterdApplication)
			getApplication();
			RssDownloadHelper.updateRssData(app.getRssUrl(),
			getContentResolver());
			return null;
		}
	
	@Override
		protected void onPostExecute(Void result) {
		
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			Editor editor = prefs.edit();
			editor.putLong("ultima_actualizacion",
			System.currentTimeMillis());
			editor.commit();
			setBarraProgresoVisible(false);
		}
	
	@Override
		protected void onCancelled() {
			setBarraProgresoVisible(false);
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			Editor editor = prefs.edit();
			editor.putLong("ultima_actualizacion", 0);
			editor.commit();
			super.onCancelled();
			}
		}

public void cargarNoticias() {
	SharedPreferences prefs = getPreferences(MODE_PRIVATE);
	long ultima = prefs.getLong("ultima_actualizacion", 0);
	if ((System.currentTimeMillis() - ultima) > FRECUENCIA_ACTUALIZACION) {
		tarea = new ActualizarPostAsyncTask();
		tarea.execute();
	}
}


@Override
	protected void onStop() {
	// Si hay una tarea corriendo en segundo plano, la paramos
		if (tarea != null && !tarea.getStatus().equals(AsyncTask.Status.FINISHED)) {
			tarea.cancel(true);
		}
		
		super.onStop();
	}

public void setBarraProgresoVisible(boolean visible) {
	final Window window = getWindow();
	
	if (visible) {
		
		window.setFeatureInt(Window.FEATURE_PROGRESS,
		Window.PROGRESS_VISIBILITY_ON);
		window.setFeatureInt(Window.FEATURE_PROGRESS,
		Window.PROGRESS_INDETERMINATE_ON);
		
	} else {
		
		window.setFeatureInt(Window.FEATURE_PROGRESS,
		Window.PROGRESS_VISIBILITY_OFF);
	}
}

@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menuprincipal, menu);
		return true;
	}

@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuAcercaDe:
		showDialog(DIALOG_ABOUT);
		return true;
		case R.id.menuQuit:
		finish();
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
	}

@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DIALOG_ABOUT:
			AlertDialog dialogAbout = null;
			final AlertDialog.Builder builder;
			LayoutInflater li = LayoutInflater.from(this);
			View view = li.inflate(R.layout.acercade, null);
			builder = new AlertDialog.
			Builder(this).setIcon(R.drawable.icon)
			.setTitle(getString(R.string.app_name))
			.setPositiveButton("Ok", null)
			.setView(view);
			dialogAbout = builder.create();
			return dialogAbout;
			default:
			return null;
		}
	}

@Override
	protected void onListItemClick(ListView l, View v,int position, long id) {
		Intent i = new Intent();
		i.setClass(TitularesActivity.this,
		NoticiaActivity.class);
		i.putExtra("idNoticia", id);
		startActivity(i);
		}
	}