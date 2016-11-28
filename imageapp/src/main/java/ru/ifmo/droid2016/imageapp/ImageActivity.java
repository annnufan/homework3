package ru.ifmo.droid2016.imageapp;

import android.graphics.BitmapFactory;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import ru.ifmo.droid2016.imageapp.loader.ImageLoader;
import ru.ifmo.droid2016.imageapp.loader.LoadResult;
import ru.ifmo.droid2016.imageapp.loader.ResultType;
import ru.ifmo.droid2016.imageapp.model.Image;

/**
 * Created by Anna Kopeliovich on 26.11.2016.
 */


public class ImageActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LoadResult<Image>> {

    private ImageView image;
    private TextView errorMassage;

    public boolean imageDownload = false;

    public static String FILE_NAME = "img.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        errorMassage = (TextView) findViewById(R.id.errorText);
        image = (ImageView) findViewById(R.id.image);

        errorMassage.setVisibility(View.VISIBLE);
        image.setVisibility(View.GONE);

        final Bundle loaderArgs = getIntent().getExtras();
        getSupportLoaderManager().initLoader(0, loaderArgs, this);
    }

    private static final String TAG = "Images";

    @Override
    public Loader<LoadResult<Image>> onCreateLoader(int id, Bundle args) {
        return new ImageLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<LoadResult<Image>> loader, LoadResult<Image> result) {
        if (result.resultType == ResultType.OK && result.data != null && result.data.download) {
            displayNonEmptyData(result.data);
        } else {
            displayEmptyData();
        }
    }

    @Override
    public void onLoaderReset(Loader<LoadResult<Image>> loader) {
        displayEmptyData();
    }

    private void displayEmptyData() {
        errorMassage.setVisibility(View.VISIBLE);
        image.setVisibility(View.GONE);
    }

    private void displayNonEmptyData(Image images) {
        File f = new File(FILE_NAME);
        image.setImageBitmap(BitmapFactory.decodeFile(f.getPath()));
        errorMassage.setVisibility(View.GONE);
        image.setVisibility(View.VISIBLE);
    }
}
