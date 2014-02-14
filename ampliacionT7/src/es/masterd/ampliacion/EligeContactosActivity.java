package es.masterd.ampliacion;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class EligeContactosActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setTitle("Elija un Contacto:");

		// Contactos con teléfono ordenados por nombre:
		Cursor mCursor = getContentResolver().query(
				Data.CONTENT_URI,new String[] { Data._ID, Data.DISPLAY_NAME, Phone.NUMBER,Phone.TYPE },
				Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "' AND "
						+ Phone.NUMBER + " IS NOT NULL", null,
				Data.DISPLAY_NAME + " ASC");

		startManagingCursor(mCursor);

		// Configuramos el adapter
		ListAdapter adapter = new SimpleCursorAdapter(this, // context
				android.R.layout.simple_list_item_2, // Layout
				mCursor, // cursor
				new String[] { Data.DISPLAY_NAME, Phone.NUMBER }, // campos del cursor
				new int[] { android.R.id.text1, android.R.id.text2 } // campos del view
		);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent result = new Intent();

		// Obtenemos los datos
		Cursor c = (Cursor) getListAdapter().getItem(position);
		int colIdx = c.getColumnIndex(Phone.NUMBER);
		String phone = c.getString(colIdx);

		// Guardamos el teléfono para mostrarlo
		result.putExtra("phone", phone);
		setResult(Activity.RESULT_OK, result);

		// Cerrar activity
		finish();
	}
}
