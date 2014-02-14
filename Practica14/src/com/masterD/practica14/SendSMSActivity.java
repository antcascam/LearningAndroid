package com.masterD.practica14;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SendSMSActivity extends ListActivity {

	private String telefono = "";


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sms);
		
		final EditText sms = (EditText) findViewById(R.id.TextoSms);
		
		Button boton = (Button) findViewById(R.id.BotonEnviar);

		boton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {			
				try {
					telefono = getIntent().getExtras().getString("telefono");	
					setTitle(telefono);
				} catch(Exception e) {
					e.printStackTrace();
					finish();
				}
				String texto = sms.getText().toString();
				enviaSMS(telefono, texto);
				sms.setText("");
			}
		});

		// Cargamos la lista de mensajes enviados
		Uri uri = Uri.parse("content://sms/sent");
		Cursor cursor = getContentResolver().query(uri, null, null, null, null);
		startManagingCursor(cursor);
		// Relacionamos las columnas con la vista
		String[] columns = new String[] { "address", "body" };
		int[] names = new int[] { android.R.id.text1, android.R.id.text2 };
		// Usamos un adapter del sistema
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2, cursor, columns, names);
		// Lo asociamos con la Activity
		setListAdapter(adapter);
	}

	private void enviaSMS(String destino, String texto) {
		try {
			SmsManager smsMgr = SmsManager.getDefault();
			smsMgr.sendTextMessage(destino, null, texto, null, null);
			
			// Si tenemos acceso al proveedor de contenidos SMS
			// Guardamos el mensaje en la carpeta de enviados
			
			if (getPackageManager().resolveContentProvider("sms", 0) != null) {
				ContentValues values = new ContentValues();
				values.put("address", destino);
				values.put("body", texto);
				Uri uri = Uri.parse("content://sms/sent");
				getContentResolver().insert(uri, values);
			}
			Toast.makeText(SendSMSActivity.this, "SMS enviado",Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(SendSMSActivity.this,"Error en el envio: " + e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
		}
	}
}
