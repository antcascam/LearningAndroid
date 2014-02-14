package com.masterd.ampliacion;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class DbActivity  extends ListActivity{
	private Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jugadores);
		final String[] columnas= new String[]{
			JuegoProvider._ID,
			JuegoProvider.NOMBRE,
			JuegoProvider.PUNTOS,
		};
		Uri uri =Uri.parse("content://es.masterd.juego/jugadores/*");
		cursor = managedQuery(uri, columnas, null, null, null);
		String[] camposDB = new String[]{
			JuegoProvider.NOMBRE, 
			JuegoProvider.PUNTOS
		};
		int[] camposView = new int[]{
			android.R.id.text1,
			android.R.id.text2,
		};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
			this,
			android.R.layout.two_line_list_item,
			cursor,
			camposDB,
			camposView
		);
		setListAdapter (adapter);
	}
}
