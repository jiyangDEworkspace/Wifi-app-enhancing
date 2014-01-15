package your.com.HelloWifi;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class GPSService extends Service {
	LocationListener locationListener;
	LocationManager locationManager;
	String message = "";
	String receipt = "";
	String provider;
	WifiManager wifi;
	PowerManager powerManager;
	WakeLock wakeLock;
	Timer timer;
	TimerTask timerTask;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP|PowerManager.SCREEN_DIM_WAKE_LOCK, "");
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  
            //Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            message += "ditu.google.cn/?q=" + lat + "," + lng;
            Log.d("first try", message);
        }
        else {
            message += "无法获取地理信息";
        }
		
        locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				if (location != null) {
		            double lat = location.getLatitude();
		            double lng = location.getLongitude();
		            message = "ditu.google.cn/?q=" + lat + "," + lng;
		            Log.d("onLocationChanged", message);
		            
		        }
		        else {
		            message = "无法获取地理信息";
		        }
				Log.d("tag", "in locationListener");
			}
			public void onProviderDisabled(String arg0) {
			}
			public void onProviderEnabled(String arg0) {
			}
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {				
			}
        };
        timer = new Timer();
		timerTask = new TimerTask() {

			@Override
			public void run() {
				sendSMS();
			}
		};
       
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Toast.makeText(this, "GPS service onStart", 0).show();
		Bundle bundle= intent.getExtras();
		receipt = bundle.getString("receipt");
		Log.d("receipt", receipt);
		wakeLock.acquire();
		locationManager.requestLocationUpdates(provider, 1000, 1, locationListener);
		timer.schedule(timerTask, 60000);
	}
	
	public void sendSMS() {
		String SENT = "SMS_SENT";
    	String DELIVERED = "SMS_DELIVERED";
    	PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
    	PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
    	Log.d("sendSMS", "in sndSMS");
    	//Log.d("message", message);
    	
    	BroadcastReceiver sentReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch(getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS sent", 
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(getBaseContext(), "Generic failure", 
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    Toast.makeText(getBaseContext(), "No service", 
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    Toast.makeText(getBaseContext(), "Null PDU", 
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(getBaseContext(), "Radio off", 
                            Toast.LENGTH_SHORT).show();
                    break;
				}
			}
    		
    	};
    	
    	BroadcastReceiver deliveredReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				switch(getResultCode()) {
				case Activity.RESULT_OK:
                    Toast.makeText(getBaseContext(), "SMS delivered", 
                            Toast.LENGTH_SHORT).show();
                    stopSelf();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(getBaseContext(), "SMS not delivered", 
                            Toast.LENGTH_SHORT).show();
                    stopSelf();
                    break;
				}
				
			}
    		
    	};
    	registerReceiver(sentReceiver, new IntentFilter(SENT));
    	registerReceiver(deliveredReceiver, new IntentFilter(DELIVERED));
    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(receipt, null, message, sentPI, deliveredPI);
    	//unregisterReceiver(sentReceiver);
    	
	}
	
	@Override
	public void onDestroy() {
		locationManager.removeUpdates(locationListener);
		wifi.setWifiEnabled(true);
		wakeLock.release();
		
	}

}
