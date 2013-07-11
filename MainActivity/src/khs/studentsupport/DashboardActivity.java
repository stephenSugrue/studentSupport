
package khs.studentsupport;

import khs.supportapp.library.UserFunctions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import khs.studentsupport.R;

public class DashboardActivity extends Activity {
	UserFunctions userFunctions;
	Button btnLogout;//logout button
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Dash-board Screen for the application
         * */        
     //////////////////////// ////// Check login status in database/////////////////////////////////////
        userFunctions = new UserFunctions();
        //if the users details are correct proceed to main screen 
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        	setContentView(R.layout.dashboard);
        	btnLogout = (Button) findViewById(R.id.btnLogout);
        	
        	 btnLogout.setOnClickListener(new View.OnClickListener() {
                 
                 public void onClick(View arg0) {
                    
                     userFunctions.logoutUser(getApplicationContext());
                     Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                     login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(login);
                     // Closing dash-board screen
                     finish();
                 }
             });
        	 
        	 
        	 
        	/////////////// //////On click listener for the papers button////////////////////////
        	 Button papersBtn = (Button) findViewById(R.id.papersButton);
        	
     		papersBtn.setOnClickListener(new View.OnClickListener() {
     			public void onClick(View v) {
     				Intent btnIntent = new Intent(getApplicationContext(), Papers.class);
         			startActivity(btnIntent);
     			}
     		});
     		
     		
     		  //////////////////////On click listener for the contacts button/////////////////////
     	    Button peoplebtn = (Button) findViewById(R.id.notificationsButton);
     	   	peoplebtn.setOnClickListener(new View.OnClickListener() {
      			public void onClick(View v) {
      				Intent btnIntent = new Intent(getApplicationContext(), AllContactsActivity.class);
          			startActivity(btnIntent);
      			}
      		});
     		
     		//////////////////////On click listener for the calendar button////////////////////////
			Button calBtn = (Button) findViewById(R.id.calButton);
			calBtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {					
				Intent btnIntent = new Intent(getApplicationContext(), CalendarView.class);
	    		startActivity(btnIntent);	
				}
			});
			
			
			////////////////////////On click listener for contacts button////////////////////////////
			Button contactsBtn = (Button) findViewById(R.id.contactsButton);
			contactsBtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					Intent btnIntent = new Intent(getApplicationContext(), Contacts.class);
					startActivity(btnIntent);
				}
			});
			
           /////////////////On click listener for the scholarships button/////////////////////////
			Button scholarshipsBtn = (Button) findViewById(R.id.scholarshipsButton);
			scholarshipsBtn.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
				
					Intent btnIntent = new Intent(getApplicationContext(), Scholarships.class);
					startActivity(btnIntent);
				}
			});
			
			
			//////////////////On click listener for the videos button////////////////////
			Button videosBtn = (Button) findViewById(R.id.videosButton);
			videosBtn.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					
					Intent btnIntent = new Intent(getApplicationContext(), Videos.class);
					startActivity(btnIntent);
				}
			});
			
        }
        else{
        	/////////////////// user is not logged in show login screen////////////////////////
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dash-board screen
        	finish();
        }
  
    }
}