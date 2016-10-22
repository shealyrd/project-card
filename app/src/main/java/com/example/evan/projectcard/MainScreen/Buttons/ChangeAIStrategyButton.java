package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by Evan on 8/4/2016.
 */
public class ChangeAIStrategyButton extends MainScreenButton {

    public ChangeAIStrategyButton(Context context) {
        super(context);
    }

    public ChangeAIStrategyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeAIStrategyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValues() {
        setText("Change AI Strategy");
        setTextSize(20);
    }
}
