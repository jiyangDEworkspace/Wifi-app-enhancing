package your.com.HelloWifi.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import your.com.HelloWifi.R;
import your.com.HelloWifi.School;
import your.com.HelloWifi.R.id;
import your.com.HelloWifi.R.layout;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListActivity extends Activity  {
	School Tsinghua = new School();
    ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>> ();
    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/WifiInfo/WifiData.dat");
    //SimpleAdapter mSchedule = new SimpleAdapter(this, myList, R.layout.item, new String[] {"title", "text"}, new int[] {R.id.title,R.id.text});
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	int i, j;
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView list = (ListView) findViewById(R.id.MyListView);

        try{
    		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
    		Tsinghua = (School)ois.readObject(); 
    		ois.close(); 
    	}
    	catch(Exception e)
    	{
    		Toast.makeText(getApplicationContext(),"There is something wrong reading your own database!",Toast.LENGTH_SHORT).show();
    		e.printStackTrace();
    	}
    	for(i = 0; i < Tsinghua.buildings.size();i++)
    	{
    		for(j = 0; j < Tsinghua.buildings.get(i).datas.size(); j++)
    		{
    			HashMap<String, String> map = new HashMap<String, String>();
    			map.put("title", Tsinghua.buildings.get(i).datas.get(j).place);
    			map.put("text", Tsinghua.buildings.get(i).name);
    			map.put("buildingNum", String.valueOf(i));
    			map.put("dataNum",String.valueOf(j));
    			myList.add(map);
    		}        		
    	}	

    	final SimpleAdapter mSchedule = new SimpleAdapter(this, myList, R.layout.item, new String[] {"title", "text"}, new int[] {R.id.title,R.id.text});
        list.setAdapter(mSchedule);
        list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
			{
				// TODO Auto-generated method stub
				final int buildingNum = Integer.valueOf(myList.get(arg2).get("buildingNum"));
				final int dataNum = Integer.valueOf(myList.get(arg2).get("dataNum"));
				final int location = arg2;
				AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
		        builder.setMessage("Delete this address?")
		               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   Tsinghua.buildings.get(buildingNum).datas.remove(dataNum);
		               			try {
		               				FileOutputStream out = new FileOutputStream(file,false);
		               				ObjectOutputStream s =  new ObjectOutputStream(out);
		               				s.writeObject(Tsinghua);
		               				s.close();
		               			} 
		               			catch (Exception e) {
		               				// TODO Auto-generated catch block
		               				e.printStackTrace();
		               			}
		                	   Toast.makeText(getApplicationContext(),"Address removed", Toast.LENGTH_LONG).show();
		                	   myList.remove(location);
		                	   mSchedule.notifyDataSetChanged();
		                   }
		               })
		               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                       // User cancelled the dialog
		                   }
		               });
		        AlertDialog dialog = builder.create();
		        dialog.show();
				
				//Toast.makeText(getApplicationContext(),buildingNum+ " " + dataNum, Toast.LENGTH_LONG).show();
				//Tsinghua.buildings.get(buildingNum).datas.remove(dataNum);
				//myList.remove(arg2);
				//showingDialog(arg2);
				//mSchedule.notifyDataSetChanged();
		
				
			}
        });
    }
    
    /*
    private void showingDialog(int location)
    {
    	final int temp = location;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this address?")
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Toast.makeText(getApplicationContext(),"Address removed", Toast.LENGTH_LONG).show();
                	   myList.remove(temp);
                	   //mSchedule.notifyDataSetChanged();
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    */
   
}
