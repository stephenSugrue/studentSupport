package khs.studentsupport;

import  khs.studentsupport.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Videos extends Activity{

private ListView lv;
String videoNames[] = {"Doctor Who Trailer", "The Doctor - Regenerations", "Doctor Who: The Best of David Tennant"};

public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.videos);
    lv = (ListView) findViewById(R.id.videosListView);

    lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, videoNames));
    lv.setTextFilterEnabled(true);

    lv.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?>Videos, View v, int position, long id) {

        	//FIRST VIDEO
        if (lv.getItemAtPosition(position) == videoNames[0] ) {	
    String video_path = "http://www.youtube.com/watch?v=qrEUBl2pacU";
    Uri uri = Uri.parse(video_path);

    uri = Uri.parse("vnd.youtube: " + uri.getQueryParameter("v"));

    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(intent);
        }//end if
        
        //SECOND VIDEO
        if (lv.getItemAtPosition(position) == videoNames[1]) {
        	  String video_path = "http://www.youtube.com/watch?v=uXCpY_3Sac8";
        	    Uri uri = Uri.parse(video_path);

        	    uri = Uri.parse("vnd.youtube: " + uri.getQueryParameter("v"));

        	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        	    startActivity(intent);
        }//end second if
        
        if (lv.getItemAtPosition(position) == videoNames[2]) {
        	 String video_path = "http://www.youtube.com/watch?v=cSXc9qkESkM";
     	    Uri uri = Uri.parse(video_path);

     	    uri = Uri.parse("vnd.youtube: " + uri.getQueryParameter("v"));

     	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
     	    startActivity(intent);
        }
};
  });
 }
}//end class

