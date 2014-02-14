package es.masterD.tema7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JuegoSQLHelper extends SQLiteOpenHelper {
	
	 private static final int DB_VERSION = 1;
	 private static final String DB_NAME = "Juego";
	
	public JuegoSQLHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		if (db.isReadOnly()) {
			db = getWritableDatabase();
		}
		db.execSQL("CREATE TABLE jugadores(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, puntos INTEGER);");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Juego");
		onCreate(db);
	}
}

