package com.example.iamma.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class custinfo extends Activity {
	EditText cust_name,cust_email,cust_phone,cust_address; 
	Button save;
	DatabaseHelper cust_Helper;
	TextView log;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.custinfo);
        try{
        	cust_Helper = new DatabaseHelper(custinfo.this);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
       //load form widget
        save=(Button)findViewById(R.id.Save);
        cust_name = (EditText)findViewById(R.id.cust_name);
        cust_email =(EditText)findViewById(R.id.cust_email);
        cust_phone =(EditText)findViewById(R.id.cust__number);
        cust_address =(EditText)findViewById(R.id.cust_adress);

        log = (TextView)findViewById(R.id.textViewLogin);
        log.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// click on register text open the register activity
				Intent intent = new Intent(custinfo.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
        
        //register user
       save.setOnClickListener(new OnClickListener() {
		
    	   @Override
			public void onClick(View v) {
    		 
    		 String name = cust_name.getText().toString();
    		 //System.out.println(c_name);
    		 String mail = cust_email.getText().toString();
    		 String phone = cust_phone.getText().toString();
    		 String addrs = cust_address.getText().toString();
    		   if(name.equals("")||mail.equals("")||phone.equals("") || addrs.equals("")){
    			  Toast.makeText(custinfo.this, "All fields are Mandatory", Toast.LENGTH_LONG).show(); 
    		   }
    		   else{

    		   		cust_Helper.addCustomer(name,phone,mail,addrs);
    			   	Intent i =new Intent(custinfo.this,TimmyRestaurantActivity.class);
    			   	Bundle bundle = new Bundle();
    			   	bundle.putString("cust_name", name);
    			   	i.putExtras(bundle);
    			   	startActivity(i);
    			   	finish();
    		   }

			}
    	   	
       });
     
      
	}
	public void onResume(){
		super.onResume();
		cust_Helper = new DatabaseHelper(custinfo.this);
		//cust_Helper.getWritableDatabase();
	}
	public void onStop(){
		super.onStop();
		cust_Helper.close();
	
	}
	
}
