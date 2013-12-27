package your.com.HelloWifi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class ScanService extends Service {
	WifiManager wifi;
	TimerTask timerTask1;
	Timer timer;
	String receipt;
	private ArrayList<ArrayList> WifiSum = new ArrayList<ArrayList>();
	private ArrayList<ArrayList> wifiInfo = new ArrayList<ArrayList>();
	public final int loopTime = 105;
	List<ScanResult> scanResultList;
	List<WifiConfiguration> wifiConfigList;
	String message = "";
	int count = 0;
	PowerManager powerManager;
	WakeLock wakeLock;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP|PowerManager.SCREEN_DIM_WAKE_LOCK, "");
		timer = new Timer();
		timerTask1 = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				count++;
				Log.d("timer", "timer alright");
				if (count >=5 && count <= 54) 
					scan();
				else if (count >= 55) {
					timer.cancel();
					Log.d("timer", "timer cancelled");
					locating();
					sendSMS();
					//stopSelf();
				}
					
			}
			
		};
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Toast.makeText(this, "service onStart", 0).show();
		Bundle bundle= intent.getExtras();
		receipt = bundle.getString("receipt");
		Log.d("receipt", receipt);
		Toast.makeText(this, receipt, 0).show();
		wakeLock.acquire();
		wifi.setWifiEnabled(false);
		wifi.setWifiEnabled(true);
		wifiInfo.clear();
		for (int j = 0; j < 4; j++)
    		wifiInfo.add(new ArrayList());
		
		timer.scheduleAtFixedRate(timerTask1, 30000, 500);
		
	}
	
	@Override
    public void onDestroy() {
		super.onDestroy();
		timer.cancel();
		wakeLock.release();
		Toast.makeText(this, "service onDestroy", Toast.LENGTH_LONG).show();
	}
	
	public void scan() {
		int i = 0;
    	int flag = 0;
    	wifi.startScan();
    	scanResultList = wifi.getScanResults();
    	wifiConfigList = wifi.getConfiguredNetworks();
    	
    	if (wifi.getConnectionInfo().equals(null))
    		Log.d("wifiinfo", "null");
    	if (wifiInfo.get(0).equals(new ArrayList())) {
    		Log.d("equal", "equal");
			wifiInfo.get(0).add(wifi.getConnectionInfo().getBSSID());
			wifiInfo.get(1).add(wifi.getConnectionInfo().getMacAddress());
    	}
    	
		wifiInfo.get(2).add(wifi.getConnectionInfo().getRssi());
		wifiInfo.get(3).add(wifi.getConnectionInfo().getLinkSpeed());
		for (ScanResult result : scanResultList) {
			
			if(result.SSID.equals("Tsinghua"))
			{
				for(ArrayList l : WifiSum)
				{
					if(l.get(0).equals(result.BSSID))
					{
						l.add(result.level);
						flag = 1;
					}
				}
				if(flag == 0)
				{
					flag = WifiSum.size();
					WifiSum.add(new ArrayList());
					WifiSum.get(flag).add(result.BSSID);
					WifiSum.get(flag).add(result.level);
				}
				flag = 0;
			}
		}
	}
	
	public void locating() {
		int i;
		//以下是查找教室的代码
		//Toast.makeText(this, "start locating", Toast.LENGTH_LONG).show();
		Log.d("message", "in locating");
		
    	Data sample = new Data();
    	sample  = Calculate.getSampleData(WifiSum);
    	Building chosenBuildings = new Building();
    	ArrayList<Data> chosenDatas = new ArrayList<Data>();
    	//Data chosenData = new Data();
    	ArrayList<Data> chosenData = new ArrayList<Data>();
    	
    	chosenBuildings = School.chooseFromBuildings(sample.BSSIDStrongest[0]);
    	//text.append( "\nThe strongest BSSID is " + sample.BSSIDStrongest[0]+"\n");
    	
    	//text.append("教学楼：您在 " + chosenBuildings.name+"\n");
    	message += "教学楼：" + chosenBuildings.name;
    	Log.d("message", message);
    	//text.append("您在 " + School.test_chooseFromBuildings(sample.BSSIDStrongest[0])+"\n");
    	chosenDatas = chosenBuildings.firstChooseFromDatas(sample);
    	message += "初选：";
    	Log.d("message", message);
    	//text.append("初选：您在 ");
    	for(i = 0; i<chosenDatas.size(); i++)
    	{
    		message += i + ": " + chosenDatas.get(i).place + "	";
    		//text.append(i + ": " + chosenDatas.get(i).place + "	");
    	}
    	chosenData = chosenBuildings.secondChooseFromDatas(sample,chosenDatas);
    	message += "精选：";
    	
    	//text.append("\n精选：您在 ");
    	
    	for(i = 0; i<chosenData.size(); i++)
    	{
    		message += i + ": " + chosenData.get(i).place + "	";
    		//text.append(i + ": " + chosenData.get(i).place + "	");
    	}
    	
    	Log.d("message", message);
    	//sendSMS();
    	/*
    	Intent scan = new Intent(null, GPSService.class);
		Bundle bundle1=new Bundle();
        bundle1.putString("receipt", receipt);
        scan.putExtras(bundle1);
        startService(scan);
    	stopSelf();
    	*/
	}
	
	public void sendSMS() {
		String SENT = "SMS_SENT";
    	String DELIVERED = "SMS_DELIVERED";
    	/*
    	PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
    	PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
    	//Log.d("phonenumber", phoneNo);
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
    	*/
    	String content = "";
    	content = message;
    	Log.d("sms", "in sms");
    	PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, ScanService.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        List<String> texts = sms.divideMessage(message);
        for (String text:texts) {
        	
        	Log.d("sms message", text);
        	sms.sendTextMessage(receipt, null, text, pi, null);
        }
        
        stopSelf();
	}

}
