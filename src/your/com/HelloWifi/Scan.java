package your.com.HelloWifi;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

//this class was prepared to build another activity when receiving a message. but this idea has been abandoned. maybe i'm just
//too sympethetic about this class and don't want it dye


public class Scan extends Activity {
	String receipt;
	private ArrayList<ArrayList> WifiSum = new ArrayList<ArrayList>();
	private ArrayList<ArrayList> wifiInfo = new ArrayList<ArrayList>();
	public final int loopTime = 55;
	WifiManager wifi;
	List<ScanResult> scanResultList;
	List<WifiConfiguration> wifiConfigList;
	String message = "";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devmode);
		
		Bundle bundle=getIntent().getExtras();
		receipt = bundle.getString("receipt");
		Toast.makeText(Scan.this, "in scan.class:" + receipt, 0).show();
		
		
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		/*
		wifi.setWifiEnabled(false);
    	wifi.setWifiEnabled(true);
    	while (!wifi.isWifiEnabled())
    		;
    	while (wifi.getConnectionInfo().equals(null))
    		;
    	//SystemClock.sleep(10000);
		*/
		scan();
		Log.d("tag", "finish scan");
		sendSMS();
		//super.finish();
	}
	
	
	
	
	public void sendSMS() {
		String SENT = "SMS_SENT";
    	String DELIVERED = "SMS_DELIVERED";
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
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(getBaseContext(), "SMS not delivered", 
                            Toast.LENGTH_SHORT).show();
                    break; 
				}
				
			}
    		
    	};
    	registerReceiver(sentReceiver, new IntentFilter(SENT));
    	registerReceiver(deliveredReceiver, new IntentFilter(DELIVERED));
    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(receipt, null, message, sentPI, deliveredPI);
	}
	
	
	public void scan() {
		int i = 0;
    	int flag = 0;
    	int connectNum = 0;
    	
    	wifiInfo.clear();
    	
    	wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);//打开wifi
		wifi.setWifiEnabled(true);
    	
    	for (int j = 0; j < 4; j++)
    		wifiInfo.add(new ArrayList());
    	for (i = 0; i < loopTime; i++){
    		wifi.startScan();
        	scanResultList = wifi.getScanResults();
        	wifiConfigList = wifi.getConfiguredNetworks();
        	if (i < 5)
        		continue;
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
        	
        	SystemClock.sleep(500);
    	}
    	
    	
    	//以下是查找教室的代码
    	Data sample = new Data();
    	sample  = Calculate.getSampleData(WifiSum);
    	Building chosenBuildings = new Building();
    	ArrayList<Data> chosenDatas = new ArrayList<Data>();
    	//Data chosenData = new Data();
    	ArrayList<Data> chosenData = new ArrayList<Data>();
    	
//    	chosenBuildings = School.chooseFromBuildings(sample.BSSIDStrongest[0]);
    	//text.append( "\nThe strongest BSSID is " + sample.BSSIDStrongest[0]+"\n");
    	
    	//text.append("教学楼：您在 " + chosenBuildings.name+"\n");
    	message += "教学楼：您在 " + chosenBuildings.name;
    	//text.append("您在 " + School.test_chooseFromBuildings(sample.BSSIDStrongest[0])+"\n");
    	chosenDatas = chosenBuildings.firstChooseFromDatas(sample);
    	message += "初选：您在 ";
    	//text.append("初选：您在 ");
    	for(i = 0; i<chosenDatas.size(); i++)
    	{
    		message += i + ": " + chosenDatas.get(i).place + "	";
    		//text.append(i + ": " + chosenDatas.get(i).place + "	");
    	}
    	chosenData = chosenBuildings.secondChooseFromDatas(sample,chosenDatas);
    	message += "精选：您在 ";
    	//text.append("\n精选：您在 ");
    	for(i = 0; i<chosenData.size(); i++)
    	{
    		message += i + ": " + chosenData.get(i).place + "	";
    		//text.append(i + ": " + chosenData.get(i).place + "	");
    	}
	}
	

}
