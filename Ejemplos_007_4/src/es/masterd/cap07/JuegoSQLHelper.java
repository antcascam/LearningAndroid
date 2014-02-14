package es.masterd.cap07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class JuegoSQLHelper extends SQLiteOpenHelper{

	public JuegoSQLHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		if(db.isReadOnly()){db = getWritableDatabase();}
		db.execSQL("CREATE TABLE jugadores (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, puntos INTEGER);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
