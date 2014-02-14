package es.masterd.cap071;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class Preferencias_compartidas extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences settings = getPreferences(0);
        int veces = settings.getInt("numveces", 0);
        Toast.makeText(this, "Cargado" + veces + " veces", Toast.LENGTH_LONG).show();
    }

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();
        int nActual = settings.getInt("numveces", 0);
        editor.putInt("numveces", nActual + 1);
        editor.commit();
		super.onStop();
	}
}