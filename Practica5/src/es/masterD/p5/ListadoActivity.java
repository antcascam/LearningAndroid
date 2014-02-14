package es.masterD.p5;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListadoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz52);
     
     // Nuestra colección de datos
        String[] lista = new String[] { "Alumno 1: Antonio", "Alumno 2: Alba", "Alumno 3: Marcos", "Alumno 4: Estrella", "Alumno 5: Julio", "Alumno 6: José", "Alumno 7: Alejandro", "Alumno 8: Carmen", "Alumno 9: Francisco", "Alumno 10: Virginia" };
     
     // Creamos un Adapter para acceder a los datos de nuestro listado
     // Cada item se mostrará en un view definido por Android
     // (simple_list_item_1)
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        
     // Enlazamos nuestro adapter con nuestra vista
        ListView vista=(ListView)findViewById(android.R.id.list);
        vista.setAdapter(adapter);
    }

}