package com.example.yggdralisk.qwertyshirts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

/**
 * Created by yggdralisk on 30.05.16.
 */
public class MyListAdapter extends BaseAdapter {

    private final ArrayList<String> imageUrls = new ArrayList<>();
    private final Context context;

    MyListAdapter(ArrayList<String> imageUrls, Context context) {
        this.imageUrls.addAll(imageUrls);
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    @NonNull
    public String getItem(int position) {
        return imageUrls.size() > position ? imageUrls.get(position) : "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = LayoutInflater.from(context);
            v = li.inflate(R.layout.list_element, null);
        }

        String url = getItem(position);
        ImageView imv = (ImageView) v.findViewById(R.id.image);

        if (imv != null) {
            Glide.with(context).load(url).into(imv);
        }

        return v;
    }
}
