package es.masterd.juego;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;

public class Proveedor_de_contenido extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Uri uri = Uri.parse("content://es.masterd.juego/jugadores");
		ContentValues values = new ContentValues();
		values.put("nombre", "Federico");
		values.put("puntos", "100");
		getContentResolver().insert(uri, values);
		return super.onTouchEvent(event);
	}
}