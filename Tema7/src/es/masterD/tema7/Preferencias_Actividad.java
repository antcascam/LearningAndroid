package es.masterD.tema7;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.widget.TextView;

public class Preferencias_Actividad extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), PreferenciasActivity.class);
		startActivity(intent);
		return super.onTouchEvent(event);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try{
			PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
			SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
			String ColorUsuario = p.getString("pref_color", "#000000" );
			int color = Color.parseColor(ColorUsuario);
			TextView titulo = (TextView)findViewById(R.id.titulo);
			titulo.setTextColor(color);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	
}