package your.com.HelloWifi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Write{
	
	final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	final String FILE_PATH = "/WifiInfo";
	final File path = new File(SD_PATH + FILE_PATH);
	final String fileName = "WifiData.txt";
	
	EditText editText;
	//String location;
	Calendar time = Calendar.getInstance();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String currentTime = timestamp.toString();
	
	
	public void Write(final ArrayList<ArrayList> WifiSum, final ArrayList<ArrayList> wifiInfo, Context context, final String location){
		//location = et.getText().toString();
		School Tsinghua = new School();
		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/WifiInfo/WifiData.dat");
    	if(!file.exists())
    	{
    		Log.d("abc","DatFileNotFound");
    	}
    	else
    	{
    		ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				Tsinghua = (School)ois.readObject();
				ois.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			    
			try {
				if(WifiSum.size() > 0)
				{
					Data sample = new Data();
					sample = Calculate.getSampleData(WifiSum);   //
					sample.place = location;
					sample.BSSIDConnected = new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1);
					Tsinghua.buildings.get(0).datas.add(sample);
					Tsinghua.buildings.get(0).getExistingBSSIDs();
					FileOutputStream out = new FileOutputStream(file,false);
					ObjectOutputStream s =  new ObjectOutputStream(out);
					s.writeObject(Tsinghua);
					s.close();
				}
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
    	}
		
	}
	
}
