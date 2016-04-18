package com.jonathangama.dao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.jonathangama.R;
import com.jonathangama.model.Book;

import java.io.InputStream;

/**
 * Created by Jonathan Gama on 4/11/2016.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    String url;
    Context context;
    Book book;

    public DownloadImageTask(Context context, Book book, ImageView bmImage) {
        this.bmImage = bmImage;
        this.book=book;
        this.context = context;
    }


    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Image Error", e.getMessage());
            e.printStackTrace();
            mIcon11 = getBitmapFromResource();
        }
        book.setBookImage(mIcon11);
        return mIcon11;
    }

    private Bitmap getBitmapFromResource()
    {
        Bitmap bitmap=null;
        try {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.nobook);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();

        }
        return bitmap;
    }



    protected void onPostExecute(Bitmap result) {

        //in case resizing becomes necessary
        //Bitmap newbitMap;
        //newbitMap = resize(result);
        //bmImage.setImageBitmap(newbitMap);

        bmImage.setImageBitmap(result);

    }

    //in case resizing becomes necessary at some point
    private Bitmap resize(Bitmap result)
    {
        int bmWidth=result.getWidth();
        int bmHeight=result.getHeight();
        int ivWidth=bmImage.getWidth();
        int ivHeight=bmImage.getHeight();

        int new_width=ivWidth;

        int new_height = (int) Math.floor((double) bmHeight *( (double) new_width / (double) bmWidth));
        Bitmap newbitMap = Bitmap.createScaledBitmap(result,new_width,new_height, true);

        return newbitMap;
    }

}
