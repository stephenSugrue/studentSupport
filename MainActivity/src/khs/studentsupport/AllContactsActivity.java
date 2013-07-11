package khs.studentsupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AllContactsActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;

	// url to get all contacts list
	private static String url_all_products = "http://kate.ict.op.ac.nz/~sugrusl1/android_connect/get_all_contacts.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_PID = "pid";
	private static final String TAG_NAME = "name";
	private static final String TAG_ROLE = "role";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_BUILDING = "building";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_PHONE= "phone";
	
	
	static final String KEY_NAME = "name";
	static final String KEY_ROLL = "roll";
	static final String KEY_LOCATION = "location";
	static final String KEY_BUILDING = "building";
	static final String KEY_EMAIL = "email";
	static final String KEY_PHONE = "phone";

	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);

		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();

		// Get listview
		// selecting single ListView item
				ListView lv = getListView();

				lv.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// getting values from selected ListItem
						String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
						String roll = ((TextView) view.findViewById(R.id.role)).getText().toString();
						String location = ((TextView) view.findViewById(R.id.location)).getText().toString();
						String building = ((TextView) view.findViewById(R.id.building)).getText().toString();
						String email = ((TextView) view.findViewById(R.id.email)).getText().toString();
						String phone = ((TextView) view.findViewById(R.id.phone)).getText().toString();
						
						// Starting new intent
						Intent in = new Intent(getApplicationContext(), SelectedSupportContact.class);
						in.putExtra(KEY_NAME, name);
						in.putExtra(KEY_ROLL, roll);
						in.putExtra(KEY_LOCATION, location);
						in.putExtra(KEY_BUILDING, building);
						in.putExtra(KEY_PHONE, phone);
						in.putExtra(KEY_EMAIL, email);
						
						startActivity(in);

					}
				});

	}

	// Response from  Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received 
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AllContactsActivity.this);
			pDialog.setMessage("Loading contacts. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_CONTACTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						String name = c.getString(TAG_NAME);
						String role = c.getString(TAG_ROLE);
						String location = c.getString(TAG_LOCATION);
						String building= c.getString(TAG_BUILDING);
						String email = c.getString(TAG_EMAIL);
						String phone = c.getString(TAG_PHONE);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PID, id);
						map.put(TAG_NAME, name);
						map.put(TAG_ROLE, role);
						map.put(TAG_LOCATION, location);
						map.put(TAG_BUILDING, building);
						map.put(TAG_EMAIL, email);
						map.put(TAG_PHONE, phone);

						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {
					// no products found
					// Launch Add New product Activity
					Intent i = new Intent(getApplicationContext(),
							NewContactActivity.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
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
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							AllContactsActivity.this, productsList,
							R.layout.list_item, new String[] { TAG_PID,
									TAG_NAME, TAG_ROLE,TAG_LOCATION,TAG_BUILDING,TAG_EMAIL,TAG_PHONE},
							new int[] { R.id.pid, R.id.name,R.id.role,R.id.location,R.id.building,R.id.email,R.id.phone });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}