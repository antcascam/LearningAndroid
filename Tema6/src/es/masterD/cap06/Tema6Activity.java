package es.masterD.cap06;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Tema6Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.resources);
    	// Configura el cambio de color del panel en base a la barra
    	final TextView nivel = (TextView) findViewById(R.id.nivel);
    	final View panel = findViewById(R.id.panel);
    	SeekBar barra = (SeekBar) findViewById(R.id.barra);
    	barra.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    
    public void onProgressChanged(SeekBar arg0, int nLevel, boolean arg2) {
    		nivel.setText("" +nLevel);
    		Drawable fondo = panel.getBackground();
    		fondo.setLevel(nLevel);
    		}
    		public void onStartTrackingTouch(SeekBar arg0) {}
    		public void onStopTrackingTouch(SeekBar arg0) {}
    		});
    	}
}