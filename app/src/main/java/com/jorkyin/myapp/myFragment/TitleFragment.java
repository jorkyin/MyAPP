package com.jorkyin.myapp.myFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jorkyin.myapp.R;

/**
 * Created by YinJian on 2016/3/18.
 */
public class TitleFragment extends Fragment implements View.OnClickListener{

    private ImageButton mLeftMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tifle,container,false);
        mLeftMenu = (ImageButton) view.findViewById(R.id.id_title_left_btn);
        mLeftMenu.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),
                "i am an ImageButton in TitleFragment ! ",
                Toast.LENGTH_SHORT).show();
    }
}
