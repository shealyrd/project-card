package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.evan.projectcard.MainScreen.MainScreenActivity;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.InvertColorTouchListener;

/**
 * Created by Evan on 8/4/2016.
 */
public class MainScreenButton extends RelativeLayout {
    String displayText = "";
    Button manifest;



    public MainScreenButton(Context context) {
        super(context);
        initialize();
    }

    public MainScreenButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MainScreenButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void setValues(){}
    public void setText(String input){displayText = input;}
    public void setTextSize(int input){manifest.setTextSize(TypedValue.COMPLEX_UNIT_DIP, input);}

    public void initialize(){
        inflate(getContext(), R.layout.main_screen_button_layout, this);
        View child = getChildAt(0);
        manifest = (Button) child;
        initOnTouch();
        setValues();
        manifest.setText(displayText);
    }

    private void initOnTouch(){
        manifest.setOnTouchListener(new InvertColorTouchListener(manifest));
    }

    public void setOnClick(OnClickListener listener){
        manifest.setOnClickListener(listener);
    }

    @Override
    public void startAnimation(Animation animation){
        manifest.startAnimation(animation);
    }
}
