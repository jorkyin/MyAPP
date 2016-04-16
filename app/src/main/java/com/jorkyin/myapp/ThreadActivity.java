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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jorkyin.myapp.utils.SizeConverter;

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
private boolean downloadTab= true;
    private Button mDownloadButton;
    private ProgressBar mProgressBar;

    private static final String TAG = ThreadActivity.class.getSimpleName();

    private TextView tvFileInfo;
    private TextView tvNetworkInfo;

    private Handler mHandler = new DownloadHandler(this);
    private int mDownloadSize;
    private int mContentLength;
    private String mFileNamestr;
    private int x = 100;
    private int mLength;
    private EditText mEt_url;
    private String mAPK_url;

    public int getX() {
        return x;
    }

    public void setDownloadTab(boolean downloadTab) {
        this.downloadTab = downloadTab;
    }

    public void setX(int x) {
        this.x = x;
    }

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
        mEt_url = (EditText) findViewById(R.id.et_url);
        mDownloadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.donwload_button:
                mAPK_url = mEt_url.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        download(mAPK_url);
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
            mContentLength = urlConnection.getContentLength();

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
            mFileNamestr = url.getPath().substring(url.getPath().lastIndexOf("/") + 1);
            String fileName = downloadFoldersName + mFileNamestr;
            File apkFile = new File(fileName);
            if (apkFile.exists()) {
                apkFile.delete();
            }

            mDownloadSize = 0;
            mLength = 0;
            byte[] bytes = new byte[1024];
            //写文件字节流
            OutputStream outputStream = new FileOutputStream(fileName);
            downloadTab=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (downloadTab){Message message = mHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putInt("downloadSize", mDownloadSize);
                        bundle.putInt("contentLength", mContentLength);
                        bundle.putString("fileNamestr", mFileNamestr);
                        message.setData(bundle);
                        message.what = 0;
                        mHandler.sendMessage(message);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

            while ((mLength = inputStream.read(bytes)) != -1) {
                //向文件中写字节流
                outputStream.write(bytes, 0, mLength);
                //已下载大小
                mDownloadSize += mLength;


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
                        activity.setDownloadTab(false);
                        activity.getTvNetworkInfo().setText("下载完成");

                    }else{
                        activity.getTvNetworkInfo().setText(SizeConverter.autoConverter((downloadSize - activity.getX())*10) + "/s     " + SizeConverter.autoConverter(downloadSize) + " / " + SizeConverter.autoConverter(contentLength) + "      " + progress + "%");

                    }
                    activity.getTvFileInfo().setText(fileNamestr);
                    activity.setX(downloadSize);
                    activity.getProgressBar().setProgress(progress);
                    break;
            }
        }
    }
}
