package com.example.iamma.restaurant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText nameText,phoneText;
	Button registeredButton,newUser;
	ModelClass loginModelClass;
	DatabaseHelper dbHelper;
	Cursor cursor;
	//static final String KEY_NAME = "name";
	//static final String KY_PHONE = "phone";
	//boolean validate;
	SQLiteOpenHelper dHelper;
	SQLiteDatabase db;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginModelClass = new ModelClass();
		
		try{
			dbHelper = new DatabaseHelper(LoginActivity.this);
		//	dbHelper.getWritableDatabase();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		
		nameText = (EditText)findViewById(R.id.user_text);
		phoneText =(EditText)findViewById(R.id.pass_text);
		
		registeredButton = (Button)findViewById(R.id.registerd_user);
		newUser = (Button)findViewById(R.id.new_user);

		// save username and phone
		//SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		//String uname = pref.getString(KEY_NAME, "Your Name");
		//String mphone = pref.getString(KY_PHONE, "Your phone no");
		//nameText.setText(uname);
		//phoneText.setText(mphone);
		
		newUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String cust_name = nameText.getText().toString();
				String mphone = phoneText.getText().toString();

				if (cust_name.equals("") || mphone.equals("")) {
					Toast.makeText(LoginActivity.this, "Please enter the data", Toast.LENGTH_SHORT).show();

				} else if (cust_name.equals("admin") && mphone.equals("12345")) {
					Intent i = new Intent(LoginActivity.this, AdminActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("cust_name", cust_name);
					i.putExtras(bundle);
					startActivity(i);
					finish();
				} else if(cust_name.equals("ammad") && mphone.equals("12345")){
					Intent i = new Intent(LoginActivity.this, TimmyRestaurantActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("cust_name", cust_name);
					i.putExtras(bundle);
					startActivity(i);
					finish();
				}

				else {
					cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.CUSTOMER_TABLE + " WHERE " + DatabaseHelper.C_NAME + "=? AND " + DatabaseHelper.C_PHONE + " =? ", new String[]{cust_name, mphone});

					if (cursor != null) {
						if (cursor.getCount() > 0) {
							cursor.moveToFirst();
							Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

							nameText.setText("");
							phoneText.setText("");
							// intent to start welcome activity after successful login
							Intent intent = new Intent(LoginActivity.this, TimmyRestaurantActivity.class);
							Bundle bundle = new Bundle();
							bundle.putString("cust_name", cust_name);
							intent.putExtras(bundle);
							//intent.putExtra("Username",x );
							startActivity(intent);
							finish();
						} else {
							final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
							builder.setTitle("Alert");
							builder.setMessage("Email or Phone is wrong.");
							builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {

									dialogInterface.dismiss();

								}
							});
							AlertDialog dialog = builder.create();
							dialog.show();
							//-------Alert Dialog Code Snippet End Here
						}
					}
					else {
						Toast.makeText(LoginActivity.this,"Error not found",Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		registeredButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iL =new Intent(LoginActivity.this,custinfo.class);
				startActivity(iL);
				finish();
			}
		});
	}
}
