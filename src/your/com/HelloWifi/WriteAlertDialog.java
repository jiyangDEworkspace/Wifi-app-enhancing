package your.com.HelloWifi;


import your.com.HelloWifi.Activity.ScanActivity;
import android.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;


/**
 * 
 * @author chenjiyang
 * @category to pop up a dialog allowing the user to input address
 *
 */

public class WriteAlertDialog extends DialogFragment {
	
	private String location;
	private EditText et, uName;

	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	// Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.writealertdialoglayout, null))
        	   .setMessage("Please input a name for this address")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                	   Dialog dialogView = (Dialog) dialog;
                	   EditText et;
                	   et = (EditText) dialogView.findViewById(R.id.addressinput);
                	   location = et.getText().toString();
                	   Log.d("WriteAlertDialog", location);
                	   //HelloWifiActivity callingActivity = (HelloWifiActivity) getActivity();
                	   ScanActivity callingActivity = (ScanActivity) getActivity();
                	   callingActivity.onWrite(location);
                	   
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Holo_Light_Panel);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}