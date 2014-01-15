package your.com.HelloWifi;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Ring extends Activity{
	MediaPlayer mediaPlayer;
	Uri ring;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devmode);
		AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int max2 = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max2, 0);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, max, 0);
		ring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mediaPlayer = new MediaPlayer();
        
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
		
	}
	
	public void stop(View v) {
		mediaPlayer.stop();
		mediaPlayer.release();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		mediaPlayer.stop();
		mediaPlayer.release();
	}

}
