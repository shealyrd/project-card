package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.SinglePlayerGame.SinglePlayerActivity;

/**
 * Created by Evan on 8/4/2016.
 */
public class SinglePlayerButton extends MainScreenButton {

    public SinglePlayerButton(Context context) {
        super(context);
    }

    public SinglePlayerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SinglePlayerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValues() {
        setText("Single-Player");
        setOnClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SinglePlayerActivity.class);
                getContext().startActivity(intent);
            }
        });

    }
}
