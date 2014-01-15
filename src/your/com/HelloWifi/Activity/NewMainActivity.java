package your.com.HelloWifi.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


@SuppressWarnings("deprecation")
public class NewMainActivity extends TabActivity {
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tabhost);
        
        // Setup UI
        TabHost tabHost = getTabHost();
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Scan")  
                .setContent(new Intent(this, ScanActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Address")  
                .setContent(new Intent(this, ListActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Settings")  
                .setContent(new Intent(this, SettingActivity.class)));
        
    }    
    
}
