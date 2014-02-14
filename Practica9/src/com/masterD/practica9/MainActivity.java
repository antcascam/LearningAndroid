package com.masterD.practica9;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// asignamos elementos
		Button Boton3 = (Button) findViewById(R.id.button3);
		Button Boton4 = (Button) findViewById(R.id.button4);
		Button Boton5 = (Button) findViewById(R.id.button5);
		
		//creamos listener
		Boton3.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), FechaTexto.class);
					startActivity(intent);
					}
				});
		Boton4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), FechaTimePicker.class);
				startActivity(intent);
				}
			});
		
		Boton5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//mostramos fecha con toast
				mostrarFechaGuardada();
			}
		});
	}
	
	private void mostrarFechaGuardada(){
		//mostramos fecha con toast
		SharedPreferences shared = getSharedPreferences("fechaguardada", MODE_PRIVATE);
		String edad = shared.getString("fecha", "");
		
		//controlamos que al principio no haya nada guardado
		if(edad.equals(""))
			edad = "Aún no has introducido ninguna, por favor seleccione una opción.";
		
		Toast toast = Toast.makeText(this, "Fecha de cumpleaños guardada: " + edad,Toast.LENGTH_LONG);
		toast.show();
	}
}
