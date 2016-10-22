package com.example.evan.projectcard.Network.Http;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpImageHandler extends AsyncTask<String, Void, byte[]> {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected byte[] doInBackground(String... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);

        Request request = builder.build();

        try {

            Response response = client.newCall(request).execute();
            return response.body().bytes();

        } catch (Exception e) {
        }

        return null;
    }
}