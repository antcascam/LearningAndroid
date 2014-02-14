package es.masterd.cap05;

import es.masterd.cap05.dialogo.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


//OJO CAMBIAR EN EL MANIFIEST PARA QUE FUNCIONE ESTA COMO MAIN y quitar import es.masterd.cap05.miadapter.R;!!!!!!!!!!

public class MiListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplelist);
		// Nuestra colección de datos
		String[] listado = new String[] { "item 1", "item 2", "item 3","item 4" };
		// Creamos un Adapter para acceder a los datos de nuestro listado
		// Cada item se mostrarÃ¡ en un view definido por android
		// (simple_list_item_1)
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listado);
		// Enlazamos nuestro adapter con nuestra vista
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String texto = (String)getListAdapter().getItem(position);
		Toast.makeText(MiListActivity.this, texto, Toast.LENGTH_SHORT).show();
	}
}