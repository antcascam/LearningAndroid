package com.masterD.practica13;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AgendaLlamadasActivity extends ListActivity {
	private Cursor cursor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agenda);

		String[] campos = new String[] { ContactsContract.Contacts.DISPLAY_NAME };
		int[] views = new int[] { android.R.id.text1 };
		cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				AgendaLlamadasActivity.this, // Contexto
				android.R.layout.simple_list_item_1, // Vista item
				cursor, // Cursor con datos
				campos, // Proyecci�n de campos
				views // Proyecci�n de vistas
		);
		setListAdapter(adapter);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		Cursor tlfCur = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,// Url de b�squeda
				null, // proyecci�n de campos (sacamos todos)
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", // Condicion
				new String[] { "" + id }, // Campos par la condicion
				null); // Orden

		// Generar la lista de telefonos para crear un dialogo
		int nTelefonos = tlfCur.getCount();
		final String[] telefonos = new String[nTelefonos];
		int x = 0;
		while (tlfCur.moveToNext()) {
			int col = tlfCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			telefonos[x++] = tlfCur.getString(col);
		}
		tlfCur.close();
		
		// Creamos el dialogo
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona un n�mero");
		builder.setItems(telefonos, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int item) {
				// Realizamos una llamada al n�mero clicado
				//Toast.makeText(AgendaLlamadasActivity.this, telefonos[item],Toast.LENGTH_SHORT).show();
				llama(telefonos[item]);
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void llama (String numero){
		try {
			Intent llamada = new Intent(Intent.ACTION_CALL);
			Uri uriTlf = Uri.parse("tel:"+numero);
			llamada.setData(uriTlf);
			startActivity(llamada);
		} catch (Exception e) {
			Toast.makeText(AgendaLlamadasActivity.this,"Error al realizar la llamada", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
	}
}
