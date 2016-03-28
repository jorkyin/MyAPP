package com.jorkyin.myapp.myFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorkyin.myapp.R;

/**
 * Created by YinJian on 2016/3/18.
 */


public class FriendFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_welcome, container, false);
    }

}