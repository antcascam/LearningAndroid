package es.masterd.rss;

import es.masterd.rss.R;
import es.masterd.rss.db.FeedsDB.Posts;

import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;

public class NoticiaActivity extends Activity {
	
	private TextView titulo;
	private TextView fecha;
	private WebView contenido;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noticia);
		setTitle(R.string.titulo_noticias);
		titulo = (TextView) findViewById(R.id.feedTitulo);
		titulo.setOnClickListener(new OnClickListener() {
	
			@Override
				public void onClick(View v) {
				finish();
				}
			});
		
	fecha = (TextView) findViewById(R.id.feedFecha);
	contenido = (WebView) findViewById(R.id.feedContenido);
	
	}
	
	protected void onStart() {
		super.onStart();
		try {
			Bundle extras = getIntent().getExtras();
			long idNoticia = extras.getLong("idNoticia");
			final String[] columnas = new String[] { Posts._ID, // 0
					Posts.TITLE, // 1
					Posts.PUB_DATE, // 2
					Posts.DESCRIPTION // 3
			};
			
			
			Uri uri = Uri.parse("content://es.masterd.blog/post");
			uri = ContentUris.withAppendedId(uri, idNoticia);
		
			Cursor cursor = managedQuery(uri, columnas, null, null,Posts.PUB_DATE + " DESC");
			cursor.setNotificationUri(getContentResolver(), uri);
			startManagingCursor(cursor);

			if(cursor.moveToFirst()) {
				titulo.setText(cursor.getString(1));
				java.text.DateFormat dateFormat = DateFormat.getLongDateFormat(NoticiaActivity.this);
		
				fecha.setText(dateFormat.format(cursor.getLong(2)));
				String texto = new String(cursor.getString(3).getBytes(),"utf-8");
				contenido.loadDataWithBaseURL(null, texto,"text/html", "UTF-8", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

