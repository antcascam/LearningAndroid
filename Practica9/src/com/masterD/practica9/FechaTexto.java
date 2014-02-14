package com.masterD.practica9;


import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FechaTexto extends Activity{
	private EditText contenido;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		//asignamos elementos
		contenido = (EditText) findViewById(R.id.editText1);
		Button Boton = (Button) findViewById(R.id.button1);
		Button Boton5 = (Button) findViewById(R.id.button5);
		
		// creamos listener
		Boton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				// obtenemos fecha
				String edad = contenido.getText().toString();

				// comprobamos que se ha introducido correctamente:
				boolean cumplePatron = Pattern.matches(
						"\\d\\d-\\d\\d-\\d\\d\\d\\d", edad);

				if (cumplePatron) {
					guardar(edad);
					mostrarGuardado();
				} else {
					mostrarError();
				}
			}
		});
		
		Boton5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//mostramos fecha con toast
				mostrarFechaGuardada();
			}
		});

	}
	
	private void guardar(String edad){
		//Guardamos con sharedpreferences
		SharedPreferences shared = getSharedPreferences("fechaguardada", MODE_PRIVATE);
		SharedPreferences.Editor editor = shared.edit();
		editor.putString("fecha", edad);
		editor.commit();
	}
	
	private void mostrarError(){
		//mostramos error con un toast
		Toast toast = Toast.makeText(this, "La fecha introducida es errónea, escriba el siguiente formato: dd-mm-aaaa ", Toast.LENGTH_LONG);
		toast.show();
	}
	
	private void mostrarGuardado(){
		//mostramos error con un toast
		Toast toast1 = Toast.makeText(this, "Se ha guardado la fecha correctamente.", Toast.LENGTH_LONG);
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

