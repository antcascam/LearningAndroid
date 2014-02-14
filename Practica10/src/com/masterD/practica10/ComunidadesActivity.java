package com.masterD.practica10;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.SimpleCursorAdapter;

public class ComunidadesActivity extends ListActivity {
	private Cursor cursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Creamos la base de datos con sqlHelper
		ComunidadesSQLHelper dbHelper = new ComunidadesSQLHelper(ComunidadesActivity.this);
		
		//obtenemos la base de datos
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		//creamos un array de string para la proyeccion
		String[] camposDb = { "comunidad", BaseColumns._ID };
		
		//creamos el cursor
		cursor = db.query("comunidades", camposDb, null, null, null, null,"comunidad ASC");
		
		//creamos el array de enteros para el adapter
		int[] camposView = new int[] { android.R.id.text1 };

		//finalmente creamos  y mostramos el adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(ComunidadesActivity.this,android.R.layout.two_line_list_item, cursor, camposDb,camposView);
		setListAdapter(adapter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		startManagingCursor(cursor);
	}

	@Override
	protected void onStop() {
		stopManagingCursor(cursor);
		super.onStop();
	}
}

