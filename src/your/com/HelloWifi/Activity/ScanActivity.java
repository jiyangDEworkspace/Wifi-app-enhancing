package your.com.HelloWifi.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import your.com.HelloWifi.BSSID;
import your.com.HelloWifi.BSSIDConnected;
import your.com.HelloWifi.BootService;
import your.com.HelloWifi.Building;
import your.com.HelloWifi.Calculate;
import your.com.HelloWifi.Data;
import your.com.HelloWifi.R;
import your.com.HelloWifi.School;
import your.com.HelloWifi.StoreFile;
import your.com.HelloWifi.Write;
import your.com.HelloWifi.WriteAlertDialog;
import your.com.HelloWifi.R.id;
import your.com.HelloWifi.R.layout;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends Activity{
	
	TabHost tabHost;
	
	//UI related
	TextView text;
	ImageView imageView;
	
	//wifi related
	WifiManager wifi;
	WifiManager wifi_local;
	List<ScanResult> scanResultList;
	List<WifiConfiguration> wifiConfigList;
	private String networkSSID;
	private ArrayList<ArrayList> WifiSum = new ArrayList<ArrayList>();
	private ArrayList<ArrayList> wifiInfo = new ArrayList<ArrayList>();
	public static int loopTime;
	
	private ProgressBar progressBar;
	private int status = 0;
	private Handler handler, ToastHandler;
	
	//self-define database related
	School Tsinghua = new School();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Setup UI
        setContentView(R.layout.activity_scan);
        
        TabActivity parent = (TabActivity) getParent();
        tabHost = parent.getTabHost();
        
        text = (TextView) findViewById(R.id.text);
        text.setMovementMethod(ScrollingMovementMethod.getInstance());
        //Typeface font = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Light.ttf");
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        text.setTypeface(font);
        //imageView = (ImageView) findViewById(R.id.Image2);
        loopTime = 20;
        progressBar = (ProgressBar) findViewById(R.id.progress);

        //Setup WiFi
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        progressBar.setMax(loopTime);
                
        //Check if run first time
        try {
			InputStream in = getApplicationContext().openFileInput("key.txt");
        }
        catch (java.io.FileNotFoundException e) {
        	 StoreFile storeFile = new StoreFile();
        	 storeFile.method(ScanActivity.this, true);
             //InitialDataBase();
        }
        InitialDataBase();
        //check if service existed
        checkBootService();
	}
	
	
	public void test(View v)
	{
//		if (true)
//			return;
//		tabHost.getTabWidget().setVisibility(View.GONE);
//		text.setText("Scanning...\n");
//		text.append("a new line?\n");
//		text.append("let's have a look");
		
		Time t = new Time();
		t.setToNow();
		String currentTime = t.year + "" + (t.month + 1) + ""+ t.monthDay + "_" + t.hour + "" + t.minute + "" + t.second;
		
		//Toast.makeText(getApplicationContext(), "test button", Toast.LENGTH_LONG).show();
		imageView.setAlpha(0);
		Log.d("image", imageView.toString());
//		Intent intent = new Intent(ScanActivity.this, DisplayActivity.class);
//		intent.putExtra("ScanResult", currentTime);
//		ScanActivity.this.startActivity(intent);
	}
	
	public void ResetLayout()
	{
		text.setText("Touch to Scan");
		tabHost.getTabWidget().setVisibility(View.VISIBLE);
		imageView.setAlpha(0);
		progressBar.setVisibility(View.INVISIBLE);
		
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void showAnimation(View view)  {
		
		//Toast.makeText(getApplicationContext(), "in animation", Toast.LENGTH_LONG).show();
		final float centerX = view.getWidth() / 2.0f;
	    final float centerY = view.getHeight() / 2.0f;
	    //starting angle, end angle
	    RotateAnimation rotateAnimation = new RotateAnimation(0, 1800, centerX, centerY); 
	    rotateAnimation.setDuration(1000 * 20);
	    rotateAnimation.setFillAfter(true);
	    
	    view.setAlpha(255);
	    Log.d("view",view.toString());
	    view.startAnimation(rotateAnimation);
	    
	    imageView = (ImageView) view;
		/*
		AnimationSet set = new AnimationSet(true);//define a set of the animations
	    AlphaAnimation alphaAnimation0 = new AlphaAnimation(0,1);// AlphaAnimation class
	    AlphaAnimation alphaAnimation1 = new AlphaAnimation(1,0);// AlphaAnimation class
	    AlphaAnimation alphaAnimation2 = new AlphaAnimation(1, 1);// AlphaAnimation class
		final float centerX = view.getWidth() / 2.0f; 
	    final float centerY = view.getHeight() / 2.0f; 
	    RotateAnimation rotateAnimation = new RotateAnimation(0, 3600, centerX, 
	            centerY); 

	    int time = 1000 * 20;
	    int alphtime = 5 * 1000;
	    rotateAnimation.setDuration(time); 
	    
	    alphaAnimation0.setDuration(alphtime);//set the duration
	    alphaAnimation2.setStartOffset(alphtime);
	    alphaAnimation2.setDuration(time-2*alphtime);

	    alphaAnimation1.setStartOffset(time-alphtime);
	    alphaAnimation1.setDuration(alphtime);	    

		set.addAnimation(rotateAnimation);
		set.addAnimation(alphaAnimation0);
		set.addAnimation(alphaAnimation2);
		set.addAnimation(alphaAnimation1);
	    
		set.setFillAfter(true);//the figure stay at the last frame
		
		view.setAnimation(set);//set the animation 
	    view.setAlpha(255);
		view.startAnimation(set);
		*/
		
	}
	
	public void scanWifi(View v) {   //do the scan work
    	int i = 0;
    	int waitNum = 0;
    	int flag = 0;
    	int connectNum = 0;
    	status = 0;
    	
    	//self-define database related
    	File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/WifiInfo/WifiData.dat");
    	if(!file.exists())
    		text.append("抱歉！未找到数据库\n");
    	else
    	{
    		//clear textview
    		showAnimation(v);
        	text.setText("Scanning...");
        	//to display a toast in the main thread
    		ToastHandler = new Handler() {
    			@Override
    			public void handleMessage(Message msg) {
    				//Toast.makeText(getApplicationContext(), "扫描即将开始，请耐心等待", Toast.LENGTH_LONG).show();
    				progressBar.setVisibility(1);
    				progressBar.setProgress(0);
    				
    			}
    		};
    		
    		//the new thread to update toast, bind with ToastHandler
    		new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					ToastHandler.sendMessage(ToastHandler.obtainMessage());
				}
    		}).start();
    		
    		handler = new Handler() {
    			@Override
    			public void handleMessage(Message msg) {
    				//text.append("from handler");
    				displayResult();
    			}
    		};
    	
    	    new Thread(new Runnable() {
			public void run() {
				int i = 0, flag = 0;
				// TODO Auto-generated method stub
				Log.d("thread", "entered");
				
				wifi.setWifiEnabled(false);
	    		wifi.setWifiEnabled(true);
	    		while (!wifi.isWifiEnabled())
	    			;
	    		
	    		try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Log.d("wrong thread", "in scan, sleep wrong");
				}
				
	    		wifiInfo.clear();
	    		for (int j = 0; j < 4; j++)
	    			wifiInfo.add(new ArrayList());
	    		
				while (status < loopTime) {
					try {
						Thread.sleep(300);
						wifi.startScan();
			        	scanResultList = wifi.getScanResults();
			        	for (ScanResult result : scanResultList) {
			    			//if(result.SSID.equals("Tsinghua"))
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
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					status++;
					handler.post(new Runnable() {
					public void run() {
						progressBar.setProgress(status);
						}
					});
				}
				Log.d("thread", "end");
				handler.sendMessage(handler.obtainMessage());
			}
    	    }).start();
    	}

    }
	
	//figure out the location with the result from method scanWifi()
	public void displayResult() {  
    	int i = 0;
    	String message = "";
    	Log.d("doScan", "entered");
    	
    	//以下是查找教室的代码
    	if(WifiSum.size()<1)
    	{
    		text.append( "抱歉,您所在的地点无定位所需wifi！\n"/* + WifiSum.size()*/);
    		message += "Sorry, your phone is in a non Wifi environment\n";
    	}
    	else
    	{
    		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/WifiInfo/WifiData.dat");
        	try{
        		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));    
        		Tsinghua = (School)ois.readObject();
        		ois.close(); 
        	}
        	catch(Exception e)
        	{
        		text.append("There is somrthing wrong reading your own database!");
        		e.printStackTrace();
        	}
	    	//text.append( WifiSum.get(0)+"\n");
        	//2013.10.5 edited
        	/*
	    	for(i=0;i<WifiSum.size();i++)
	    	{
	    		text.append(WifiSum.get(i).get(0).toString());	    		
	    	}
	    	*/
    		Data sample = new Data();
	    	sample  = Calculate.getSampleData(WifiSum);
	    	Building chosenBuildings = new Building();
	    	ArrayList<Data> chosenDatas = new ArrayList<Data>();
	    	//Data chosenData = new Data();
	    	ArrayList<Data> chosenData = new ArrayList<Data>();
	    	
	    	chosenBuildings = Tsinghua.chooseFromBuildings(sample.BSSIDStrongest[0]);
	    	if(chosenBuildings.name == null)
	    	{
	    		text.append( "抱歉！您所在的地点不在数据库中！\n");
	    		message += "Sorry, the location is not in your database\n";
	    	}
	    	else{
	       		text.append("类别：" + chosenBuildings.name+"\n");
	       		message += "Category: " + chosenBuildings.name + "\n";
	       		chosenDatas = chosenBuildings.firstChooseFromDatas(sample);
	       		if(chosenDatas.isEmpty())
	       		{
	       			text.append("抱歉！无法获取更详细的信息！\n");
	       		}
	       		else
	       		{
	       			text.append("初选：");
	       			message += "Level1: ";
	       			for(i = 0; i<chosenDatas.size(); i++)
	       			{
	       				text.append(i + ": " + chosenDatas.get(i).place + "	");
	       				message += chosenDatas.get(i).place + " ";
	       				if (i >= 2)
	       					break;
	       			}
	       			text.append("\n");
	       			message += "\n";
	       			chosenData = chosenBuildings.secondChooseFromDatas(sample,chosenDatas);
	       			if(chosenData.isEmpty())
	       			{
	       				text.append("抱歉！无法获取更详细的信息！\n");
	       			}
	       			else
	       			{
	       				text.append("精选：");
	       				message += "Level2: ";
	       				for(i = 0; i<chosenData.size(); i++)
	       				{
	       					text.append(i + ": " + chosenData.get(i).place + "	");
	       					message += chosenData.get(i).place + " ";
	       					if (i >= 2)
		       					break;
	       				}
	       				text.append("\n");
	       				message += "\n";
	       			}
	       		}
	    	}
    	}
    	Intent intent = new Intent(ScanActivity.this, DisplayActivity.class);
    	
		intent.putExtra("ScanResult", message);
		ScanActivity.this.startActivityForResult(intent, 1);
		//ScanActivity.this.startActivity(intent);
		
		//get system time
