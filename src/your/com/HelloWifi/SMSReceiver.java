package your.com.HelloWifi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	private String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private String password = "";
	private String key;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		try {
			InputStream in = context.getApplicationContext().openFileInput("key.txt");
			if (in != null) {
				InputStreamReader tmp = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(tmp);
				String str;
				StringBuilder buf = new StringBuilder();
				while ((str = reader.readLine()) != null) {
					buf.append(str);
				}
				in.close();
				key = buf.toString();
			}
		}
		catch (Throwable t) {
			//Toast.makeText(context.getApplicationContext(), "Exceptioni got you : " + t.toString(), Toast.LENGTH_SHORT).show();
		}
		
		//Toast.makeText(context, ACTION, 0).show();
		Bundle bundle = intent.getExtras();
		SmsMessage[] sms = null;
		String message = "";
		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			sms = new SmsMessage[pdus.length];
			for (int i = 0; i < sms.length; i++) {
				sms[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				message += sms[i].getMessageBody().toString();
			}
			//Toast.makeText(context, "message: " + message, Toast.LENGTH_SHORT).show();
			//Toast.makeText(context, "key: " + key, Toast.LENGTH_SHORT).show();
			if (message.equals(key)) {
				Toast.makeText(context, "about to scan...", Toast.LENGTH_SHORT).show();
				Intent scan = new Intent(context, ScanService.class);
				Bundle bundle1=new Bundle();
		        bundle1.putString("receipt", sms[0].getOriginatingAddress());
		        scan.putExtras(bundle1);
		        
				context.startService(scan);
				
				/*
				Intent scan = new Intent(context, Scan.class);
				scan.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				//it.setClass(context, Scan.class);
		        
		        //context.getApplicationContext().startActivity(it);
		        context.startActivity(scan);
				*/
			}
			else if(message.equals(key+ ".ring")) {
				Log.d("ring", "ok");
				Toast.makeText(context, "about to ring...", Toast.LENGTH_SHORT).show();
				context.startService(new Intent(context,RingService.class));
				/*
				Intent ring = new Intent(context, Ring.class);
				context.startService(ring);
				*/
			}
			else if(message.equalsIgnoreCase(key + ".gps")) {
				Toast.makeText(context, "GPS starting...", Toast.LENGTH_SHORT).show();
				Intent scan = new Intent(context, GPSService.class);
				Bundle bundle1=new Bundle();
		        bundle1.putString("receipt", sms[0].getOriginatingAddress());
		        scan.putExtras(bundle1);
		        
				context.startService(scan);
			}
		}
	}
}
