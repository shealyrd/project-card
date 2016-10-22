package com.example.evan.projectcard.Network.Bluetooth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Created by Evan on 9/25/2016.
 */
public class BluetoothPayload {
    ArrayList<byte[]> packets = new ArrayList<byte[]>();

    public void add(byte[] input){
        packets.add(input);
    }

    public byte[] toByteArray(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        for(byte[] p: packets){
            try {
                outputStream.write(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream.toByteArray();
    }

    public void clear(){
        packets.clear();
    }
}
