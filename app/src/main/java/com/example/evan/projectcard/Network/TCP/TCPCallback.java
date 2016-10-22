package com.example.evan.projectcard.Network.TCP;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Evan on 10/4/2016.
 */
public abstract class TCPCallback {

    Handler handler;

    public TCPCallback(){
        handler = createHandler();
    }

    protected Handler createHandler(){
        return new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {

                    case TCPConstants.CONNECTION_STARTED:
                        TCPCallback.this.onConnect();
                        break;

                    case TCPConstants.MESSAGE_WRITE:
                        TCPCallback.this.onWrite();
                        break;

                    case TCPConstants.MESSAGE_READ:
                        byte[] readBuf = (byte[]) msg.obj;
                        TCPCallback.this.onRead(readBuf);
                        break;

                    case TCPConstants.OBJECT_WRITE:
                        TCPCallback.this.onWriteObject();
                        break;

                    case TCPConstants.OBJECT_READ:
                        Object readObj =  msg.obj;
                        TCPCallback.this.onReadObject(readObj);
                        break;

                    case TCPConstants.CONNECTION_CLOSED:
                        TCPCallback.this.onDisconnect();
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

