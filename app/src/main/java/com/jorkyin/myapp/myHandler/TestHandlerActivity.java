package com.jorkyin.myapp.myHandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.jorkyin.myapp.R;

import java.lang.ref.WeakReference;


/**
 * Created by YinJian on 2016/3/21.
 */
public class TestHandlerActivity extends Activity {
    private TextView mTv_hanler_Content;
    private TestHandler testHandler = new TestHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler_demo);
        initActivity();
    }

    public TextView getmTv_hanler_Content() {
        return mTv_hanler_Content;
    }

    private void initActivity() {
        mTv_hanler_Content = (TextView) findViewById(R.id.handler_tv_Content);
        Message msg = testHandler.obtainMessage();
        msg.arg1 = 0;
        msg.arg2 = 1;
        msg.what = 888888;
        msg.obj = 10000;
        testHandler.sendMessage(msg);
    }

    public static class TestHandler extends Handler {
        public final WeakReference<TestHandlerActivity> mTestHandlerActivityWeakReference;

        public TestHandler(TestHandlerActivity activity) {
            mTestHandlerActivityWeakReference = new WeakReference<TestHandlerActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TestHandlerActivity testHandlerActivity = mTestHandlerActivityWeakReference.get();
            //接收消息
            switch (msg.what) {
                case 888888:
                    int value = (int) msg.obj;
                    testHandlerActivity.getmTv_hanler_Content().setText(String.valueOf(value / 1000));
                    msg = obtainMessage().obtain();
                    msg.arg1 = 0;
                    msg.arg2 = 1;
                    msg.what = 888888;
                    msg.obj = value - 1000;
                    if (value > 0) {
                        sendMessageDelayed(msg, 1000);
                    } else
                        break;
            }

        }
    }
}
