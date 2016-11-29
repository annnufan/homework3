package ru.ifmo.droid2016.imageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Anna Kopeliovich on 26.11.2016.
 */


public class ImageActivity extends AppCompatActivity {

    private ImageView image;
    private TextView errorMassage;

    public static String BROADCAST_NAME = "BROADCAST";
    public static String BASE_URL = "https://pp.vk.me/c636222/v636222621/2d7dc/xFK_YmbNLhs.jpg";

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        final String fileName = getFilesDir().getPath() + "/image.jpg";

        errorMassage = (TextView) findViewById(R.id.errorText);
        image = (ImageView) findViewById(R.id.image);

        errorMassage.setVisibility(View.VISIBLE);
        image.setVisibility(View.GONE);


        File f = new File(fileName);
        if (f.exists()) {
            image.setImageBitmap(BitmapFactory.decodeFile(f.getPath()));
            errorMassage.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
        }

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                File f = new File(fileName);
                image.setImageBitmap(BitmapFactory.decodeFile(f.getPath()));
                errorMassage.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
            }
        };

        registerReceiver(receiver, new IntentFilter(BROADCAST_NAME));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
