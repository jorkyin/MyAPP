package com.jorkyin.myapp;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by YinJian on 2016/4/14.
 */
public class RWFile {
    private Context mContext;

    public RWFile(Context context) {
        mContext = context;
    }

    public void testSDCard() {
        //获取SD卡目录
        String tempFile = Environment.getExternalStorageDirectory().getAbsolutePath();
        Environment.getDataDirectory();//获取Android中的打他数据目录
        Environment.getDownloadCacheDirectory();
        Environment.getExternalStorageDirectory();
    }

    public void testResFile() {
        //读资源文件
        //InputStream inputStreams = getResources().openRawResource(R.raw.LIbai);
        //读取资源文件
        mContext.getResources().getColor(R.color.colorAccent);
    }

    public void testAssets() {
        //Write file in the assets
        WebView webView = new WebView(mContext);
        webView.loadUrl("file:///android_asset/Test.html");

        try {
            //Read file   open只能是文件不是是文件夹
            InputStream inputStream = mContext.getResources().getAssets().open("Test.html");

        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            //读取文件名列表
            String[] fileNames = mContext.getAssets().list("images/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //读取数据流
            InputStream inputStream = mContext.getAssets().open("images/dog.ipg");
            //蒋数据转换为图片（位图）
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //读音频
        try {
            AssetFileDescriptor assetFileDescriptora = mContext.getAssets().openFd("libai.mp3");
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

    public void testFileDemo() {
        String string = "Create a new file of Test.txt in the internal storage";
        //Create a new file of Test.txt in the internal storage
        File file = new File(mContext.getFilesDir(), "Test.txt");
        Log.i("MainActivity", "getFilesDir:" + mContext.getFilesDir().getAbsolutePath());
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
            FileOutputStream fileOutputStream = mContext.openFileOutput("test2.txt", Context.MODE_PRIVATE);
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
}
