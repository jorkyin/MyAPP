package com.jorkyin.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by YinJian on 2016/3/1.
 */
public class WelcomeActivity extends Activity {
    private Handler mHandler = new Handler();
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_welcome);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        }, 3000);
    }
}
