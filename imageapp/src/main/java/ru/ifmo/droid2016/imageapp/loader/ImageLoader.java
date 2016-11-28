package ru.ifmo.droid2016.imageapp.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.facebook.stetho.urlconnection.StethoURLConnectionManager;

import ru.ifmo.droid2016.imageapp.ImageActivity;
import ru.ifmo.droid2016.imageapp.model.Image;


/**
 * Created by Anna Kopeliovich on 26.11.2016.
 */

public class ImageLoader extends AsyncTaskLoader<LoadResult<Image>> {

    public ImageLoader(Context context) {
        super(context);
    }


    @Override
    public LoadResult<Image> loadInBackground() {
        final StethoURLConnectionManager stethoManager = new StethoURLConnectionManager("API");
        HttpURLConnection connection = null;

        ResultType resultType = ResultType.NO_INTERNET;
        Image data = null;

        try {
            connection = (HttpURLConnection) new URL(Uri.parse("https://pp.vk.me/c636222/v636222621/2d7dc/xFK_YmbNLhs.jpg").toString()).openConnection();

            stethoManager.preConnect(connection, null);
            connection.connect();
            stethoManager.postConnect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                Bitmap bmp = BitmapFactory.decodeStream(connection.getInputStream());
                FileOutputStream fout = getContext().openFileOutput(ImageActivity.FILE_NAME, Context.MODE_PRIVATE);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fout);
                resultType = ResultType.OK;
                data = new Image(true, ImageActivity.FILE_NAME);
                fout.close();
            }

        } catch (IOException e) {
            data = new Image(false, "");
        }
        return new LoadResult<>(resultType, data);
    }
}

