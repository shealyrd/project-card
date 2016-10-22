package com.example.evan.projectcard.Network.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.concurrent.ExecutionException;

/**
 * Created by Evan on 10/6/2016.
 */
public class HttpExecutor {

    public static String downloadUrlText(String url){
        OkHttpXmlHandler handler = new OkHttpXmlHandler();
        String result = null;
        try {
            result = handler.execute(url).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static Bitmap downloadUrlImage(String url){
        OkHttpImageHandler handler = new OkHttpImageHandler();
        byte[] result = null;
        try {
            result = handler.execute(url).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0,
                result.length);
        return bitmap;
    }

    public static String postJson(String url, String body){
        OkHttpPostJsonHandler handler = new OkHttpPostJsonHandler();
        String result = "";
        try {
            result = handler.execute(url, body).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
