package com.example.evan.projectcard.Network.Bluetooth;
import android.os.Handler;
import android.os.Message;

import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameConnection;
import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothMove;
import com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth.BluetoothConstants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BluetoothServiceHandler extends Handler{
    BluetoothGameConnection connection;
    boolean packetFlag = false;


    public BluetoothServiceHandler(BluetoothGameConnection connection) {
        this.connection = connection;
    }

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {

            case BluetoothConstants.CONNECTION_STARTED:
                connection.onConnected();
                break;

            case BluetoothConstants.MESSAGE_WRITE:
                break;

            case BluetoothConstants.MESSAGE_READ:
                byte[] readBuf = (byte[]) msg.obj;
                android.util.Log.d("Bluetooth", readBuf.length + "");
                Move move = null;
                try {
                    move = BluetoothMove.deserialize(readBuf);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                connection.readMove(move);
                //android.util.Log.d("Bluetooth", bytes2String(readBuf));
               /* if(bytes2String(readBuf).contains(BluetoothConstants.STREAM_END)){
                    packetFlag = false;
                    connection.flushPayload();
                    android.util.Log.d("Bluetooth", "packet mode off");
                }
                if(packetFlag){
                    connection.recievePacket(readBuf);
                }
                if(bytes2String(readBuf).contains(BluetoothConstants.STREAM_START)){
                    packetFlag = true;
                    android.util.Log.d("Bluetooth", "packet mode on");
                }*/
                break;

            case BluetoothConstants.CONNECTION_CLOSED:
                connection.onDisconnected();
             }
        }

    private String bytes2String(byte[] input){
        String str = "";
        try {
           str  = new String(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}