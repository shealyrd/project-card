package com.example.evan.projectcard.Network.TCP;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.evan.projectcard.R;

import java.util.Set;

/**
 * Created by Evan on 10/2/2016.
 */
public class TCPLobbyActivity extends Activity {
    Button button;
    Set<String> connectionChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);
        //populateList();
        button = (Button) findViewById(R.id.button1);
    }


}
