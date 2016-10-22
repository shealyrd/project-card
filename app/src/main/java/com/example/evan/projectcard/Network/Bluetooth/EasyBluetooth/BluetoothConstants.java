package com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth;

/**
 * Created by Evan on 9/23/2016.
 */
public interface BluetoothConstants {

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    public static final int CONNECTION_CLOSED = 6;
    public static final int CONNECTION_STARTED = 7;
    public static final int OBJECT_READ = 8;
    public static final int OBJECT_WRITE = 9;

    public static final String OBJECT_FLAG_SET = "OBJECT_FLAG_SET";

}