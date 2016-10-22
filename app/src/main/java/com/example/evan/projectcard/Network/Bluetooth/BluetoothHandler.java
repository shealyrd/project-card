package com.example.evan.projectcard.Network.Bluetooth;

import android.os.Handler;
import android.os.Message;

import com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth.BluetoothConstants;
import com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth.EZBluetoothCallback;

/**
 * Created by Evan on 9/28/2016.
 */
public class BluetoothHandler extends Handler {
    EZBluetoothCallback callback;


    public BluetoothHandler(EZBluetoothCallback callback){
        this.callback = callback;
    }

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {

            case BluetoothConstants.CONNECTION_STARTED:
                break;

            case BluetoothConstants.MESSAGE_WRITE:
                break;

            case BluetoothConstants.MESSAGE_READ:
                break;

            case BluetoothConstants.CONNECTION_CLOSED:
                break;
        }
    }
}
