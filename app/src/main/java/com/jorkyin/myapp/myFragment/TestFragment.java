package com.jorkyin.myapp.myFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorkyin.myapp.R;

/**
 * Created by YinJian on 2016/3/17.
 */
public class TestFragment extends Fragment {
    public static final String TAG = TestFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        //创建或者填充Fragment的UI
        //View view = inflater.inflate(R.layout.item_phone_book_friend,container,false);

        // /返回UI，如果这个Fragment没有UI，返回null
        //return super.onCreateView(inflater, container, savedInstanceState);

        //创建或者填充Fragment的UI，并且返回它，如果这个Fragment没有UI，返回null
        return inflater.inflate(R.layout.item_phone_book_friend, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
