package ru.ifmo.droid2016.imageapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Anna Kopeliovich on 26.11.2016.
 */

public class ImageDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
