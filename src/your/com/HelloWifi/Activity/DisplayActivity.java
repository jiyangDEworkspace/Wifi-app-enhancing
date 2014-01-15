package your.com.HelloWifi.Activity;

import your.com.HelloWifi.R;
import your.com.HelloWifi.WriteAlertDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayActivity extends Activity{
	
	EditText et;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		TextView text = (TextView) findViewById(R.id.resultText2);
		Button write = (Button) findViewById(R.id.saveButton);
		et = (EditText) findViewById(R.id.editSave);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra("ScanResult");
		
		text.setText(message);
		
	}
	
	public void Write(View v) {
		Intent returnIntent = new Intent();
		returnIntent.putExtra("result", et.getText().toString());
		DisplayActivity.this.setResult(RESULT_OK, returnIntent);
		DisplayActivity.this.finish();
		
//    	WriteAlertDialog dialog = new WriteAlertDialog();
//    	dialog.show(getFragmentManager(), "MyDF");
    }
	
	

}
