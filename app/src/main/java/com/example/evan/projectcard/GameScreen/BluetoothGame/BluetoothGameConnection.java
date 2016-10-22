package com.example.evan.projectcard.GameScreen.BluetoothGame;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.widget.Toast;

import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.Network.Bluetooth.oldBluetoothConnectionService;
import com.example.evan.projectcard.Network.Bluetooth.BluetoothPayload;
import com.example.evan.projectcard.Network.Bluetooth.BluetoothServiceHandler;

import java.io.IOException;

/**
 * Created by Evan on 9/25/2016.
 */
public class BluetoothGameConnection {
    oldBluetoothConnectionService connection;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothServiceHandler mHandler;
    BluetoothGameActivity context;

    BluetoothPayload payload = new BluetoothPayload();

    public BluetoothGameConnection(BluetoothGameActivity context, String address){
        this.context = context;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.enable();
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        mHandler = new BluetoothServiceHandler(this);
        connection = new oldBluetoothConnectionService(context, mHandler);
        connection.connect(device, false);
    }

    public void onConnected(){
        Toast.makeText(context,"Connected",Toast.LENGTH_LONG).show();
        context.dismissProgressDialog();
        context.startGame();
    }

    public void onDisconnected(){
        Toast.makeText(context,"Disconnected",Toast.LENGTH_LONG).show();
        context.finish();
    }

    public void writeMove(Move move){
        BluetoothMove bMove = new BluetoothMove(move);
        try {
           // Toast.makeText(context, "Move written", Toast.LENGTH_SHORT).show();
            byte[] payload = bMove.serialize();
            Toast.makeText(context, payload.length + "", Toast.LENGTH_SHORT).show();
            connection.currentBuffer= payload.length;
            connection.write(payload);

        } catch (IOException e) {
            Toast.makeText(context, "Move writing error", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void readMove(Move move){
        if(move != null){
            context.recieveMove(move);
        }
        else{
            Toast.makeText(context, "Move is null", Toast.LENGTH_SHORT);
        }
    }

}
