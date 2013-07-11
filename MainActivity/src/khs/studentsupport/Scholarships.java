package khs.studentsupport;


import  khs.studentsupport.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Scholarships extends Activity {

	private ListView lv;
	private EditText et;
	private ArrayAdapter<String> adapter;
	String scholarshipNames[] = {"Scholarship One" , "Scholarship Two" , "Scholarship Three" , "Scholarship Four"};

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.scholarships);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		lv = (ListView) findViewById(R.id.scholarshipsListView);
		et = (EditText) findViewById(R.id.search_box);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scholarshipNames);
		
		lv.setAdapter(adapter);
		lv.setTextFilterEnabled(true);
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?>arg0, View v, int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(Scholarships.this);
				
				if (lv.getItemAtPosition(position) == scholarshipNames[0]) {
					adb.setTitle("Details for " + scholarshipNames[0]);
					adb.setMessage("Value: NZ$10,000\n" +
								   "Number Granted: 1\n" + 
								   "Application Close Date: February 2nd");
				}
				
				if (lv.getItemAtPosition(position) == scholarshipNames[1]) {
					adb.setTitle("Details for " + scholarshipNames[1]);
					adb.setMessage("Value: NZ$3,000\n"+
								   "Number Granted: 5\n"+ 
								   "Application Close Date: August 20th");
				}
				
				if (lv.getItemAtPosition(position) == scholarshipNames[2]) {
					adb.setTitle("Details for " + scholarshipNames[2]);
					adb.setMessage("Value: NZ$6,000\n" +
								  "Number Granted: 2\n" + 
								 "Application Close Date: February 2nd");
				}
				
				if (lv.getItemAtPosition(position) == scholarshipNames[3]) {
					adb.setTitle("Details for " + scholarshipNames[3]);
					adb.setMessage("Value: NZ$15,000\n" + 
									"Number Granted: 1\n" + 
									"Application Close Date: January 14th");
				}
				
				//Setting the okay button
				adb.setPositiveButton("OK", null);
				adb.show();
			}
			
	});
	
		et.addTextChangedListener(new TextWatcher(){

		
			public void afterTextChanged(Editable arg0) {
				
				
			}

		
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			
				
			}

			
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
				Scholarships.this.adapter.getFilter().filter(s);
				
			}
			
		});
}//end on create
	
}