//		Time t = new Time();
//		t.setToNow();
//		String currentTime = t.year + "" + (t.month + 1) + ""+ t.monthDay + "_" + t.hour + "" + t.minute + "" + t.second;
		//onWrite(currentTime);
		
		//reset layout
		ResetLayout();
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         String result=data.getStringExtra("result");
		         onWrite(result);
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
		}//onActivityResult
	
	
	//There is a same "write" function in HelloWifi Activity
	public void Write(View v) {
    	WriteAlertDialog dialog = new WriteAlertDialog();
    	dialog.show(getFragmentManager(), "MyDF");
    }
	
	public void onWrite(String location) {
        // TODO add your implementation.
    	Log.d("onWrite", location);
    	
    	if (location.equals(""))
    		Toast.makeText(getApplicationContext(), "请输入地点", Toast.LENGTH_SHORT).show();
    	else {
    	Write writeFunction = new Write();
    	Log.d("location", location);
    	writeFunction.Write(WifiSum, wifiInfo, ScanActivity.this, location); 
    	
    	WifiSum.clear();
        wifiInfo.clear();
        Toast.makeText(getApplicationContext(), location + "已保存", Toast.LENGTH_SHORT).show();
        imageView.setEnabled(true);
        Log.d("flag", "ok");
    	}
    	
    }
	
	public void checkBootService()
	{
		Log.d("checkservice", "entered");
		Context context = getApplicationContext();
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager.
				 getRunningServices(Integer.MAX_VALUE);
		if(!(serviceList.size() > 0)) 
		{
			Log.d("checkservice", "number 0");
        	//Toast.makeText(this, "service  0", Toast.LENGTH_LONG).show();
			return;
		}
		for(int i = 0; i < serviceList.size(); i++) {
        	//two things can be imported here
            RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;
            Log.d("checkservice", serviceName.getClassName());
            if(serviceName.getClassName().equals("your.com.HelloWifi.BootService")) 
            {
            	//Toast.makeText(this, "service exist", Toast.LENGTH_LONG).show();
                return;
            }
		}
		context.startService(new Intent(context, BootService.class));
	}
	
	
	public void InitialDataBase()
	{
		//self-define database related
        Building selfDefined = new Building("自定义地点");
        Building library = new Building("library");
		Building thirdBuilding = new Building("Third Building");
		Building fourthBuilding = new Building("Fourth Building");
		
		
		library.existingBSSIDs = new ArrayList<String>(Arrays.asList(
				"04:c5:a4:08:ec:a0",
				"04:c5:a4:08:f6:f0",
				"04:c5:a4:09:4e:c0",
				"04:c5:a4:66:38:80",
				"44:e4:d9:00:7b:80",
				"44:e4:d9:01:04:f0",
				"44:e4:d9:40:2d:90",
				"44:e4:d9:84:e6:f0",
				"44:e4:d9:85:85:80"));
			
		thirdBuilding.datas.add(
				new Data("1101", 9, "10:8c:cf:10:80:20", "08:17:35:c7:f0:80", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("08:17:35:c7:f0:80", 70.112f, 1.218f, 3, 9),
				new BSSID("08:17:35:c7:f4:f0", 98.094f, 1.221f, 3, 5),
				new BSSID("08:17:35:c7:f5:80", 95.211f, 2.392f, 3, 10),
				new BSSID("10:8c:cf:10:7e:d0", 97.734f, 1.113f, 3, 5),
				new BSSID("10:8c:cf:10:80:20", 67.488f, 2.382f, 3, 13),
				new BSSID("10:8c:cf:11:66:70", 98.612f, 1.419f, 2, 6),
				new BSSID("10:8c:cf:11:66:80", 92.547f, 1.835f, 3, 10),
				new BSSID("10:8c:cf:11:67:50", 98.675f, 1.522f, 2, 6),
				new BSSID("10:8c:cf:11:72:40", 86.368f, 2.124f, 3, 13))),
				new BSSIDConnected("08:17:35:c7:f0:80", 70.432f, 0.964f, 8, 65.0f, 0.0f, 1)));
			
		thirdBuilding.datas.add(
				new Data("1102", 13, "10:8c:cf:10:7e:d0", "08:17:35:c7:f0:80", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("08:17:35:c7:f0:80", 81.716f, 1.862f, 3, 12),
				new BSSID("08:17:35:c7:f2:10", 86.524f, 1.811f, 3, 13),
				new BSSID("08:17:35:c7:f6:c0", 92.632f, 1.91f, 3, 11),
				new BSSID("10:8c:cf:10:7e:d0", 75.88f, 1.415f, 3, 11),
				new BSSID("10:8c:cf:10:80:20", 93.197f, 3.095f, 3, 14),
				new BSSID("10:8c:cf:11:65:70", 88.663f, 1.947f, 3, 11),
				new BSSID("10:8c:cf:11:66:80", 94.62f, 2.129f, 3, 8),
				new BSSID("10:8c:cf:11:69:00", 94.556f, 2.278f, 3, 9),
				new BSSID("10:8c:cf:11:6a:f0", 94.152f, 1.97f, 3, 11),
				new BSSID("10:8c:cf:11:6d:10", 92.752f, 2.199f, 3, 9),
				new BSSID("10:8c:cf:11:6d:50", 94.903f, 2.822f, 3, 12),
				new BSSID("10:8c:cf:11:6e:90", 98.725f, 1.634f, 2, 7),
				new BSSID("10:8c:cf:11:72:40", 98.5f, 4.5f, 1, 2))),
				new BSSIDConnected("10:8c:cf:10:7e:d0", 76.67f, 1.214f, 9, 63.046f, 4.677f, 4)));
			
			thirdBuilding.datas.add(
				new Data("1103", 9, "10:8c:cf:10:80:20", "08:17:35:c7:f5:80", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("08:17:35:c7:f0:80", 83.944f, 3.393f, 3, 17),
				new BSSID("08:17:35:c7:f4:f0", 99.0f, 1.0f, 1, 2),
				new BSSID("08:17:35:c7:f5:80", 81.452f, 2.496f, 3, 16),
				new BSSID("08:17:35:c7:f6:c0", 97.167f, 0.373f, 1, 2),
				new BSSID("10:8c:cf:10:7e:d0", 89.112f, 2.643f, 3, 13),
				new BSSID("10:8c:cf:10:80:20", 76.232f, 2.54f, 3, 13),
				new BSSID("10:8c:cf:11:65:70", 97.269f, 1.722f, 2, 7),
				new BSSID("10:8c:cf:11:66:80", 86.116f, 2.181f, 3, 13),
				new BSSID("10:8c:cf:11:72:40", 90.112f, 1.983f, 3, 12))),
				new BSSIDConnected("10:8c:cf:10:80:20", 77.214f, 2.437f, 14, 65.0f, 0.0f, 1)));
			
			fourthBuilding.datas.add(
					new Data("r4401", 8, "c0:62:6b:66:51:50", "04:c5:a4:66:72:d0", new ArrayList<BSSID>(Arrays.asList(
					new BSSID("04:c5:a4:08:4a:20", 97.286f, 1.335f, 2, 5),
					new BSSID("04:c5:a4:66:72:d0", 77.964f, 3.267f, 3, 14),
					new BSSID("04:c5:a4:66:74:80", 96.148f, 2.805f, 3, 11),
					new BSSID("04:c5:a4:80:12:10", 92.45f, 2.889f, 3, 12),
					new BSSID("44:e4:d9:3f:7f:d0", 93.256f, 2.724f, 3, 13),
					new BSSID("44:e4:d9:41:0c:00", 93.888f, 2.76f, 3, 10),
					new BSSID("c0:62:6b:66:51:50", 73.75f, 1.711f, 3, 9),
					new BSSID("c0:62:6b:e4:85:50", 95.108f, 1.855f, 3, 7))),
					new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			
			fourthBuilding.datas.add(
					new Data("r4402", 10, "c0:62:6b:e4:85:50", "04:c5:a4:66:74:80", new ArrayList<BSSID>(Arrays.asList(
					new BSSID("04:c5:a4:08:3c:c0", 91.582f, 2.036f, 3, 11),
					new BSSID("04:c5:a4:08:4a:20", 76.041f, 1.467f, 3, 10),
					new BSSID("04:c5:a4:66:70:b0", 93.832f, 1.715f, 3, 7),
					new BSSID("04:c5:a4:66:72:d0", 97.254f, 1.935f, 2, 5),
					new BSSID("04:c5:a4:66:74:80", 69.121f, 3.031f, 3, 11),
					new BSSID("04:c5:a4:66:a7:60", 95.472f, 2.089f, 3, 8),
					new BSSID("04:c5:a4:66:a8:e0", 94.4f, 1.2f, 1, 2),
					new BSSID("c0:62:6b:66:51:50", 94.813f, 2.254f, 3, 8),
					new BSSID("c0:62:6b:e4:80:c0", 96.777f, 2.052f, 3, 7),
					new BSSID("c0:62:6b:e4:85:50", 61.864f, 2.091f, 3, 11))),
					new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			
			//existingBSSIDs
			fourthBuilding.existingBSSIDs = new ArrayList<String>(Arrays.asList(
					"04:c5:a4:08:26:b0",
					"04:c5:a4:08:3a:a0",
					"04:c5:a4:08:3c:c0",
					"04:c5:a4:08:41:80",
					"04:c5:a4:08:4a:20"));
			thirdBuilding.existingBSSIDs = new ArrayList<String>(Arrays.asList(
					"08:17:35:c7:d4:80",
					"08:17:35:c7:f0:80",
					"08:17:35:c7:f2:10",
					"08:17:35:c7:f4:f0",
					"08:17:35:c7:f5:80"));
			
			Tsinghua.buildings.add(selfDefined);
			Tsinghua.buildings.add(library);
			Tsinghua.buildings.add(thirdBuilding);
			Tsinghua.buildings.add(fourthBuilding);
			
			//write database into SD
			File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/WifiInfo");
			if(!path.exists())
			{
				path.mkdir();
			}
	    	File file = new File(path,"WifiData.dat");
	    	if(!file.exists())
	    	{
	    		
	    		//text.append("edited\n");
	    		try {
					FileOutputStream out = new FileOutputStream(file,false);
					ObjectOutputStream s =  new ObjectOutputStream(out);
					s.writeObject(Tsinghua);
					s.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	}


}

