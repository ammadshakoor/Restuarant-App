package com.example.iamma.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends Activity {

    Button userdetail,fooditem,bLogout;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent myintent = getIntent();
        Bundle extras = myintent.getExtras();
        user_name = extras.getString("cust_name");
        Toast.makeText(AdminActivity.this, "Welcome " + user_name ,Toast.LENGTH_LONG ).show();

        userdetail = (Button)findViewById(R.id.btnUser);
        userdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, UserActivity.class);
                startActivity(i);
            }
        });

        fooditem = (Button)findViewById(R.id.btnFood);
        fooditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminActivity.this,"Contact to developer",Toast.LENGTH_SHORT).show();
            }
        });

        bLogout = (Button)findViewById(R.id.btnLogout);
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
