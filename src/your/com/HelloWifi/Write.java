package your.com.HelloWifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
		int i = 0;
		File file = new File(SD_PATH + FILE_PATH, fileName);
    	if (!path.exists())
    		path.mkdir();
    	try {
    		FileOutputStream os = new FileOutputStream(file,true);
    		os.write("\n-------------------------------------------------------------------------------------\n".getBytes());
    		os.write(("\n//" + currentTime + "\n").getBytes());
    		os.write(("example.place = \"" + location + "\";\n").getBytes());
            os.write(("example.BSSIDNum = " + WifiSum.size() + ";\n").getBytes());
            os.write("BSSID[] bs = new BSSID[example.BSSIDNum];\n\n".getBytes());
            for(ArrayList l : WifiSum)
			{
            	os.write(("bs[" + i + "] = new BSSID").getBytes());
				os.write(("(\"" + l.get(0).toString() + "\");\n").getBytes());
				os.write(("RSS.add(new int[]{").getBytes());
				os.write(l.get(1).toString().getBytes());
				for (int j = 2; j < l.size(); j++) {
					os.write(("," + l.get(j).toString()).getBytes());
				}
				os.write(("});\n").getBytes());
				i++;
			}
            
            if (wifiInfo.size() >= 1){
            	Log.d("wifiinfo", "enter");
            	os.write(("\nbsc.name = \"" + wifiInfo.get(0).get(0).toString() + "\";\n").getBytes());
	            os.write(("int RSS_con[] = new int[]{").getBytes());
	            os.write(wifiInfo.get(2).get(0).toString().getBytes());
	            for (int j = 1; j < wifiInfo.get(2).size(); j++)
	            	os.write(("," + wifiInfo.get(2).get(j).toString()).getBytes());
	            os.write("};\n".getBytes());
	            os.write("int Linkspeed[] = new int[]{".getBytes());
	            os.write(wifiInfo.get(3).get(0).toString().getBytes());
	            for (int j = 1; j < wifiInfo.get(3).size(); j++)
	            	os.write(("," + wifiInfo.get(3).get(j).toString()).getBytes());
	            os.write("};\n".getBytes());
	            /*
	            os.write(("BSSID:" + wifiInfo.get(0).toString()).getBytes());
	            os.write(("\nMAC:" + wifiInfo.get(1).toString()).getBytes());
	            os.write(("\nRSSI:" + wifiInfo.get(2).toString()).getBytes());
	            os.write(("\nLink Speed:" + wifiInfo.get(3).toString()).getBytes());
	            */
	            os.close();
	            
            }
            
    	}
    	catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
		
		/*
		AlertDialog editDialog = new AlertDialog.Builder(context).create();
		editText = new EditText(context);
    	editDialog.setView(editText);
    	editDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				location = editText.getText().toString();
				File file = new File(SD_PATH + FILE_PATH, fileName);
		    	if (!path.exists())
		    		path.mkdir();
		    	try {
		    		FileOutputStream os = new FileOutputStream(file,true);
		    		os.write("\n\n-------------------------------------------------------------------------------------\n".getBytes());
		            os.write((location + "\n").getBytes());
		            os.write((currentTime + "\n").getBytes());
		            os.write(("共有" + WifiSum.size() + "个搜索结果:\n").getBytes());
		            for(ArrayList l : WifiSum)
					{
						os.write((l.toString()+"\n").getBytes());
					}
		            
		            if (wifiInfo.size() >= 1){
		            	Log.d("wifiinfo", "enter");
		            	os.write("---------------------------------------------------------------------------------\n".getBytes());
			            os.write("wifiInfo:\n".getBytes());
			            os.write(("BSSID:" + wifiInfo.get(0).toString()).getBytes());
			            os.write(("\nMAC:" + wifiInfo.get(1).toString()).getBytes());
			            os.write(("\nRSSI:" + wifiInfo.get(2).toString()).getBytes());
			            os.write(("\nLink Speed:" + wifiInfo.get(3).toString()).getBytes());
			            os.close();
			            flag = true;
			            buttonScan.setEnabled(true);
			            if (flag)
			            	Log.d("write", "okok");
		            }
		            
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
				Log.d("cancel", "cancel");
				flag = false;
			}
		});
    	editDialog.setCancelable(false);
    	editDialog.setMessage("your location");
    	editDialog.show();
    	Log.d("sequence", "last");
    	
    	*/
	}
	
}
