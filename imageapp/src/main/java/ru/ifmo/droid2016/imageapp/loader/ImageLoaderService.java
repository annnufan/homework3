package ru.ifmo.droid2016.imageapp.loader;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import ru.ifmo.droid2016.imageapp.ImageActivity;


/**
 * Created by Anna Kopeliovich on 26.11.2016.
 */

public class ImageLoaderService extends IntentService{

    public ImageLoaderService() {
        super("ImageLoaderService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            URL url = new URL(intent.getStringExtra("base_url"));
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            FileOutputStream fout = openFileOutput("image.jpg", Context.MODE_PRIVATE);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fout);
            fout.close();
            sendBroadcast(new Intent(ImageActivity.BROADCAST_NAME));
        } catch (IOException e) {
        }
    }
}

