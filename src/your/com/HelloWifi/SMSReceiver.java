package your.com.HelloWifi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	private String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private String password = "";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		try {
			InputStream in = context.getApplicationContext().openFileInput("string.txt");
			if (in != null) {
				InputStreamReader tmp = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(tmp);
				String str;
				StringBuilder buf = new StringBuilder();
				while ((str = reader.readLine()) != null) {
					buf.append(str);
				}
				in.close();
				password = buf.toString();
			}
		}
		catch (Throwable t) {
			Toast.makeText(context.getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
		}
	}

}
