package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothMove;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Evan on 8/4/2016.
 */

public class MusicButton extends MainScreenButton {

    public MusicButton(Context context) {
        super(context);
    }

    public MusicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValues() {
        setText("Music");
        setOnClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothMove move = new BluetoothMove(new Move());
                byte[] bytes = null;
                try {
                    bytes = move.serialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
                byte[] data = Base64.decode(base64, Base64.DEFAULT);
                if(Arrays.equals(bytes, data)){
                    Toast.makeText(MusicButton.this.manifest.getContext(), "It was inverted correctly", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MusicButton.this.manifest.getContext(), "It was lossy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
