package com.jorkyin.myapp.servers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.jorkyin.myapp.R;

/**
 * Created by wuhuifang on 2016/3/15.
 */
public class MusicActivity extends Activity implements View.OnClickListener {

    MusicService mMusicService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.LocalBinder localBinder = (MusicService.LocalBinder) service;
            mMusicService = localBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muisc);
        findViewById(R.id.muisc_bt_Start).setOnClickListener(this);
        findViewById(R.id.muisc_bt_Stop).setOnClickListener(this);

        if (mMusicService != null){
            int x = mMusicService.getx();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.muisc_bt_Start:
                bindService(new Intent(MusicActivity.this, MusicService.class), mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.muisc_bt_Stop:
                unbindService(mServiceConnection);
                break;
        }
    }
}
