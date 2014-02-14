package com.masterD.tema9;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AgendaActivity extends ListActivity {
	private Cursor cursor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agenda);

		String[] campos = new String[] { ContactsContract.Contacts.DISPLAY_NAME };
		int[] views = new int[] { android.R.id.text1 };
		cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				AgendaActivity.this, // Contexto
				android.R.layout.simple_list_item_1, // Vista item
				cursor, // Cursor con datos
				campos, // Proyección de campos
				views // Proyección de vistas
		);
		setListAdapter(adapter);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		Cursor tlfCur = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,// Url de búsqueda
				null, // proyección de campos (sacamos todos)
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
		builder.setTitle("Selecciona un número");
		builder.setItems(telefonos, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int item) {
				Toast.makeText(AgendaActivity.this, telefonos[item],Toast.LENGTH_SHORT).show();
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
	}
}
