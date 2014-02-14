package es.masterd.cap05.miadapter;

import es.masterd.cap05.dialogo.R;
import java.util.ArrayList;
import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class VersionesGalleryActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.simplelist);
		setContentView(R.layout.gallery);
		ArrayList<AndroidVersion> versiones = new ArrayList<AndroidVersion>();
		versiones.add(new AndroidVersion("Android", "", R.drawable.logo));
		versiones.add(new AndroidVersion("CupCake", "1.5", R.drawable.cupcake));
		versiones.add(new AndroidVersion("Donut", "1.6", R.drawable.donut));
		versiones.add(new AndroidVersion("Eclair", "2.0/2.1", R.drawable.eclair));
		Gallery gallery = (Gallery) findViewById(android.R.id.list);
		VersionesAdapter adapter = new VersionesAdapter(VersionesGalleryActivity.this, versiones);
		gallery.setAdapter(adapter);
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menuprincipal, menu);
	return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuAcercaDe:
			
			/*String aboutTxt = "(c) 2010 Master-D";
			Toast.makeText(VersionesGalleryActivity.this, aboutTxt,
					Toast.LENGTH_LONG).show();*/
			
			
			// Cargamos la vista personalizada
			 ViewGroup vistaRaiz = (ViewGroup) findViewById(R.id.toastRaiz);
			 LayoutInflater inflater = getLayoutInflater();
			 View layout = inflater.inflate(R.layout.mi_toast, vistaRaiz);
			
			// Cambiamos el texto (buscamos el label y lo modificamos)
			 TextView texto = (TextView) layout.findViewById(R.id.texto);
			 texto.setText("(c) 2012 Master-D y AJ Castaño");
			
			// Creamos el toast y le asignamos la vista que hemos personalizado
			 Toast toast = new Toast(getApplicationContext());
			 toast.setView(layout);
			
			// Lo centramos en pantalla
			 toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
			
			// Permanecerá más rato en pantalla
			 toast.setDuration(Toast.LENGTH_LONG);
			
			// Lo mostramos
			 toast.show();
			return true;
			
		case R.id.menuQuit:
			// Conseguimos una referencia al servicio encargado de mostrarlos
			 String ns = Context.NOTIFICATION_SERVICE;
			 NotificationManager notificationManager = (NotificationManager) getSystemService(ns);
			
			// Definimos el aviso
			 int icono = R.drawable.notification_icon;
			 CharSequence aviso = "Notificación Importanteeee!!!!";
			 long ahora = System.currentTimeMillis();
			 Notification notification = new Notification(icono, aviso, ahora);
			
			// Definimos los detalles del aviso
			 Context context = getApplicationContext();
			 CharSequence titulo = "Has salido de la aplicación!";
			 CharSequence texto1 = "Esto es una muestra de notificación";
			 Intent notificationIntent = new Intent(this, VersionesGalleryActivity.class);
			 PendingIntent contentIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
			 notification.setLatestEventInfo(context, titulo, texto1,contentIntent);
			
			// Pasamos el aviso al manager
			 final int NOTIFICACION_ID = 1;
			 notificationManager.notify(NOTIFICACION_ID, notification);
			 finish();
			return true;
			
		default:
			return false;
		}
	}
}