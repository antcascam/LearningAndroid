package com.masterD.practica9;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class FechaTimePicker extends Activity {
	private DatePicker contenido;
	private int dia; 
	private int mes; 
	private int ano;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picker);

		// asignamos elementos
		contenido = (DatePicker) findViewById(R.id.datePicker1);
		Button Boton = (Button) findViewById(R.id.button2);
		Button Boton5 = (Button) findViewById(R.id.button5);

		// creamos listener
		Boton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				// obtenemos fecha
				dia = contenido.getDayOfMonth();
				mes = contenido.getMonth();
				ano = contenido.getYear();

				// guardamos y mostramos guardado
				guardar();
				mostrarGuardado();
			}
		});
		
		Boton5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//mostramos fecha con toast
				mostrarFechaGuardada();
			}
		});

	}

	private void guardar() {
		// Guardamos con sharedpreferences
		
		String edad = new StringBuilder().append(dia).append("-").append(mes + 1).append("-").append(ano).append(" ").toString(); 
		
		SharedPreferences shared = getSharedPreferences("fechaguardada", MODE_PRIVATE);
		SharedPreferences.Editor editor = shared.edit();
		editor.putString("fecha", edad);
		editor.commit();
	}

	private void mostrarGuardado() {
		// mostramos guardado correcto con un toast
		Toast toast1 = Toast.makeText(this,"Se ha guardado la fecha correctamente.", Toast.LENGTH_LONG);
		toast1.show();
	}
	
	private void mostrarFechaGuardada(){		
		//mostramos fecha con toast		
		SharedPreferences shared = getSharedPreferences("fechaguardada", MODE_PRIVATE);
		String edad = shared.getString("fecha", "");
		
		//controlamos que al principio no haya nada guardado
				if(edad.equals(""))
					edad = "Aún no has introducido ninguna, introdúzcala y pulse guardar.";
				
		Toast toast = Toast.makeText(this, "Fecha de cumpleaños guardada: " + edad,Toast.LENGTH_LONG);
		toast.show();
	}
}
