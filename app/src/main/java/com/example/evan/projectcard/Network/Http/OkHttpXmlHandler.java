package com.example.evan.projectcard.Network.Http;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Evan on 10/6/2016.
 */
public class OkHttpXmlHandler extends AsyncTask<String, Void, String> {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);

        Request request = builder.build();

        try {

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
        }

        return null;
    }
}