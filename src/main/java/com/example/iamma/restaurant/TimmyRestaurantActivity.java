package com.example.iamma.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TimmyRestaurantActivity extends Activity {
    
	Button go_to_menu,go_to_order_list,findstore,info,log,req;

	String user_name;
	@Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent myintent = getIntent();
        Bundle extras = myintent.getExtras();
        user_name = extras.getString("cust_name");
        Toast.makeText(TimmyRestaurantActivity.this, "Welcome " + user_name ,Toast.LENGTH_LONG ).show();
        
       // initialise form widget
        go_to_menu = (Button)findViewById(R.id.Go_To_Menu);
        
        go_to_order_list = (Button)findViewById(R.id.Go_To_Order_List);
       
        findstore = (Button)findViewById(R.id.FindStore);
        
        info = (Button)findViewById(R.id.Info);

		log = (Button)findViewById(R.id.btnLog);

		req = (Button)findViewById(R.id.request);

        
        ModelClass.createlist();
        info.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				//starting new activity on button click
				Intent i =new Intent(TimmyRestaurantActivity.this,InfoScreen.class);
				TimmyRestaurantActivity.this.startActivity(i);
			}
		});

        go_to_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(TimmyRestaurantActivity.this,MenuScreen.class);
				TimmyRestaurantActivity.this.startActivity(i);
			}
		});
    
        findstore.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(TimmyRestaurantActivity.this,FindStore.class);
				TimmyRestaurantActivity.this.startActivity(i);
			}
		});

        go_to_order_list.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(TimmyRestaurantActivity.this,OrderList.class);
				TimmyRestaurantActivity.this.startActivity(i);
			}
		});

        log.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v){
				Intent i =new Intent(TimmyRestaurantActivity.this,LoginActivity.class);
				TimmyRestaurantActivity.this.startActivity(i);
        		finish();
			}
		});

        req.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Toast.makeText(TimmyRestaurantActivity.this,"Request mode to develop",Toast.LENGTH_SHORT).show();
			}
		});
    }
}