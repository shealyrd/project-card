package com.example.evan.projectcard.Network.Http;

import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;

/**
 * Created by Evan on 10/9/2016.
 */
public class HttpGameConnection {
    public boolean running;
    boolean done;
    String token;
    String gameID;
    HttpConnectionCallback callback;

    public void setCallback(HttpConnectionCallback callback){
        this.callback = callback;
    }

    public void setGameID(String id){
        gameID = id;
    }

    public void sendRelayData(String data){
        Log.d("SENDING", data);
        Log.d("SENDING JSON", "{\"token\":\"" + token +"\", \"id\": \"" + gameID  + "\", \"data\": \"" + data + "\"}");
        HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/postrelaydata", "{\"token\":\"" + token +"\", \"id\": \"" + gameID  + "\", \"data\": \"" + data + "\"}");
    }

    public void close(){
        Log.d("CLOSE", "CLOSING");
        /*sendRelayData("DISCONNECTED");
        Log.d("CLOSE", "SENT");*/
        JSONObject leaveObj = new JSONObject();
        try {
            leaveObj.put("id", gameID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/leavegame", leaveObj.toString());
        //sendRelayData("DISCONNECTED");
        Log.d("CLOSE", "SENT");

    }

    public void start(){
        running = true;
                token = HttpExecutor.downloadUrlText("http://projectcard-91355.onmodulus.net/api/gettoken");
                  final Handler mHandler = new Handler();
                   Runnable r = new Runnable() {
                       @Override
                       public void run() {
                           String request = "{\"token\":\"" + token + "\", \"id\": \"" + gameID + "\"}";
                           Log.d("Http", "Request: " + request);
                           //callback.onRead(request);
                           String payload = HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/getrelaydata", request);
                           Log.d("Http", "Payload: " + payload);
                           if (payload != null) {
                               if(payload.equals("CONNECTED")){
                                   callback.onConnect();
                               }
                               if(payload.equals("DISCONNECTED")){
                                   Log.d("Http", "disconnecting...");
                                   callback.onDisconnect();
                                   running = false;
                               }
                               if(!payload.startsWith("404")){
                                   callback.onRead(payload);
                               }
                           } else {
                               callback.on404();
                           }
                           if(running){
                               mHandler.postDelayed(this, 2000);
                           }
                           else{
                               done = true;
                           }
                       }
                   };
                   mHandler.postDelayed(r, 2000);
    }

}
