package your.com.HelloWifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelloWifiActivity extends Activity {
	//private static final String TAG = "HelloWifiActivity";
	WifiManager wifi;
	WifiManager wifi_local;
	//BroadcastReceiver receiver;
	TextView text;
	Button buttonScan;
	Button buttonStart;
	Button buttonStop;
	Button buttonCheck;
	List<ScanResult> scanResultList;
	List<WifiConfiguration> wifiConfigList;
	WifiInfo wifiInfo;
	Button buttonWrite;
	EditText editText;
	private String fileName;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Setup UI
        text = (TextView) findViewById(R.id.text);
        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        buttonWrite = (Button) findViewById(R.id.buttonWrite);
        editText = new EditText(this);
        
        //Setup WiFi
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        //Get WiFi status
        WifiInfo info = wifi.getConnectionInfo();
        text.append("\n\nWiFi Status: " + info.toString());
        
        //List availabe networks
        List<WifiConfiguration> configs = wifi.getConfiguredNetworks();
        for (WifiConfiguration config : configs) {
        	text.append("\n\n" + config.toString());
        }
        text.append("\n");
        
        /*
        //Register Broadcast Receiver
        if (receiver == null)
        	receiver = new WiFiScanReceiver(this);
        registerReceiver(receiver, new IntentFilter(
        		WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG, "onCreate()");
        */
    }
    
    public void startingWifi(View v) {
    	wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifi.setWifiEnabled(true);
		System.out.println("wifi state --->" + wifi.getWifiState());
		Toast.makeText(this, "startingwifi" + wifi.getWifiState(), Toast.LENGTH_SHORT).show();
		text.append("startingwifi" + wifi.getWifiState() + "\n");
    	
    }
    
    public void stoppingWifi(View v) {
    	
    	wifi.setWifiEnabled(false);
    	System.out.println("wifi state --->" + wifi.getWifiState());
		Toast.makeText(this, "stoppingwifi" + wifi.getWifiState(), Toast.LENGTH_SHORT).show();
		text.append("stoppingwifi" + wifi.getWifiState() + "\n");
    	
    }
    
    
    public void checkingWifi(View v) {
    	System.out.println("wifi state --->" + wifi.isWifiEnabled());
    	
		Toast.makeText(this, "checkingwifi " + wifi.isWifiEnabled(), Toast.LENGTH_LONG).show();
		text.append("\n----------------------------------------------------------\n");
		text.append("checking current wifi connection:\n");
		text.append("connected to:\n" + wifi.getConnectionInfo() + "\n");
		text.append("connetion's SSID = " + wifi.getConnectionInfo().getSSID() + "\n");
		
    }
    
    public void scanWifi(View v) {
    	int i = 0;
    	wifi.startScan();
    	scanResultList = wifi.getScanResults();
    	wifiConfigList = wifi.getConfiguredNetworks();
		text.append("\n----------------------------------------------------------\n");
    	text.append(scanResultList.size() + " scan result available:\n");
    	for (i = 0; i < scanResultList.size(); i++){
    		text.append("scanResult" + i + ":\n"+ scanResultList.get(i).toString() + "\n");
    		//Toast.makeText(this, "Wifi scan result" + (i) + ":\n" + scanResultList.get(i).toString(), Toast.LENGTH_LONG).show();
    	}
		text.append("\n----------------------------------------------------------\n");
		text.append("wifi configuration:\n");
    	for (i=0; i<wifiConfigList.size(); i++){
    		text.append("wifiConfig" + i + ":\n" + wifiConfigList.get(i).toString() + "\n");
    		//Toast.makeText(this, "Wifi config result" + (i) + ":\n" + wifiConfigList.get(i).toString() , Toast.LENGTH_LONG).show();
    	}
    	
    	
    	wifiInfo = wifi.getConnectionInfo();
    	text.append("wifiinfo = :\n" + wifiInfo.toString() + "\n\n\n");
    	
    	
    }
    
    public void write(View v) {
    	final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    	final String FILE_PATH = "/WifiInfo";
    	final File path = new File(SD_PATH + FILE_PATH);
        editText = new EditText(this);

    	AlertDialog editDialog = new AlertDialog.Builder(this).create();
    	editDialog.setView(editText);
    	editDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				fileName = editText.getText().toString() + ".txt";
				File file = new File(SD_PATH + FILE_PATH, fileName);
		    	if (!path.exists())
		    		path.mkdir();
		    	try {
		    		//InputStream is = (InputStream) text.getText();
		    		FileOutputStream os = new FileOutputStream(file,false);
		    		//byte[] data = new byte[is.available()];
		            //is.read(data);
		            os.write(text.getText().toString().getBytes());
		            //is.close();
		            os.close();
		            Toast.makeText(getApplicationContext(), fileName + "已保存", Toast.LENGTH_SHORT).show();
		    	}
		    	catch (IOException e) {
		            // Unable to create file, likely because external storage is
		            // not currently mounted.
		            Log.w("ExternalStorage", "Error writing " + file, e);
		        }
				
				
			}
		});
    	editDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
    	editDialog.setCancelable(false);
    	editDialog.setMessage("Please enter a finename");
    	editDialog.show();
    	
    }
    
    public void connect(View v) {
    	int i;
    	//wifi_local = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    	wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    	//Toast.makeText(this, "startingwifi" + wifi.getWifiState(), Toast.LENGTH_SHORT).show();
    	WifiConfiguration wc = new WifiConfiguration();
    	if (!wifi.isWifiEnabled()) {
    		AlertDialog dialog = new AlertDialog.Builder(this).create();
    		dialog.setButton("OK", new DialogInterface.OnClickListener() {
			
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    				dialog.cancel();
    				
    			}
    		});
    		dialog.setCancelable(false);
    		dialog.setTitle("Caution");
    		dialog.setMessage("Wifi is not started yet or being started, please start wifi or wait for a few seconds");
    		//dialog.setView(editText);
    		dialog.show();
    	}
    	else {
    	/*
    	for (i=0;i<scanResultList.size();i++){
    		Log.d("capabilities", scanResultList.get(i).capabilities);
    		if (scanResultList.get(i).capabilities != "[WPA-EAP-CCMP][WPA2-EAP-CCMP]"){
    			Log.d("true", scanResultList.get(i).capabilities);
    			break;
    		}
    	}
    	Log.d("i", "i = " + i );
    	wifi_local.disconnect();
    	ScanResult sr = scanResultList.get(i);
    	*/
    	//wifi.setWifiEnabled(true);
    	//Toast.makeText(this, "startingwifi" + wifi.getWifiState(), Toast.LENGTH_SHORT).show();
    	String networkSSID = "library";
    	wc.SSID = "\"" + networkSSID + "\"";
    	
    	//wc.preSharedKey  = "";
    	//wc.hiddenSSID = false;
    	wc.status = WifiConfiguration.Status.ENABLED;
    	wc.priority = 1;
    	wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
    	wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
    	wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
    	wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
    	wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
    	wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
    	wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
    	wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
    	wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    	
    	/*
    	wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
    	wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
    	*/
    	/*
    	List<WifiConfiguration> list = wifi.getConfiguredNetworks();
    	for( WifiConfiguration item : list ) {
    	    if(item.SSID != null && item.SSID.equals("\"" + sr.SSID + "\"")) {
    	         wifi_local.disconnect();
    	         wifi_local.enableNetwork(item.networkId, true);
    	         wifi_local.reconnect();                

    	         break;
    	    }           
    	 }
    	*/
    	
    	//boolean es = wifi.saveConfiguration();
        //Log.d("WifiPreference", "saveConfiguration returned " + es );
    	while (!wifi.isWifiEnabled())
    		;
    	int res = wifi.addNetwork(wc);
    	Log.d("WifiPreference", "add Network library returned " + res );
    	boolean b = wifi.enableNetwork(res, true);        
    	Log.d("WifiPreference", "enableNetwork library returned " + b );
    	if (b) {
    		while (wifi.getConnectionInfo().getSSID() == null) {
    			;
    		}
    		text.append("\n----------------------------------------------------------\n");
    		text.append("connected to:\n" + wifi.getConnectionInfo() + "\n");
    	}
    	String networkSSID2 = "Tsinghua";
    	wc.SSID = "\"" + networkSSID2 + "\"";
    	res = wifi.addNetwork(wc);
    	Log.d("WifiPreference", "add Network Tsinghua returned " + res );
    	b = wifi.enableNetwork(res, true);        
    	Log.d("WifiPreference", "enableNetwork Tsinghua returned " + b );
    	if (b) {
    		while (wifi.getConnectionInfo().getSSID() == null)
    			;
    		text.append("connected to:\n" + wifi.getConnectionInfo() + "\n");
    	}
    	
    	}
    }

}