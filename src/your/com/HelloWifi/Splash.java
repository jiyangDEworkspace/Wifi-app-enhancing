package your.com.HelloWifi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	int delay = 500;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Runnable r = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				Intent mainIntent = new Intent(Splash.this, HelloWifiActivity.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
			}
        	
        };
        new Handler().postDelayed(r, delay);
        
	}

}
