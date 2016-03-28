package com.jorkyin.myapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by YinJian on 2016/3/23.
 */
public class TestBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (TextUtils.equals(intent.getAction(), SendBrocastActivity.COM_JORKYIN_MYAPP_BROADCAST)) {
                String toastString = intent.getStringExtra("toast");
                Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
            }
        }
    }
}
