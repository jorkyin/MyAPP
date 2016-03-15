package com.jorkyin.myapp.servers;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jorkyin.myapp.R;

/**
 * Created by wuhuifang on 2016/3/15.
 */
public class MusicService extends Service {
    private static final String TAG = MusicActivity.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
private IBinder mIBinder = new LocalBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer=MediaPlayer.create(this, R.raw.myc);
        Log.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        mMediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        Log.i(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    public class LocalBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

    public int getx(){
        return 12;
    }
}
