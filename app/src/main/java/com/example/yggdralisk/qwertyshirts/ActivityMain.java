package com.example.yggdralisk.qwertyshirts;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by yggdralisk on 30.05.16.
 */
public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        Intent intent = getIntent();
        String[] urls = intent.getStringArrayExtra(getString(R.string.urls_extra_string));
        setLinearView(urls);
    }

    private void setLinearView(String[] urls) {
        ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(new MyListAdapter(urls,getApplicationContext()));
    }

}
