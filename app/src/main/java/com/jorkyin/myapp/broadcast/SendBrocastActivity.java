package com.jorkyin.myapp.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jorkyin.myapp.R;

/**
 * Created by YinJian on 2016/3/23.
 */
public class SendBrocastActivity extends Activity implements View.OnClickListener{
    public static final String COM_JORKYIN_MYAPP_BROADCAST="com.jorkyin.myapp.broadcast";
    //广播接收器
    private TestBroadcastReceiver mTestBroadcastReceiver= new TestBroadcastReceiver();
    private Button mSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        mSendBroadcast =(Button)findViewById(R.id.send_broadcast_bt);
        mSendBroadcast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_broadcast_bt:
                //发送广播
                Intent intent = new Intent();
                intent.setAction(COM_JORKYIN_MYAPP_BROADCAST);
                intent.putExtra("toast", "this is my toast of broadcast");

                sendBroadcast(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //有一个intentFilter 过滤器     动态注册
        IntentFilter intentFilter = new IntentFilter();
        //过滤广播信号
        intentFilter.addAction(COM_JORKYIN_MYAPP_BROADCAST);
        registerReceiver(mTestBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mTestBroadcastReceiver);
    }
}
