package com.jorkyin.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by YinJian on 2016/3/1.
 */
public class WelcomeActivity extends AppCompatActivity{
    private Handler mHandler = new Handler();
    private Intent mIntent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIntent =new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(mIntent);
            }
        },3000);
    }
}
