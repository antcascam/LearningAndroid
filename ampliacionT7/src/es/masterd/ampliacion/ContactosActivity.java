package es.masterd.ampliacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ContactosActivity extends Activity implements OnClickListener {
	private static final int REQUEST_CHOOSE_PHONE = 1;
	private TextView vPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//Identificamos los elementos
		vPhone = (TextView) findViewById(R.id.TextView01);
		findViewById(R.id.Button01).setOnClickListener(this);
		
		//Añadimos un valor por defecto al texto de vPhone
		vPhone.setText("Seleccione un contacto:");

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((requestCode == REQUEST_CHOOSE_PHONE)
				&& (resultCode == Activity.RESULT_OK)) {
			try {
				String phone = data.getStringExtra("phone");
				vPhone.setText("El número seleccionado es: "+phone);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onClick(View v) {
		Intent intent = new Intent("es.masterd.CHOOSE_PHONE");
		startActivityForResult(intent, REQUEST_CHOOSE_PHONE);
	}
}

