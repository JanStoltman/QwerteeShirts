package com.example.yggdralisk.qwertyshirts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashScreen extends AppCompatActivity {
    private static final String HTTPS_PREFIX = "https:";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }

        super.onDestroy();
    }

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

    private void proceedToMainActivity(ArrayList<String> res) {
        Intent intent = new Intent(this, ActivityMain.class);
        intent.putExtra(getString(R.string.urls_extra_string), res);
        startActivity(intent);
        this.finish();
    }

    private void getElements() throws IOException {
        DisposableObserver<ArrayList<String>> imagesObserver = new DisposableObserver<ArrayList<String>>() {
            @Override
            public void onNext(ArrayList<String> imageUrls) {
                proceedToMainActivity(imageUrls);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        };

        compositeDisposable.add(imagesObserver);

        Observable.fromCallable(() -> scrapImages(getApplicationContext()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imagesObserver);
    }

    private ArrayList<String> scrapImages(Context context) throws IOException {
        Elements dynamicImageWraps = null;
        ArrayList<String> imageUrls = new ArrayList<String>();

        Document doc = Jsoup.connect(context.getString(R.string.qwertee_url)).get();
        dynamicImageWraps = doc.getElementsByClass("design-dynamic-image-wrap");//"zoom-dynamic-image design-dynamic-image");

        for (Element elem : dynamicImageWraps) {
            imageUrls.add(
                    HTTPS_PREFIX.concat(elem.select("div").first()
                            .select("picture").first()
                            .select("img")
                            .attr("src"))

            );
        }


        return imageUrls;
    }
}
