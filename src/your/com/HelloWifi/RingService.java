package your.com.HelloWifi;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

public class RingService extends Service {
	MediaPlayer mediaPlayer;
	Uri ring;
	TimerTask timerTask;
	private static Timer timer = new Timer();

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, "ring service onCreate", Toast.LENGTH_LONG).show();
		AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int max2 = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max2, 0);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, max, 0);
		ring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mediaPlayer = new MediaPlayer();
        timer = new Timer();
		timerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				mediaPlayer.release();
				stopSelf();
			}
			
		};
        
		
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Toast.makeText(this, "ring service onStart", Toast.LENGTH_LONG).show();
		
		try {
			mediaPlayer.setDataSource(this, ring);
			mediaPlayer.setLooping(true);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.toString(), 0).show();
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.toString(), 0).show();
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.toString(), 0).show();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.toString(), 0).show();
			e.printStackTrace();
		}
		timer.schedule(timerTask, 10000);
	}
	
	@Override
    public void onDestroy() {
		super.onDestroy();
		
		Toast.makeText(this, "ring service onDestroy", Toast.LENGTH_LONG).show();
	}
	

}
