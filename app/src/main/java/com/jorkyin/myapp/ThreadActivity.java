package com.jorkyin.myapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by YinJian on 2016/4/14.
 */
public class ThreadActivity extends Activity implements View.OnClickListener {

    private Button mDownloadButton;
    private ProgressBar mProgressBar;
    private static final String APK_URL = "http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk";
    private static final String TAG = ThreadActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mDownloadButton = (Button) findViewById(R.id.donwload_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mDownloadButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.donwload_button:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        download(APK_URL);
                    }
                }).start();

                break;
        }
    }

    public void download(String apkUrl) {
        try {
            URL url = new URL(apkUrl);

            URLConnection urlConnection = url.openConnection();//打开连接

            InputStream inputStream = urlConnection.getInputStream();//下载url流文件

            int contentLength = urlConnection.getContentLength();//获得下载文件大小

            //设置读写SD卡权限
            int REQUEST_EXTERNAL_STORAGE = 1;
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            int permission = ActivityCompat.checkSelfPermission(ThreadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        ThreadActivity.this,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }


            String downloadFoldersName = Environment.getExternalStorageDirectory() + File.separator + "Download"+ File.separator;
            File file = new File(downloadFoldersName);

            if(!file.exists()){
                Log.i(TAG,"file:"+downloadFoldersName);
                if (file.mkdir()){
                    Log.i(TAG,"创建成功："+downloadFoldersName);
                }else{
                    Log.i(TAG,"创建失败:"+downloadFoldersName);
                }
            }

            String fileName = downloadFoldersName + "test.apk";
            File apkFile = new File(fileName);
            if(apkFile.exists()){
                apkFile.delete();
            }
            int downloadSize = 0;
            int length = 0;
            byte[] bytes = new byte[1024];
            OutputStream outputStream = new FileOutputStream(fileName);

            while ((length = inputStream.read(bytes)) != -1){

                outputStream.write(bytes, 0, length);
                downloadSize += length;
                int progress = downloadSize * 100 / contentLength;
                mProgressBar.setProgress(progress);
                Log.i(TAG, "Downloag:" + progress);
            }
            inputStream.close();
            outputStream.close();
            Log.i(TAG, "download success");

        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "download failure");
        }
    }
}
