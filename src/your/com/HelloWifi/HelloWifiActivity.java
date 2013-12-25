package your.com.HelloWifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
	//WifiInfo wifiInfo;
	Button buttonWrite;
	EditText editText;
	private String location;
	public static final int MENU_CLOSE = Menu.FIRST+1;
	//Spinner spin;
	private String networkSSID;
	private ArrayList<ArrayList> WifiSum = new ArrayList<ArrayList>();
	private ArrayList<ArrayList> wifiInfo = new ArrayList<ArrayList>();
	
	
	
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
        //text.append("\n\nWiFi Status: " + info.toString());
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
    	int flag = 0;
    	int connectNum = 0;
    	wifiInfo.clear();
    	for (int j = 0; j < 4; j++)
    		wifiInfo.add(new ArrayList());
    	for (i = 0; i < 20; i++){
    	wifi.startScan();
    	scanResultList = wifi.getScanResults();
    	wifiConfigList = wifi.getConfiguredNetworks();
		//text.append("\n----------------------------------------------------------\n");
    	//text.append(scanResultList.size() + " scan result available:\n");
    	//for (i = 0; i < scanResultList.size(); i++){
    		//text.append("scanResult" + i + ":\n"+ scanResultList.get(i).toString() + "\n");
    		//Toast.makeText(this, "Wifi scan result" + (i) + ":\n" + scanResultList.get(i).toString(), Toast.LENGTH_LONG).show();
    	//}
    	
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
					WifiSum.get(flag).add(result.frequency);
					WifiSum.get(flag).add("\nLevel: " + result.level);
				}
				flag = 0;
			}
		}
    	
    	SystemClock.sleep(500);
    	}
    	for (ArrayList list : wifiInfo)
    		text.append(list.toString());
    	buttonScan.setEnabled(false);
    	
    }
    
    public void write(View v) {
    	final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    	final String FILE_PATH = "/WifiInfo";
    	final File path = new File(SD_PATH + FILE_PATH);
    	final String fileName = "WifiData.txt";
        editText = new EditText(this);

    	AlertDialog editDialog = new AlertDialog.Builder(this).create();
    	editDialog.setView(editText);
    	editDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				location = editText.getText().toString();
				File file = new File(SD_PATH + FILE_PATH, fileName);
		    	if (!path.exists())
		    		path.mkdir();
		    	try {
		    		FileOutputStream os = new FileOutputStream(file,false);
		    		os.write("\n\n-------------------------------------------------------------------------------------\n".getBytes());
		            os.write((location + "\n").getBytes());
		            os.write(("共有" + WifiSum.size() + "个搜索结果:\n").getBytes());
		            for(ArrayList l : WifiSum)
					{
						os.write((l.toString()+"\n").getBytes());
					}
		            os.write("---------------------------------------------------------------------------------\n".getBytes());
		            os.write("wifiInfo:\n".getBytes());
		            os.write(("BSSID:" + wifiInfo.get(0).toString()).getBytes());
		            os.write(("\nMAC:" + wifiInfo.get(1).toString()).getBytes());
		            os.write(("\nRSSI:" + wifiInfo.get(2).toString()).getBytes());
		            os.write(("\nLink Speed:" + wifiInfo.get(3).toString()).getBytes());
		            os.close();
		            WifiSum.clear();
		            wifiInfo.clear();
		            Toast.makeText(getApplicationContext(), location + "已保存", Toast.LENGTH_SHORT).show();
		        	buttonScan.setEnabled(true);
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
    		wifi.startScan();
	    	scanResultList = wifi.getScanResults();
	    	ArrayList<String> wifiList = new ArrayList<String>();
	    	boolean flag = false;
	    	for (ScanResult result : scanResultList) {
	    		flag = false;
	    		for (String item : wifiList) {
	    			if (item.equals(result.SSID)){
	    				flag = true;
	    				Log.d("item", "the same");
	    				break;
	    				
	    			}
	    		}
	    		if (flag == false){
	    			wifiList.add(result.SSID);
	    			Log.d("Spinner", result.SSID);
	    		}
	    		
	    		
	    	}
	    	
	    final String[] wifiString = new String[wifiList.size()];
	    wifiList.toArray(wifiString);
	    networkSSID = wifiString[0];
		AlertDialog.Builder netDialog = new AlertDialog.Builder(this);
		netDialog.setSingleChoiceItems(wifiString, 0, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				networkSSID = wifiString[id];
			}
		});
		netDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		netDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
		    	WifiConfiguration wc = new WifiConfiguration();
				wc.SSID = "\"" + networkSSID + "\"";
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
		    	
		    	while (!wifi.isWifiEnabled())
		    		;
		    	int res = wifi.addNetwork(wc);
		    	Log.d("WifiPreference", "add Network returned " + res );
		    	boolean b = wifi.enableNetwork(res, true);
		    	Log.d("WifiPreference", "enableNetwork returned " + b );
		    	if (b) {
		    		while (wifi.getConnectionInfo().getSSID() == null) {
		    			;
		    		}
		    		Toast.makeText(getApplicationContext(), "connected to " + networkSSID, Toast.LENGTH_SHORT).show();
		    		text.append("\n----------------------------------------------------------\n");
		    		text.append("connected to:\n" + wifi.getConnectionInfo() + "\n");
		    	}
			}
		});
		netDialog.setTitle("请输入想要连接的wifi名称：");
		netDialog.show();
		
    	/*
    	//wc.preSharedKey  = "";
    	//wc.hiddenSSID = false;
    	
    	wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
    	wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
    	
    	List<WifiConfiguration> list = wifi.getConfiguredNetworks();
    	for( WifiConfiguration item : list ) {
    	    if(item.SSID != null && item.SSID.equals("\"" + sr.SSID + "\"")) {
    	         wifi_local.disconnect();
    	         wifi_local.enableNetwork(item.networkId, true);
    	         wifi_local.reconnect();                

    	         break;
    	    }           
    	 }
    	
    	
    	//boolean es = wifi.saveConfiguration();
        //Log.d("WifiPreference", "saveConfiguration returned " + es );
    	*/
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, MENU_CLOSE, Menu.NONE, "close");
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case MENU_CLOSE:
    		//finish();
    		super.finish();
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

}