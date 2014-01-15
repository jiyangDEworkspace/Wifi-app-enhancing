package your.com.HelloWifi.Activity;

import java.util.ArrayList;
import java.util.HashMap;

import your.com.HelloWifi.R;
import your.com.HelloWifi.StoreFile;
import your.com.HelloWifi.R.layout;
import your.com.HelloWifi.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SettingActivity extends Activity {
	
	HashMap<String, Object> map;
	
	public static final int SECURITY = 0;
	public static final int ADDRESS_SORTING = 1;
	public static final int IMPORT = 2;
	public static final int FREQUENCY = 3;
	public static final int HELP = 4;
	public static final int ABOUT = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		
		ListView list = (ListView) findViewById(R.id.settingList);
		
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>(); 
		
		map = new HashMap<String, Object>();
		map.put("settingTitle", "Security");
		listItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("settingTitle", "Address sorting");
		listItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("settingTitle", "Import/export");
		listItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("settingTitle", "Scan frequency");
		listItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("settingTitle", "Help");
		listItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("settingTitle", "About");
		listItem.add(map);
		
		
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.setting_item, new String[] {"settingTitle"},
				new int[] {R.id.settingTitle});
		
		list.setAdapter(listItemAdapter); 
		
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				switch (arg2) {
				case SECURITY:
					Security();
					return;
					
				case ADDRESS_SORTING:
					return;
					
				case FREQUENCY:
					return;
				case IMPORT:
					return;
					
				case HELP:
					Help();
					return;
					
				case ABOUT:
					return;
				
					
				}
				
				Toast.makeText(getApplicationContext(), "点击第"+arg2+"个项目", Toast.LENGTH_LONG).show();
			}
			
		});
	}
	
	public void Security()
	{
		StoreFile storeFile = new StoreFile();
   	 	storeFile.method(SettingActivity.this, false);
	}
	
	public void Help()
	{
		AlertDialog helpDialog = new AlertDialog.Builder(this).create();
		helpDialog.setTitle("Help");
		helpDialog.setMessage(getString(R.string.helpE));
		helpDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

}
