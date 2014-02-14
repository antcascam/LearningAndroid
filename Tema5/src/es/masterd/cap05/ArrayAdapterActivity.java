package es.masterd.cap05;

import es.masterd.cap05.dialogo.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


//OJO CAMBIAR EN EL MANIFIEST PARA QUE FUNCIONE ESTA COMO MAIN  y quitar import es.masterd.cap05.miadapter.R;!!!!!!!!!!

public class ArrayAdapterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplelist);
		// Nuestra colección de datos
		String[] listado = new String[] { "item 1", "item 2", "item 3",
				"item 4" };
		// Creamos un Adapter para acceder a los datos de nuestro listado
		// Cada item se mostrará en un view definido por Android
		// (simple_list_item_1)
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listado);
		// Enlazamos nuestro adapter con nuestra vista
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(adapter);
	}
}