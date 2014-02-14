package com.turtledroid.ticu;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class TiCuMainActivity extends Activity {
	
	protected static final int CODIGO_PETICION = 100;

	private TextView config;
	private CheckBox check;
	private Button salir;
	private SharedPreferences settings;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);

		// Asignamos botones
		config = (TextView) findViewById(R.id.config);
		check = (CheckBox) findViewById(R.id.check);
		salir = (Button) findViewById(R.id.salir);

		// Al pulsar sobre el config, cambiamos de activity
		config.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),TiCuConfigActivity.class);
				// Pasamos un parámetro
				intent.putExtra("parametro1", "esto es un parámetro");
				// Llamamos a la nueva actividad indicando que esperamosque nos
				// devuelva un valor
				// asociado a la constante CODIGO_RETORNO
				startActivityForResult(intent, CODIGO_PETICION);

				return true;
			}

		});

		// Al pulsar check, activamos TiCu
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked == true)

					activaTiCu();

				else
					desactivaTiCu();
			}

		});

		// Al pulsar salir, salimos de la aplicación
		salir.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();

			}
		});

	}
	

	private void activaTiCu() {

		WifiReceiver wr = new WifiReceiver();
		
		if (getPreferences(0).getBoolean("wifi", false) == true){
			boolean wrs = wr.getwifistate();
			if(wrs==true){
				//Desactiva Bluetooth
			}
			else{
				//Activa Bluetooth
			}
		}
		
		if (getPreferences(0).getBoolean("bluetooth", false) == true){
			boolean wrs = wr.getwifistate();
			if(wrs==true){
				//Desactiva wifi
			}
			else{
				//Activa wifi
			}
		}
		
	}
	
	private void desactivaTiCu() {
		// TODO Auto-generated method stub
		
	}
	
	private class WifiReceiver extends BroadcastReceiver {
		
		private boolean conectado;

	    @Override
	    public void onReceive(Context context, Intent intent) {     
	        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
	        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
	        if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
	            //WIFI conectado
	        	conectado = true;
	        }else{
	            //WIFI desconectado  
	        	conectado = false;
	        }
	    } 
	    
	    public boolean getwifistate(){
	    	return conectado;
	    }
	}


}
