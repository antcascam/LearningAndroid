package es.masterD.tema7;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenciasActivity extends PreferenceActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}

/* Ojo con poner para que funcione
 

<activity android:name="PreferenciasActivity"></activity>

en el manifiest! después de </activity>

*/