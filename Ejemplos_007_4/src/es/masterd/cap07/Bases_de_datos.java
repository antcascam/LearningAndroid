package es.masterd.cap07;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Bases_de_datos extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Context context = getBaseContext();
        JuegoSQLHelper dbHelper = new JuegoSQLHelper(context, "JuegoDB", null, 1);
        SQLiteDatabase jugadoresDB = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", "Pepe");
        values.put("puntos", "100");
        jugadoresDB.insert("jugadores", "", values);
    }
}    