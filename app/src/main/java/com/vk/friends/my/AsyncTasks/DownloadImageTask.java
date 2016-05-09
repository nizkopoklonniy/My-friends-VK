package com.vk.friends.my.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * AsyncTask for download image. Don't cashes.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    /**
     * Constructor with parameter.
     * @param imageView ImageView.
     */
    public DownloadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * ImageView for showing image.
     */
    private ImageView imageView;

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap bitmap = null;

        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}