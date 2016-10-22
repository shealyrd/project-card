package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.evan.projectcard.MainScreen.MainScreenActivity;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/4/2016.
 */
public class OptionsButton extends MainScreenButton {

    public OptionsButton(Context context) {
        super(context);
    }

    public OptionsButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OptionsButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValues(){
        setText("Options");
        setOnClick(new OnClickListener() {
            public void onClick(View v) {
                 ((MainScreenActivity) getContext()).changeButtonListView(R.id.button_holder_options);
             }
        });
    }
}
