package ru.ifmo.droid2016.imageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by Anna Kopeliovich on 26.11.2016.
 */


public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
    }

    private static final String TAG = "Images";
}
