package com.example.iamma.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try{
                    sleep(3000);
                    //Login screen call
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException ex)
                {
                    //if exception occur
                    ex.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}
