package com.jorkyin.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinJian on 2016/3/2.
 */
public class PhoneBookAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<UserInfo> mUserInfo = new ArrayList<>();

    public PhoneBookAdapter(Context context, List<UserInfo> userInfos) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUserInfo = userInfos;
    }

    @Override
    public int getCount() {
        //返回多少条数据
        return mUserInfo.size();
    }

    @Override
    public Object getItem(int position) {
        //返回某条数据的对象
        return mUserInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        //返回某条数据的ID
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //返回数据视图

        //解析item_phone_book_friend，并赋值给convertView；
        convertView = mLayoutInflater.inflate(R.layout.item_phone_book_friend, null);

        //在View中获取控件
        TextView tv_name = (TextView) convertView.findViewById(R.id.item_phoneBook_tv_name);
        TextView tx_age = (TextView) convertView.findViewById(R.id.item_phoneBook_tv_age);
        TextView tv_avatar = (TextView) convertView.findViewById(R.id.item_phoneBook_iv_avatar);

        //和数据之间进行绑定
        tv_name.setText(mUserInfo.get(position).getName());
        tx_age.setText(mUserInfo.get(position).getAge() + "岁");
        tv_avatar.setText(mUserInfo.get(position).getName().subSequence(0, 1).toString().toUpperCase());

        return convertView;
    }
}
