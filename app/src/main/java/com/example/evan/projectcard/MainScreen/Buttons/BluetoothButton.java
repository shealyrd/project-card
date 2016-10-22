package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.example.evan.projectcard.Network.Bluetooth.BluetoothLobbyActivity;

/**
 * Created by Evan on 8/4/2016.
 */
public class BluetoothButton extends MainScreenButton {

    public BluetoothButton(Context context) {
        super(context);
    }

    public BluetoothButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BluetoothButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValues() {
        setText("Bluetooth");
        setOnClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BluetoothLobbyActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
