package com.example.evan.projectcard.Network.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Evan on 9/23/2016.
 */
public class BluetoothLobbyActivity extends Activity {
    Set<BluetoothDevice> pairedDevices;
    ListView listV;
    Context context = this;
    String chosenAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);
        populateList();
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });
    }

    public void populateList(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<String> s = new ArrayList<String>();

        for(BluetoothDevice bt : pairedDevices)
            s.add(bt.getName() + " (" + bt.getAddress() + ")");

        listV = (ListView) findViewById(R.id.listView);
        listV.setAdapter(new ArrayAdapter<String>(this, R.layout.lobby_list_item, R.id.device_name, s));
        AdapterView.OnItemClickListener mDeviceClickListener
                = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
                String fullName = ((TextView) v.findViewById(R.id.device_name)).getText().toString();
                chosenAddress = fullName.substring(fullName.indexOf("(")+1,fullName.indexOf(")"));
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you want to play against " + chosenAddress + "?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                Intent k = new Intent(context, BluetoothGameActivity.class);
                                k.putExtra("CHOSEN_DEVICE_MAC_ADDRESS", chosenAddress);
                                startActivity(k);
                                finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        };
        listV.setOnItemClickListener(mDeviceClickListener);
    }

    public void buttonClick(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn, 0);
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          populateList();
                                      }
                                  }, 5000);
    }
}


