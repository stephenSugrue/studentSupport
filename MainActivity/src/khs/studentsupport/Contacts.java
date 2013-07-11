package khs.studentsupport;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contacts extends Activity implements OnClickListener{


	boolean AllFieldsComp = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        
        
        
        // Creating a button click listener for the "Add Contact" button
        OnClickListener addClickListener = new OnClickListener() {
			
			
			public void onClick(View v) {
				
				
				// Getting reference to Name EditText 
				EditText etName = (EditText) findViewById(R.id.et_name);
				
				
															
				// Getting reference to Mobile EditText 
				EditText etMobile = (EditText) findViewById(R.id.et_mobile_phone);
				
				
				// Getting reference to WorkPhone EditText 
				EditText etWorkPhone = (EditText) findViewById(R.id.et_work_phone);
			
				//Getting reference to Extension
				EditText etExtension = (EditText) findViewById(R.id.et_extension);
							
				
				// Getting reference to HomeEmail EditText 
				//EditText etHomeEmail = (EditText) findViewById(R.id.et_home_email);
				
				// Getting reference to WorkEmail EditText 
			//	EditText etWorkEmail = (EditText) findViewById(R.id.et_work_email);	
				
				//Getting reference to RoomLocation(using Address) EditText
				EditText etAddress = (EditText) findViewById(R.id.et_address);
				
				//Getting reference to Department EditText
				EditText etDept = (EditText) findViewById(R.id.et_dept);
				
				//Getting reference to Title EditText
				EditText etTitle = (EditText)findViewById(R.id.et_title);
				
				if((etWorkPhone.getText().length()==0)||(etName.getText().length()==0)||(etMobile.getText().length()==0))
				{
					
					
					if  ((etWorkPhone.getText().length()!=0))
					{
						Toast.makeText(getBaseContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
						Toast.makeText(getBaseContext(), "Please enter name", Toast.LENGTH_SHORT).show();
					}
					
					if((etName.getText().length()!=0)&&(etWorkPhone.getText().length()==0)&&(etMobile.getText().length()==0))
					{
						Toast.makeText(getBaseContext(), "Please enter work number", Toast.LENGTH_SHORT).show();
						Toast.makeText(getBaseContext(), "Please enter mobile", Toast.LENGTH_SHORT).show();
					}
					
					if((etMobile.getText().length()!=0)&&(etName.getText().length()==0)&&(etWorkPhone.getText().length()==0))
					{
						Toast.makeText(getBaseContext(), "Please enter name", Toast.LENGTH_SHORT).show();
						Toast.makeText(getBaseContext(), "Please enter work number", Toast.LENGTH_SHORT).show();
					}
					
															
					//if((etWorkPhone.getText().length()==0)&&(etName.getText().length()==0)&&(etMobile.getText().length()==0))
					//{
					//	Toast.makeText(getBaseContext(), "Please enter work number", Toast.LENGTH_SHORT).show();
					//	Toast.makeText(getBaseContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
					//	Toast.makeText(getBaseContext(), "Please enter name", Toast.LENGTH_SHORT).show();
					//}	
					
				}
				
				
				if((etName.getText().length()!=0)&&(etMobile.getText().length()!=0)||(etWorkPhone.getText().length()!=0))
				{
					AllFieldsComp=true;
				}
				
				if(AllFieldsComp)
				{
					Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
				}
				
				if((etDept.getText().length()!=0))
				{
					
					 etDept.setText(etDept.getText().toString() + "  " + "Dept," + " "
					 +  "Room " + " " + etAddress.getText().toString()   + ","  + " ");
					
				}
				
				if((etWorkPhone.getText().length()!=0))
				{
					etWorkPhone.setText(etWorkPhone.getText().toString() +" " + "Ext" + " "+ etExtension.getText().toString());
				}
				
			
				
				ArrayList<ContentProviderOperation> ops =
				          new ArrayList<ContentProviderOperation>();
				
				int rawContactID = ops.size();
				
				// Adding insert operation to operations list 
				// to insert a new raw contact in the table ContactsContract.RawContacts
				ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
						.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
						.withValue(RawContacts.ACCOUNT_NAME, null)
						.build());

				// Adding insert operation to operations list
				// to insert display name in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
						.withValue(StructuredName.DISPLAY_NAME, etName.getText().toString())
						.build());
				
				// Adding insert operation to operations list
				// to  insert Home Phone Number in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, etWorkPhone.getText().toString())// + " "+ "Ext" + " "+ etExtension.getText().toString() )
						.withValue(Phone.TYPE, Phone.TYPE_WORK)
						.build());
				
				//ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						//.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						//.withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						//.withValue(Phone.NUMBER, etExtension.getText().toString())
						//.withValue(Phone.TYPE, Phone.TYPE_WORK)
						//.build());
				

				
				// Adding insert operation to operations list
				// to insert Mobile Number in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, etMobile.getText().toString())
						.withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
						.build());
				
				
				// Adding insert operation to operations list
				// to insert Home Email in the table ContactsContract.Data
				//ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						//.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						//.withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
						//.withValue(Email.ADDRESS, etHomeEmail.getText().toString())
						//.withValue(Email.TYPE, Email.TYPE_HOME)
						//.build());
				
				// Adding insert operation to operations list
				// to insert Work Email in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
						//s.withValue(Email.ADDRESS, etWorkEmail.getText().toString())
						.withValue(Email.TYPE, Email.TYPE_WORK)
						.build());	
				
				//Adding Department details
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
						.withValue(ContactsContract.CommonDataKinds.StructuredPostal.STREET,
						 etDept.getText().toString())// + 	etAddress.getText().toString())
								
					    // etDept.getText().toString() + " "  + "Dept," + " "
						 //+  "Room" + " " + etAddress.getText().toString()  + ","  + " " +  "Commerce Building" ) 
						.build());
				
				 //Adding Title details
				ops.add(ContentProviderOperation
				.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,rawContactID)
				.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE )
				.withValue(ContactsContract.CommonDataKinds.Organization.TITLE,etTitle.getText().toString()) 
				.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE )
				.build());

				try{
					// Executing all the insert operations as a single database transaction
					getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
					//Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
				
				}catch (RemoteException e)
				{					
					e.printStackTrace();
				}catch (OperationApplicationException e) 
				{
					e.printStackTrace();
				}
			}
		};
		
		
		
		// Creating a button click listener for the "Add Contact" button
		OnClickListener contactsClickListener = new OnClickListener() {
			
	
			public void onClick(View v) {
				// Creating an intent to open Android's Contacts List
				Intent contacts = new Intent(Intent.ACTION_VIEW,ContactsContract.Contacts.CONTENT_URI);
				
				// Starting the activity
				startActivity(contacts);	
				
				
			}
		};
		
        
        // Getting reference to "Add Contact" button
        Button btnAdd = (Button) findViewById(R.id.btn_add);
        
        // Getting reference to "Contacts List" button
        Button btnContacts = (Button) findViewById(R.id.btn_contacts);
        
        //Getting reference to "Clear Fields" button
       // Button btnClear = (Button)findViewById(R.id.btn_clear);
        
        // Setting click listener for the "Add Contact" button
        btnAdd.setOnClickListener(addClickListener);
        
        // Setting click listener for the "List Contacts" button
        btnContacts.setOnClickListener(contactsClickListener);
        
        Button resetButton=(Button)findViewById(R.id.btn_reset);
        resetButton.setOnClickListener(this);
		
        }
        

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.dashboard, menu);
        return true;
    }


  
	public void onClick(View v)
	{
		EditText etName =(EditText)findViewById(R.id.et_name);
		EditText etMobile = (EditText) findViewById(R.id.et_mobile_phone);		
		EditText etWorkPhone = (EditText) findViewById(R.id.et_work_phone);
		EditText etExtension = (EditText) findViewById(R.id.et_extension);
		EditText etWorkEmail = (EditText) findViewById(R.id.et_work_email);	
		EditText etAddress = (EditText) findViewById(R.id.et_address);
		EditText etDept = (EditText) findViewById(R.id.et_dept);
		EditText etTitle = (EditText)findViewById(R.id.et_title);
		
		//using setText to clear fields
		etName.setText("");
		etMobile.setText("");
		etWorkPhone.setText("");
		etExtension.setText("");
		etWorkEmail.setText("");
		etAddress.setText("");
		etDept.setText("");
		etTitle.setText("");
		
	}
}
