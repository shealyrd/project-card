package com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.util.Log;

import com.example.evan.projectcard.Engine.Cloning;
import com.example.evan.projectcard.Utilities.AnonymousContainer;

import java.io.Serializable;

/**
 * Created by Evan on 9/28/2016.
 */
public class EZBluetoothConnection {
    BluetoothDevice deviceSelf;
    BluetoothDevice deviceRemote;
    EZBluetoothCallback callback;
    BluetoothConnectionService connection;
/*
    public EZBluetoothConnection(BluetoothDevice deviceSelf){
        this.deviceSelf = deviceSelf;
    }
*/
    public void setCallback(EZBluetoothCallback callback){
        this.callback = callback;
    }

    public void connect(BluetoothDevice deviceRemote){
        this.deviceRemote = deviceRemote;
        connection = new BluetoothConnectionService(callback.getHandler());
        connection.connect(deviceRemote, false);
    }

    public void write(byte[] in){
        connection.write(in);
    }

    public void writeObject(final Serializable obj){
        Log.d("Bluetooth", "Writing Object flag");
        connection.write(BluetoothConstants.OBJECT_FLAG_SET.getBytes());
        final AnonymousContainer serWrap = new AnonymousContainer();
        Serializable ser = null;
        try {ser = (Serializable) Cloning.deepCopy(obj);} catch (Exception e) {e.printStackTrace(); }
        serWrap.setValue(ser);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Log.d("Bluetooth", "Writing Object");
                connection.writeObject((Serializable) serWrap.getValue());
            }
        }, 500);
    }

    public void close(){
        connection.stop();
    }



}
