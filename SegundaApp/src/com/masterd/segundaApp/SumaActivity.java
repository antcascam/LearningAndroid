package com.masterd.segundaApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SumaActivity extends Activity {
	private static final int CODIGO_RETORNO_OK = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// cargamos la vista
		setContentView(R.layout.suma);
		// Localizamos los componentes de la vista
		TextView textoCabecera = (TextView) findViewById(R.id.textoCabecera);
		TextView textoArriba = (TextView) findViewById(R.id.textoArriba);
		TextView textoAbajo = (TextView) findViewById(R.id.textoAbajo);
		Button botonSuma = (Button) findViewById(R.id.botonSuma);
		final EditText parametro2 = (EditText) findViewById(R.id.parametro2);
		final EditText parametro3 = (EditText) findViewById(R.id.parametro3);
		
		// Cambiamos el texto del campo de texto
		textoCabecera.setText(R.string.suma);
		textoArriba.setText(R.string.arriba);
		textoAbajo.setText(R.string.abajo);
		// Cambiamos el texto del boton
		botonSuma.setText(R.string.botSuma);
		
		// Asignamos el comportamiento al boton:
		// cuando se pulse se cerrará para volver a la anterior
		botonSuma.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// cerrará la actividad sacándola de la pila y
				// devolviendo el control a la Actividad anterior
				devuelveParametros(parametro2, parametro3);
			}
		});
		// Mostramos los parametros recibidos
		Intent intent = getIntent();
		Bundle todosParametros = intent.getExtras();
		parametro2.setText(todosParametros.getString("parametro2"));
		parametro3.setText(todosParametros.getString("parametro3"));
	}

	protected void devuelveParametros(EditText p1,EditText p2) {
		
		Intent data = new Intent();
		String resultado = null;
		String par1 = p1.getText().toString();
		String par2 = p2.getText().toString();
		
		if((par1.length()>0) && (par2.length()>0)){
			int a = Integer.parseInt(par1);
			int b = Integer.parseInt(par2);   
			if(a<1000 &&  b<1000){  
				resultado = Integer.toString((a + b));
			}
		}else{
			resultado = "Introduce valores entre 0 y 999";
		}
			
		data.putExtra("resultado", resultado);
		setResult(CODIGO_RETORNO_OK, data);
		finish();
	}
}