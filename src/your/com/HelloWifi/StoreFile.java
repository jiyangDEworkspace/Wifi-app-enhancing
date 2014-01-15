package your.com.HelloWifi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class StoreFile {
	EditText et;
	String key;
	public void method(final Context context, boolean flag) {
		if (flag)
			key = "chenjiyang";
		else {
			try {
				InputStream in = context.getApplicationContext().openFileInput("key.txt");
				if (in != null) {
					InputStreamReader tmp = new InputStreamReader(in);
					BufferedReader reader = new BufferedReader(tmp);
					String str;
					StringBuilder buf = new StringBuilder();
					while ((str = reader.readLine()) != null) {
						buf.append(str);
					}
					in.close();
					key = buf.toString();
				}
			}
			catch (java.io.FileNotFoundException e) {
				//
			}
			catch (Throwable t) {
				Toast.makeText(context.getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
			}
		}
		et = new EditText(context);
		AlertDialog.Builder dialog = new Builder(context);
		dialog.setTitle("请输入密匙");
		dialog.setMessage("密匙只能由英文和符号组成");
		dialog.setView(et);
		dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				if (et.getText().toString().equals("") && key.equals("chenjiyang")) {
					Toast.makeText(context, "设置为默认密匙：chenjiyang", Toast.LENGTH_SHORT).show();
					try {
						Toast.makeText(context, "key = " + key, 0).show();
			    		OutputStreamWriter out = new OutputStreamWriter(context.getApplicationContext().openFileOutput("key.txt", 0));
			    		out.write(key);
			    		out.close();
			    	}
			    	catch (Throwable t) {
			    		Toast.makeText(context, "Exception:" + t.toString(), Toast.LENGTH_SHORT).show();
			    	}
				}
				else if (et.getText().toString().equals(""))
					Toast.makeText(context, "输入无效！", Toast.LENGTH_SHORT).show();
				else {
					key = et.getText().toString();
					try {
						Toast.makeText(context, "key: " + key, 0).show();
			    		OutputStreamWriter out = new OutputStreamWriter(context.getApplicationContext().openFileOutput("key.txt", 0));
			    		out.write(key);
			    		out.close();
			    	}
			    	catch (Throwable t) {
			    		Toast.makeText(context, "Exception:" + t.toString(), Toast.LENGTH_SHORT).show();
			    	}
				}
					
			}
		});
		dialog.setNegativeButton("cancel", null);
		dialog.setCancelable(false);
		dialog.show();
		
		
	}
	/*
	public void write(Context context) {
		try {
			Toast.makeText(context, "key: " + key, 0).show();
    		OutputStreamWriter out = new OutputStreamWriter(context.getApplicationContext().openFileOutput("key.txt", 0));
    		out.write(key);
    		out.close();
    	}
    	catch (Throwable t) {
    		Toast.makeText(context, "Exception:" + t.toString(), Toast.LENGTH_SHORT).show();
    	}
	}
	*/
}
