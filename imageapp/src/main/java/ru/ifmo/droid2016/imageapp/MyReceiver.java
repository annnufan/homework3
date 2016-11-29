package ru.ifmo.droid2016.imageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;

import ru.ifmo.droid2016.imageapp.loader.ImageLoaderService;

/**
 * Created by Anna Kopeliovich on 29.11.2016.
 */

public class MyReceiver extends BroadcastReceiver {
    @MainThread
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, ImageLoaderService.class).putExtra("base_url", ImageActivity.BASE_URL));
    }
}
