package com.example.yggdralisk.qwertyshirts;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        try {
            getElements();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getElements() throws IOException {
        new ElemGetter().execute();
    }

    private void gotoMain(String[] res) {
        Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
        intent.putExtra(getString(R.string.urls_extra_string), res);
        startActivity(intent);
        this.finish();
    }

    private class ElemGetter extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            Document doc = null;
            Elements temp = null;
            try {
                doc = Jsoup.connect(getString(R.string.qwertee_url)).get();
                temp = doc.getElementsByClass("dynamic-image-design");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] urls = new String[3];
            for (int i = 0; i < 3; i++) {
                urls[i] = temp.get(i).attr("src");
            }

            return urls;
        }

        @Override
        protected void onPostExecute(String[] res) {
            super.onPostExecute(res);
            for(int i = 0; i <res.length; i++){
                res[i] = res[i].replace("//cdn","https://www");
            }
            gotoMain(res);
        }
    }
}
