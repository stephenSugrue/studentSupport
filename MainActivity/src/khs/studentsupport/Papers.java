package khs.studentsupport;

import  khs.studentsupport.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Papers extends Activity  {

	private ListView lv;
	String paperNames[] = {"Paper One" , 
						   "Paper Two" ,
						   "Paper Three",
						   "Paper Four",
						   "Paper Five",
						   "Paper Six",
						   "Paper Seven"};

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.papers);
		lv = (ListView) findViewById(R.id.papersListView);
		
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paperNames));
		lv.setTextFilterEnabled(true);
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?>arg0, View v, int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(Papers.this);
				
				//Message details for BSNS 102
				if (lv.getItemAtPosition(position) == paperNames[0]){
					adb.setTitle("Details for " + paperNames[0]);
					adb.setMessage("Lectures: 2 x 50 minutes lectures per week \n\n" +
									"Assessments: \n" +
									"Assignments 50% \n" +
									"Final Exam 50%");
				}
				
				//Message details for BSNS 103
				if (lv.getItemAtPosition(position) == paperNames[1]){
					adb.setTitle("Details for " + paperNames[1]);
					adb.setMessage("Lectures: 2 x 50 minute lectures per week\n\n"+
								   "Assessments:\n" +
								   "Assigments 50% \n" +
								   "Final Exam 50%");
				}
				
				//Message details for BSNS 104
				if(lv.getItemAtPosition(position) == paperNames[2]){
					adb.setTitle("Details for " + paperNames[2]);
					adb.setMessage("Lectures: 3 x 50 minute per week\n\n" +
							       "Assessments:\n" +
							       "Assignments 50% \n" +
							       "Final Exam 50%");
				}
				
				//Message details for BSNS 105
				if(lv.getItemAtPosition(position) == paperNames[3]){
					adb.setTitle("Details for " + paperNames[3]);
					adb.setMessage("Lectures: 2 x 50 minute per week\n\n" +
							       "Assessments: \n" +
							       "Assignments 50%\n" +
							       "Final Exam 50%");
				}
				
				//Message details for BSNS 106
				if(lv.getItemAtPosition(position) == paperNames[4]){
					adb.setTitle("Details for " + paperNames[4]);
					adb.setMessage("Lectures: 2 x 50 minute per week\n\n" +
								  "Assessments:\n" +
								  "Assignments 50% \n" +
								  "Final Exam 50%");
				}
				
				//Message details for BSNS 107
				if(lv.getItemAtPosition(position) == paperNames[5]){
					adb.setTitle("Details for " + paperNames[5]);
					adb.setMessage("Lectures: 3 x 50 minute per week\n" +
									"Assessments: \n" + 
									"Assignments 50%\n" +
									"Final Exam 50%");
				}
				
				//Message details for BSNS 108
				if(lv.getItemAtPosition(position) == paperNames[6]){
					adb.setTitle("Details for " + paperNames[6]);
					adb.setMessage("Lectures: 3 x 50 minute per week\n" +
							      "Assessments:\n" +
							      "Assignments 50% \n" +
							      "Final Exam 35%");
				}
				
				//Setting the okay button
				adb.setPositiveButton("OK", null);
				adb.show();
			}
		});
	}//end on create
	
	

}
		
	