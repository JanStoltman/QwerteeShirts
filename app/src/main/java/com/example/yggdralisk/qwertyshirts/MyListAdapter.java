package com.example.yggdralisk.qwertyshirts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by yggdralisk on 30.05.16.
 */
public class MyListAdapter extends BaseAdapter {

    private final String[] urls;
    private final Context context;

    public MyListAdapter(String[] urls,Context context) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int position) {
        return urls[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v = convertView;
        if(v == null){
            LayoutInflater li = LayoutInflater.from(context);
            v = li.inflate(R.layout.list_element,null);
        }

        String url = (String)getItem(position);

        if (url != null && url != "") {
            ImageView imv = (ImageView) v.findViewById(R.id.image);

            if (imv != null) {
                Glide.with(context).load(url).into(imv);
            }
        }

        return v;
    }
}
