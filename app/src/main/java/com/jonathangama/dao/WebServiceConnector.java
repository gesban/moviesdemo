package com.jonathangama.dao;
/**
 * Created by Jonathan Gama on 4/11/2016.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.jonathangama.view.UpdatableFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class WebServiceConnector extends AsyncTask<String, Integer, String> {
    private ProgressDialog progDialog;
    private UpdatableFragment fragment=null;
    private String json;

    public WebServiceConnector(UpdatableFragment fragment, String json) {
        this.fragment = fragment;
        this.json = json;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        int timeout=3000;
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    json = sb.toString();
                    return json;
            }

        } catch (MalformedURLException ex) {
            Log.e("WebServiceConnector", ex.getMessage());
        } catch (IOException ex) {
            Log.e("WebServiceConnector", ex.getMessage());
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Log.e("WebServiceConnector", ex.getMessage());
                }
            }
        }
        return "";
    }


    @Override
    protected void onProgressUpdate(Integer... progress) {

        //progDialog.setProgress(progress[0]);
        //activity.setProgressPercent(progress[0]);
    }


    @Override
    protected void onPostExecute(String result) {

        if (fragment != null) {
            fragment.updateUI(json);
        }


    }

}
