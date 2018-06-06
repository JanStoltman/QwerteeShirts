package com.example.yggdralisk.qwertyshirts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by yggdralisk on 30.05.16.
 */
public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        Intent intent = getIntent();
        ArrayList<String> imageUrls = intent.getStringArrayListExtra(getString(R.string.urls_extra_string));
        setLinearView(imageUrls);
    }

    private void setLinearView(ArrayList<String> imageUrls) {
        ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(new MyListAdapter(imageUrls, getApplicationContext()));
    }

}
