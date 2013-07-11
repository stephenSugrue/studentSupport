package khs.studentsupport;

import  khs.studentsupport.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Thread splashTimer = new Thread(){
        	public void run(){
        		try{
        			sleep(1000);
        			Intent menuIntent = new Intent(getApplicationContext(), LoginActivity.class);
        			startActivity(menuIntent);
        		} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
        		
        		finally{
        			finish();
        		}
        	}
        };
        splashTimer.start();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/
}
