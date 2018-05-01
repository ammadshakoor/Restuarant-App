package com.example.iamma.restaurant;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class OrderActivity extends Activity {

    ArrayAdapter<String> adapter ;
    ModelClass orderModelClass;
    ListView list;
    static String s1;
    static String s2;

    DatabaseHelper orderHelper;
    static String s;
    String cust_name,phone,email,order,address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderModelClass = new ModelClass();
        list=(ListView)findViewById(R.id.listPlaced);


        //System.out.println(mClass.getPhone());
        cust_name =orderModelClass.getName();
        phone = orderModelClass.getPhone();
        email=orderModelClass.getEmail();
        address =orderModelClass.getAddress();
        adapter=new ArrayAdapter<String>(OrderActivity.this,
                android.R.layout.simple_list_item_1,
                ModelClass.al);
        list.setAdapter(adapter);
        if(ModelClass.al.isEmpty())
        {
            Toast.makeText(this, "Yet no order Is placed Please go to menu section and add order", Toast.LENGTH_LONG).show();

        }
        else{
            s2 = ModelClass.al.get(0).toString();
        }
    }
}
