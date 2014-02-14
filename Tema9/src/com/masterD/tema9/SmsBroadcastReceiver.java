package com.masterD.tema9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			int numPdus = pdus.length;
			SmsMessage[] mensajes = new SmsMessage[numPdus];
			for (int x = 0; x < numPdus; x++) {
				mensajes[x] = SmsMessage.createFromPdu((byte[]) pdus[x]);
				String from = mensajes[x].getOriginatingAddress();
				String text = mensajes[x].getDisplayMessageBody();
				Toast.makeText(context, "De: " + from + "\n\n" + text,
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
