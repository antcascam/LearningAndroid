package com.masterD.tema9;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LlamadasActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.llamadas);
		
		//Creamos el manejador del teléfono
		TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		tManager.listen(new TelefonoListener(),PhoneStateListener.LISTEN_CALL_STATE);

		//Acción para el botón de llamada
		Button llamar = (Button) findViewById(R.id.llamar);
		llamar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					Intent llamada = new Intent(Intent.ACTION_CALL);
					Uri uriTlf = Uri.parse("tel:976445545");
					llamada.setData(uriTlf);
					startActivity(llamada);
				} catch (Exception e) {
					Toast.makeText(v.getContext(),
							"Error al realizar la llamada", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}

	class TelefonoListener extends PhoneStateListener {

		public void onCallStateChanged(int state, String number) {
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				Toast.makeText(LlamadasActivity.this,"Telefono sonado...." + number, Toast.LENGTH_LONG).show();
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Toast.makeText(LlamadasActivity.this, "descolgado..",Toast.LENGTH_LONG).show();
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				Toast.makeText(LlamadasActivity.this, "En espera...",Toast.LENGTH_LONG).show();
				break;
			default:
				super.onCallStateChanged(state, number);
				break;
			}
		}
	}
}