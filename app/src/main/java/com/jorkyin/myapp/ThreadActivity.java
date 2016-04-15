package com.jorkyin.myapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
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
import java.lang.ref.WeakReference;
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

    private TextView tvFileInfo;
    private TextView tvNetworkInfo;

    private Handler mHandler = new DownloadHandler(this);

    public TextView getTvFileInfo() {
        return tvFileInfo;
    }

    public TextView getTvNetworkInfo() {
        return tvNetworkInfo;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initActivity();
    }

    private void initActivity() {
        mDownloadButton = (Button) findViewById(R.id.donwload_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvFileInfo = (TextView) findViewById(R.id.donwload_file_info);
        tvNetworkInfo = (TextView) findViewById(R.id.download_network_info);
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

            //获得下载文件大小
            int contentLength = urlConnection.getContentLength();

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

            //在SD卡中创建Jorkyin文件夹
            String downloadFoldersName = Environment.getExternalStorageDirectory() + File.separator + "Jorkyin" + File.separator;
            File file = new File(downloadFoldersName);
            if (!file.exists()) {
                Log.i(TAG, "file:" + downloadFoldersName);
                if (file.mkdir()) {
                    Log.i(TAG, "创建文件夹" + downloadFoldersName + "成功");
                } else {
                    Log.i(TAG, "创建文件夹" + downloadFoldersName + "失败");
                }
            }

            //在SD卡中 Jorkyin文件夹中创建文件
            String fileNamestr = url.getPath().substring(url.getPath().lastIndexOf("/") + 1);
            String fileName = downloadFoldersName + fileNamestr;
            File apkFile = new File(fileName);
            if (apkFile.exists()) {
                apkFile.delete();
            }

            int downloadSize = 0;
            int length = 0;
            byte[] bytes = new byte[1024];
            //写文件字节流
            OutputStream outputStream = new FileOutputStream(fileName);
            while ((length = inputStream.read(bytes)) != -1) {
                //向文件中写字节流
                outputStream.write(bytes, 0, length);
                //已下载大小
                downloadSize += length;

                Message message = mHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("downloadSize", downloadSize);
                bundle.putInt("contentLength", contentLength);
                bundle.putString("fileNamestr", fileNamestr);
                message.setData(bundle);
                message.what = 0;
                mHandler.sendMessage(message);
            }

            inputStream.close();
            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "download failure");
        }

        mDownloadButton.setClickable(true);
    }

    static class DownloadHandler extends Handler {
        public final WeakReference<ThreadActivity> mActivity;

        public DownloadHandler(ThreadActivity activity) {
            mActivity = new WeakReference<ThreadActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThreadActivity activity = mActivity.get();

            switch (msg.what) {
                case 0:
                    int contentLength = msg.getData().getInt("contentLength");
                    int downloadSize = msg.getData().getInt("downloadSize");
                    String fileNamestr = msg.getData().getString("fileNamestr");
                    //计算当前进度百分比
                    int progress = downloadSize * 100 / contentLength;
                    if (progress == 100) {

                        Log.i(TAG, "download success");
                    }
                    activity.getTvFileInfo().setText(fileNamestr);
                    activity.getTvNetworkInfo().setText(byteToMB(downloadSize) + " / " + byteToMB(contentLength) + "      " + progress + "%");
                    activity.getProgressBar().setProgress(progress);
                    break;
            }
        }

        private String byteToMB(int contentLength) {

            if (contentLength / (1024 * 1024 * 1024) > 0) {
                return contentLength / (1024 * 1024 * 1024) + "." + (contentLength % (1024 * 1024 * 1024)) / (1024 * 1024 * 10) + "GB ";
            }

            if (contentLength % (1024 * 1024 * 1024) / (1024 * 1024) > 0) {
                return contentLength % (1024 * 1024 * 1024) / (1024 * 1024) + "." + ((contentLength % (1024 * 1024 * 1024)) % (1024 * 1024)) / (1024 * 10) + "MB";
            }
            if (contentLength % (1024 * 1024) / 1024 > 0) {
                return contentLength % (1024 * 1024) / 1024 + "KB";
            }
            return "0KB";
        }
    }
}
