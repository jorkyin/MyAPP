package com.jorkyin.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.jorkyin.myapp.broadcast.SendBrocastActivity;
import com.jorkyin.myapp.girdviewdemo.GridViewDemo;
import com.jorkyin.myapp.listViewDemo.ListViewDemo;
import com.jorkyin.myapp.myFragment.TestFragmentActivity;
import com.jorkyin.myapp.myHandler.TestHandlerActivity;
import com.jorkyin.myapp.myview.TestRedButtonActivity;
import com.jorkyin.myapp.scrolDemo.ScrolViewDemo;
import com.jorkyin.myapp.servers.MusicActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
       // testFileDemo();
        //testAssets();
       // testResFile();

       // testSDCard();
    }

    private void testSDCard() {
        //获取SD卡目录
        String tempFile = Environment.getExternalStorageDirectory().getAbsolutePath();
        Environment.getDataDirectory();//获取Android中的打他数据目录
        Environment.getDownloadCacheDirectory();
        Environment.getExternalStorageDirectory();
    }

    private void testResFile() {
        //读资源文件
        //InputStream inputStreams = getResources().openRawResource(R.raw.LIbai);
        //读取资源文件
        getResources().getColor(R.color.colorAccent);
    }

    private void testAssets() {
        //Write file in the assets
        WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/Test.html");

        try {
            //Read file   open只能是文件不是是文件夹
            InputStream inputStream = getResources().getAssets().open("Test.html");

        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            //读取文件名列表
            String[] fileNames = getAssets().list("images/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //读取数据流
            InputStream inputStream = getAssets().open("images/dog.ipg");
            //蒋数据转换为图片（位图）
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //读音频
        try {
            AssetFileDescriptor assetFileDescriptora = getAssets().openFd("libai.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(assetFileDescriptora.getFileDescriptor(),
                    assetFileDescriptora.getStartOffset(),
                    assetFileDescriptora.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void testFileDemo() {
        String string = "Create a new file of Test.txt in the internal storage";
        //Create a new file of Test.txt in the internal storage
        File file = new File(getFilesDir(), "Test.txt");
        Log.i("MainActivity", "getFilesDir:" + getFilesDir().getAbsolutePath());
        Log.i("MainActivity", "file:" + file.getAbsolutePath());

        try {
            //Create new Test.txt of file;
            boolean isSuccess = file.createNewFile();

        } catch (IOException e) {
            Log.i("MainActivity", "Test.txt create error!");
            e.printStackTrace();
        }

        try {
            //Create new file of test2.txt for FileOutputStream fun;
            FileOutputStream fileOutputStream = openFileOutput("test2.txt", Context.MODE_PRIVATE);
            try {
                //Write data to test2.txt file
                fileOutputStream.write(string.getBytes());
                //Close file
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Check external storage state
        String state = Environment.getExternalStorageState();
        if (TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {

        }
    }

    private void initActivity() {
        findViewById(R.id.main_bt_listviewDemo).setOnClickListener(this);
        findViewById(R.id.main_bt_gridviewDemo).setOnClickListener(this);
        findViewById(R.id.main_bt_scrolviewDemo).setOnClickListener(this);
        findViewById(R.id.main_bt_muiscServerDemo).setOnClickListener(this);
        findViewById(R.id.main_bt_redButton).setOnClickListener(this);
        findViewById(R.id.main_bt_MyFragment).setOnClickListener(this);
        findViewById(R.id.main_bt_MyHandler).setOnClickListener(this);
        findViewById(R.id.BroadcastReceiver).setOnClickListener(this);
        findViewById(R.id.NetWork).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_listviewDemo:
                startActivity(new Intent(MainActivity.this, ListViewDemo.class));
                break;
            case R.id.main_bt_gridviewDemo:
                startActivity(new Intent(MainActivity.this, GridViewDemo.class));
                break;
            case R.id.main_bt_scrolviewDemo:
                startActivity(new Intent(MainActivity.this, ScrolViewDemo.class));
                break;
            case R.id.main_bt_muiscServerDemo:
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
                break;
            case R.id.main_bt_redButton:
                startActivity(new Intent(MainActivity.this, TestRedButtonActivity.class));
                break;
            case R.id.main_bt_MyFragment:
                startActivity(new Intent(MainActivity.this, TestFragmentActivity.class));
                break;
            case R.id.main_bt_MyHandler:
                startActivity(new Intent(MainActivity.this, TestHandlerActivity.class));
                break;
            case R.id.BroadcastReceiver:
                startActivity(new Intent(MainActivity.this, SendBrocastActivity.class));
                break;
            case R.id.NetWork:
                startActivity(new Intent(MainActivity.this, NetWorkActivity.class));
                break;
        }
    }

}
