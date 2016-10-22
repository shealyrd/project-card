package com.example.evan.projectcard.Network.TCP;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by Evan on 10/2/2016.
 */
public class TCPConnection {
    Socket socket;
    OutputStream socketOutput;
    BufferedReader socketInput;
    TCPCallback callback;
    ConnectedThread mConnectedThread;

    public void setCallback(TCPCallback callback){this.callback = callback;}

    public void connect(String ip, int port){
        final InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
        startConnectThread(socketAddress);
    }

    private void startConnectThread(final InetSocketAddress socketAddress) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    socket.connect(socketAddress);
                    socketOutput = socket.getOutputStream();
                    socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    mConnectedThread = new ConnectedThread(socket);
                    mConnectedThread.start();
                    if(callback != null){
                        callback.onConnect();
                    }
                } catch (IOException e) {
                    disconnect();
                }
            }
        }).start();
    }

    public void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            if(callback != null) {
                callback.onDisconnect();
            }
        }
    }

    public void write(byte[] out) {
        ConnectedThread r;
        synchronized (this) {
            r = mConnectedThread;
        }
        r.write(out);
    }

    public void writeObject(Serializable out) {
        ConnectedThread r;
        synchronized (this) {
            r = mConnectedThread;
        }
        r.writeObject(out);
    }

    private class ConnectedThread extends Thread implements Runnable{
        private final Socket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private ObjectOutputStream mmObjOutStream = null;
        private ObjectInputStream mmObjInStream = null;
        private boolean objectFlag;

        public ConnectedThread(Socket socket){
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
               // Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
            try {
                mmObjOutStream = new ObjectOutputStream(mmOutStream);
                mmObjInStream = new ObjectInputStream(mmInStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void run(){
                if(objectFlag){
                    readObject();
                }
                else {
                    readBytes();
                }
        }

        private void readBytes() {
            byte[] buffer = new byte[1024];
            int bytes;
            try {
                bytes = mmInStream.read(buffer);
                byte[] filteredBuffer = Arrays.copyOfRange(buffer, 0, TCPConstants.OBJECT_FLAG_SET.getBytes().length);
                if (Arrays.equals(filteredBuffer, TCPConstants.OBJECT_FLAG_SET.getBytes())) {
                    Log.d("Bluetooth", "Object flag set");
                    objectFlag = true;
                } else {
                   callback.onRead(buffer);
                }
            } catch (IOException e) {
                disconnect();
            }
        }

        private void readObject() {
            Object payload = null;
            Log.d("Bluetooth", "ObjectInputStream established");
            try{
                try {
                    Log.d("Bluetooth", "Payload reading");
                    payload = mmObjInStream.readObject();
                    resetObjectInputStream();
                    Log.d("Bluetooth", "Payload read");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                callback.onReadObject(payload);
                Log.d("Bluetooth", "Payload sent to handler");
                objectFlag = false;
            } catch (IOException e) {
                disconnect();
            }
        }

        public void write(byte[] message){
            try {
                socketOutput.write(message);
            } catch (IOException e) {
                if(callback != null)
                    disconnect();
            }
        }

        public void writeObject(Serializable out) {
            Log.d("Bluetooth", "Writing Object in connection");
            try {
                //tmmObjOutStream.flush();
                synchronized(mmObjOutStream){
                    mmObjOutStream.writeObject(out);
                    Log.d("Bluetooth", "Output stream written");
                    callback.onWriteObject();
                    resetObjectOutputStream();
                }
            } catch (IOException e) {
                //Log.e(TAG, "Exception during write", e);
            }
        }

        public void resetObjectOutputStream(){
            try {
                //mmObjOutStream.reset();
                //mmObjOutStream.flush();
                mmObjOutStream = new ObjectOutputStream(mmOutStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void resetObjectInputStream(){
            try {
                //mmObjInStream.reset();
                mmObjInStream = new ObjectInputStream(mmInStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                mmInStream.close();
                mmOutStream.close();
                mmObjOutStream.close();
                mmObjInStream.close();
                mmSocket.close();
            } catch (IOException e) {
                //Log.e(TAG, "close() of connect socket failed", e);
            }
        }


    }






}

