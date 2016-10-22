package com.example.evan.projectcard.Network.Http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Evan on 10/8/2016.
 */
public class OkHttpPostJsonHandler extends AsyncTask<String, Void, String> {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params) {
        String body = params[1];
        String url = params[0];
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody reqBody = RequestBody.create(JSON, body);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .post(reqBody)
                //.addHeader("Authorization", "header value") //Notice this request has header if you don't need to send a header just erase this part
                .build();
        Log.d("Request", request.toString());
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}