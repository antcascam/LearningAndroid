package es.masterD.tema7;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class Tema7Activity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		SharedPreferences settings = getPreferences(0);
		int veces = settings.getInt("numVeces", 0);
		Toast t = Toast.makeText(this, "Cargado " + veces + " veces", Toast.LENGTH_LONG);
		t.show();
	}

	protected void onStop() {
		// Necesitamos un editor de preferencias para editarlas
		SharedPreferences settings = getPreferences(0);
		SharedPreferences.Editor editor = settings.edit();
		// Cambiamos el valor
		int nActual = settings.getInt("numVeces", 0);
		editor.putInt("numVeces", nActual + 1);
		// Validamos los cambios
		editor.commit();
		// Pasamos el control al padre para que termine la Actividad
		super.onStop();
	}
}