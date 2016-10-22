package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.example.evan.projectcard.GameScreen.OnlineGame.OnlineLobbyActivity;

/**
 * Created by Evan on 8/4/2016.
 */
public class OnlineButton extends MainScreenButton {

    public OnlineButton(Context context) {
        super(context);
    }

    public OnlineButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OnlineButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValues() {
        setText("Online");
        setOnClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OnlineLobbyActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
