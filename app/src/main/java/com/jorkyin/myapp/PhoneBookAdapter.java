package com.jorkyin.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by YinJian on 2016/3/2.
 */
public class PhoneBookAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mNames = {"xiaozhang", "xiaoming"};

    public PhoneBookAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        //返回多少条数据
        return mNames.length;
    }

    @Override
    public Object getItem(int position) {
        //返回某条数据的对象
        return mNames[position];
    }

    @Override
    public long getItemId(int position) {
        //返回某条数据的ID
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //返回数据视图
        convertView = mLayoutInflater.inflate(R.layout.item_phone_book_friend, null);
        TextView nametextView = (TextView) convertView.findViewById(R.id.item_phoneBook_tv_name);
        nametextView.setText(mNames[position]);
        return nametextView;
    }
}
