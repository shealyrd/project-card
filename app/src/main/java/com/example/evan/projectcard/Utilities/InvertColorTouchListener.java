package com.example.evan.projectcard.Utilities;

import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Evan on 10/15/2016.
 */
public class InvertColorTouchListener implements View.OnTouchListener {
    View manifest;
    Drawable draw;
    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255,
            0, -1.0f,     0,    0, 255,
            0,     0, -1.0f,    0, 255,
            0,     0,     0, 1.0f,   0
    };

    public InvertColorTouchListener(View v){
        manifest = v;
        if(v instanceof Button){
            draw = v.getBackground();
        }
        if(v instanceof ImageView){
            draw = ((ImageView) v).getDrawable();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                invertColors();
                break;
            case MotionEvent.ACTION_UP:
                clearColors();
                break;
        }
        return false;
    }

    public void clearColors(){
        draw.clearColorFilter();
    }

    public void invertColors(){
        draw.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
    }
}
