package com.masterD.practica10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ComunidadesSQLHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "Comunidades.db";
	private static final int DB_VERSION = 1;

	public ComunidadesSQLHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if (db.isReadOnly()) {
			db = getWritableDatabase();
		}
		db.execSQL("CREATE TABLE comunidades (" + BaseColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + "comunidad TEXT"
				+ ");");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Andalucía\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Aragón\")");
		
		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Baleares\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Canarias\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Cantabria\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Castilla la Mancha\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Castilla y León\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Cataluña\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Comunidad de Madrid\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Comunidad Valenciana\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Extremadura\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Galicia\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"La Rioja\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Murcia\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Navarra\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"País Vasco\")");

		db.execSQL("INSERT INTO comunidades(comunidad) VALUES(\"Principado de Asturias\")");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS comunidades");
		onCreate(db);
	}

}
