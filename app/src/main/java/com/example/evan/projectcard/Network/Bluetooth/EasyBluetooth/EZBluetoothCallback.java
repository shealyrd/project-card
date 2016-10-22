package com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Evan on 9/28/2016.
 */
public abstract class EZBluetoothCallback {
    Handler handler;

    public EZBluetoothCallback(){
        handler = createHandler();
    }

    protected Handler createHandler(){
        return new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {

                    case BluetoothConstants.CONNECTION_STARTED:
                        EZBluetoothCallback.this.onConnect();
                        break;

                    case BluetoothConstants.MESSAGE_WRITE:
                        EZBluetoothCallback.this.onWrite();
                        break;

                    case BluetoothConstants.MESSAGE_READ:
                        byte[] readBuf = (byte[]) msg.obj;
                        EZBluetoothCallback.this.onRead(readBuf);
                        break;

                    case BluetoothConstants.OBJECT_WRITE:
                        EZBluetoothCallback.this.onWriteObject();
                        break;

                    case BluetoothConstants.OBJECT_READ:
                        Object readObj =  msg.obj;
                        EZBluetoothCallback.this.onReadObject(readObj);
                        break;

                    case BluetoothConstants.CONNECTION_CLOSED:
                        EZBluetoothCallback.this.onDisconnect();
                        break;
                }
            }
        };
    }

    public Handler getHandler(){
        return handler;
    }

    abstract public void onConnect();
    abstract public void onDisconnect();
    abstract public void onWrite();
    abstract public void onRead(byte[] payload);
    abstract public void onWriteObject();
    abstract public void onReadObject(Object payload);


}
