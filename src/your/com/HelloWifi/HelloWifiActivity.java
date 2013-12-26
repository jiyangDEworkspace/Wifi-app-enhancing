package your.com.HelloWifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
	WifiManager wifi;
	WifiManager wifi_local;
	TextView text;
	Button buttonScan;
	Button buttonStart;
	Button buttonStop;
	Button buttonCheck;
	List<ScanResult> scanResultList;
	List<WifiConfiguration> wifiConfigList;
	Button buttonWrite;
	EditText editText;
	EditText et; //有一个EditEext是随时创建的，怕弄混了
	private String location;
	public static final int MENU_CLOSE = Menu.FIRST + 3;
	public static final int MENU_CHANGE = Menu.FIRST + 1;
	public static final int MENU_EDIT = Menu.FIRST + 2;
	private String networkSSID;
	private ArrayList<ArrayList> WifiSum = new ArrayList<ArrayList>();
	private ArrayList<ArrayList> wifiInfo = new ArrayList<ArrayList>();
	Calendar time = Calendar.getInstance();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String currentTime = timestamp.toString();
	public int loopTime;
	
	
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
        et = (EditText) findViewById(R.id.et);
        editText = new EditText(this);
        
        loopTime = 105;
        //Setup WiFi
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        //Check if run first time
        try {
			InputStream in = getApplicationContext().openFileInput("key.txt");
        }
        catch (java.io.FileNotFoundException e) {
        	 StoreFile storeFile = new StoreFile();
        	 storeFile.method(HelloWifiActivity.this);
        	 storeFile.write(HelloWifiActivity.this);
        }
        
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
    	for (i = 0; i < loopTime; i++){
    		wifi.startScan();
        	scanResultList = wifi.getScanResults();
        	wifiConfigList = wifi.getConfiguredNetworks();
        	if (i < 5)
        		continue;
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
    	//for (ArrayList list : wifiInfo)
    		//text.append(list.toString());
    	buttonScan.setEnabled(false);
    	
    	//以下是查找教室的代码
    	Data sample = new Data();
    	sample  = Calculate.getSampleData(WifiSum);
    	Building chosenBuildings = new Building();
    	ArrayList<Data> chosenDatas = new ArrayList<Data>();
    	//Data chosenData = new Data();
    	ArrayList<Data> chosenData = new ArrayList<Data>();
    	
    	chosenBuildings = School.chooseFromBuildings(sample.BSSIDStrongest[0]);
    	text.append( "\nThe strongest BSSID is " + sample.BSSIDStrongest[0]+"\n");
    	text.append("教学楼：您在 " + chosenBuildings.name+"\n");
    	//text.append("您在 " + School.test_chooseFromBuildings(sample.BSSIDStrongest[0])+"\n");
    	chosenDatas = chosenBuildings.firstChooseFromDatas(sample);
    	text.append("初选：您在 ");
    	for(i = 0; i<chosenDatas.size(); i++)
    	{
    		text.append(i + ": " + chosenDatas.get(i).place + "	");
    	}
    	chosenData = chosenBuildings.secondChooseFromDatas(sample,chosenDatas);
    	text.append("\n精选：您在 ");
    	for(i = 0; i<chosenData.size(); i++)
    	{
    		text.append(i + ": " + chosenData.get(i).place + "	");
    	}

    	
    }
    
    public void write(View v) {
    	location = et.getText().toString();
    	if (location.equals(""))
    		Toast.makeText(getApplicationContext(), "请输入地点", Toast.LENGTH_SHORT).show();
    	else {
    	Write writeFunction = new Write();
    	writeFunction.Write(WifiSum, wifiInfo, HelloWifiActivity.this, location);    	
    	
    	WifiSum.clear();
        wifiInfo.clear();
        Toast.makeText(getApplicationContext(), location + "已保存", Toast.LENGTH_SHORT).show();
        buttonScan.setEnabled(true);
        Log.d("flag", "ok");
    	}
    	
    	
    }
    
    public void connect(View v) {
    	int i;
    	wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
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
		
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, MENU_CLOSE, Menu.NONE, "close");
    	menu.add(Menu.NONE, MENU_CHANGE, Menu.NONE, "Change");
    	menu.addSubMenu(Menu.NONE, MENU_EDIT, Menu.NONE, "edit");
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case MENU_CLOSE:
    		//finish();
    		super.finish();
    		return true;
    	case MENU_CHANGE:
    	{
    		AlertDialog loopDialog = new AlertDialog.Builder(this).create();
    		editText = new EditText(this);
    		loopDialog.setView(editText);
    		loopDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			
    			public void onClick(DialogInterface dialog, int which) {
    				loopTime = Integer.parseInt(editText.getText().toString());
    			}
    		});
    		
    		loopDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			
    		loopDialog.setCancelable(false);
    		loopDialog.setTitle("Caution");
    		loopDialog.setMessage("choose scan time");
    		//dialog.setView(editText);
    		loopDialog.show();
    		}
    		return true;
    		
    	}
    	return super.onOptionsItemSelected(item);
    }

}