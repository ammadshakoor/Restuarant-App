package com.example.iamma.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class UserActivity extends Activity {

    Button btnBack;
    DatabaseHelper mydb;
    ListView lv;

    final String[] from = new String[] { DatabaseHelper.C_NAME};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mydb = new DatabaseHelper(this);

        btnBack = (Button)findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserActivity.this, AdminActivity.class);
                startActivity(i);
                finish();
            }
        });


        ArrayList<HashMap<String,String>> regList = mydb.GetCustomer();
        lv = (ListView) findViewById(R.id.listUser);
        ListAdapter adapter = new SimpleAdapter(UserActivity.this, regList, R.layout.list_user,from, new int[]{R.id.textViewUser});
        lv.setAdapter(adapter);
    }
}
