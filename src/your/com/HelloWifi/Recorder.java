package your.com.HelloWifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.os.Environment;
import android.util.Log;

public class Recorder
{
	public static void recorder(String s)
	{
		final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
		final String FILE_PATH = "/WifiInfo";
		final File path = new File(SD_PATH + FILE_PATH);
		final String fileName = "score_recorder.txt";

		File file = new File(SD_PATH + FILE_PATH, fileName);
	    if (!path.exists())
	    	path.mkdir();
	    try 
		{
	    	FileOutputStream recorder = new FileOutputStream(file,true);
			recorder.write(s.getBytes());
	        recorder.close();
		 }
	    catch (IOException e) 
		{
	        Log.w("ExternalStorage", "Error writing " + file, e);
	    }			
	}
}