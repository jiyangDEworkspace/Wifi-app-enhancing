package your.com.HelloWifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


/**
 * 
 * @author chenjiyang hetanjin bianyougang
 * @category to start the listening service at startup
 *
 */

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//for debugging purpose
		//Toast.makeText(context, "BootReceiver onReceive", Toast.LENGTH_LONG).show();
		Log.d("BootReceiver onReceive", "ok");
		if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
			//Toast.makeText(context, "startin boot service", Toast.LENGTH_LONG).show();
            context.startService(new Intent(context, BootService.class));
		}
		
	}
	

}
