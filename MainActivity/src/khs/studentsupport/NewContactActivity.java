package khs.studentsupport;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewContactActivity extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText inputName, inputRole, inputLocation,inputBuilding,inputEmail,inputPhone;

	// url to create new product
	private static String url_create_product = "http://kate.ict.op.ac.nz/~sugrusl1/android_connect/create_contact.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);

		// Edit Text
		inputName = (EditText) findViewById(R.id.name);
		inputRole = (EditText) findViewById(R.id.role);
		inputLocation = (EditText) findViewById(R.id.location);
		inputBuilding = (EditText) findViewById(R.id.building);
		inputEmail = (EditText) findViewById(R.id.email);
		inputPhone = (EditText) findViewById(R.id.phone);
		
		// Create button
		Button btnCreateContact = (Button) findViewById(R.id.btnCreateContact);

		// button click event
		btnCreateContact.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewContactActivity.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String name = inputName.getText().toString();
			String role = inputRole.getText().toString();
			String location = inputLocation.getText().toString();
			
			String building = inputBuilding.getText().toString();
			String email = inputEmail.getText().toString();
			String phone = inputPhone.getText().toString();
			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("role", role));
			params.add(new BasicNameValuePair("location", location));
			params.add(new BasicNameValuePair("building", building));
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("phone", phone));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), AllContactsActivity.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
