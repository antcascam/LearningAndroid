package es.masterd.cap05.dialogo;


import es.masterd.cap05.miadapter.VersionesGalleryActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



//OJO CAMBIAR EN EL MANIFIEST PARA QUE FUNCIONE ESTA COMO MAIN  y quitar import es.masterd.cap05.miadapter.R;!!!!!!!!!!

public class DialogosActivity extends Activity {
	private static final int DIALOG_ALERT = 1;
	private static final int DIALOG_PROGRESS = 2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogos);
		// Buscamos los componentes de boton
				Button boton1 = (Button)
				findViewById(R.id.button1);
				Button boton2 = (Button)
				findViewById(R.id.button2);
			// El boton1 al pulsarse
				boton1.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						onCreateDialog(DIALOG_ALERT);
					}
				});
				// El boton2 al pulsarse
				boton2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						onCreateDialog(DIALOG_PROGRESS);
					}
				});
	}
	
	/**
	 * Crea los diálogos
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog newDialog = null;
		
		switch (id) {
		case DIALOG_ALERT:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("¿Quieres salir?");
			builder.setCancelable(false);
			builder.setPositiveButton("Si",new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int id) {DialogosActivity.this.finish();}});
			builder.setNegativeButton("No",new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int id) {dialog.cancel();}});
			newDialog = builder.create();
			
			break;
			
		case DIALOG_PROGRESS:
			newDialog = ProgressDialog.show(DialogosActivity.this,"","Cargando, espere...", true);
			
			break;
			}
		return newDialog;
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