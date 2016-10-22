package com.example.evan.projectcard.Network.Http;

/**
 * Created by Evan on 10/9/2016.
 */
public interface HttpConnectionCallback {
    public void onRead(String http);
    public void on404();
    public void onConnect();
    public void onDisconnect();
}
