package com.jorkyin.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by YinJian on 2016/4/5.
 */
public class NetWorkActivity extends Activity implements View.OnClickListener {
    private String editTestUrl;
    private Button button;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        initActivityu();

    }

    private void initActivityu() {

        button = (Button) findViewById(R.id.network_Button);
        editText = (EditText) findViewById(R.id.network_Edittext);
        textView = (TextView) findViewById(R.id.network_textView);

        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.network_Button:
                String url = getEditTestUrl();
                //请求网络数据，需要网络权限
                //String data = requestData(url);
                //异步处理
                new RequestNetWorkDataTask().execute(url);
                break;
        }
    }

    private String requestData(String urlString) {
        try {
            URL url = new URL(urlString);
            //打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置超时
            connection.setConnectTimeout(30000);
            //设置获取数据
            connection.setRequestMethod("GET");
            //发送请求
            connection.connect();
            //返回响应状态代码
            int responseCode = connection.getResponseCode();
            //返回响应状态消息
            String responseMessage = connection.getResponseMessage();
            String result = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //返回请求的数据流
                InputStream inputStream = connection.getInputStream();
                //读取流
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                char[] buffer = new char[1024];
                reader.read(buffer);
                result = new String(buffer);
            }

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //异步任务处理
    //第一个参数是参数  第二个是进度条 第三个是返回结果
    class RequestNetWorkDataTask extends AsyncTask<String, Intent, String> {
        //在后台work处理之前
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //主线程
            //加载数据 如进度条
            textView.setText("加载中...");
        }

        //后台运行
        @Override
        protected String doInBackground(String[] params) {
            System.out.println("params[0]:" + params[0]);
            return requestData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //执行完成之后在主线程中
            textView.setText(result);
        }
    }

    public String getEditTestUrl() {
        return editText != null ? editText.getText().toString() : "";
    }
}
