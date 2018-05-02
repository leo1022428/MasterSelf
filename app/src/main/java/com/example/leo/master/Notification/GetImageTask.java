package com.example.leo.master.Notification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.leo.master.R;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImageTask extends AsyncTask<Object, Integer, Bitmap> {
    private String url;
    private int post_id, imageSize;
    private WeakReference<ImageView> imageViewWeakReference;
    private final static String TAG = "GetImageTask";


    public GetImageTask(String url, int post_id, int imageSize, ImageView imageView) {
        this.url = url;
        this.post_id = post_id;
        this.imageSize = imageSize;
        this.imageViewWeakReference = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(Object... objects) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getImage");
        jsonObject.addProperty("post_id", post_id);
        jsonObject.addProperty("imageSize", imageSize);
        return getRemoteImage(url, jsonObject.toString());
    }

    private Bitmap getRemoteImage(String url, String jsonOut) {
        HttpURLConnection connection = null;
        Bitmap bitmap = null;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bw.write(jsonOut);
            bw.close();
            Log.d(TAG, "Output : " + jsonOut);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                bitmap = BitmapFactory.decodeStream(new BufferedInputStream(connection.getInputStream()));
            } else {
                Log.d(TAG, "response code: " + responseCode);
            }

        } catch (Exception e) {
            Log.d(TAG, "Error : " + e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = imageViewWeakReference.get();
        if (imageView == null){
            return;
        }
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
        }else {
            imageView.setImageResource(R.drawable.picture);
        }

    }
}
