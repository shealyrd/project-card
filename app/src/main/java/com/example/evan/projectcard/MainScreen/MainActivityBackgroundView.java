package com.example.evan.projectcard.MainScreen;

import android.content.Context;
import android.util.AttributeSet;

import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.GifView;

import java.io.IOException;

/**
 * Created by Evan on 8/4/2016.
 */
public class MainActivityBackgroundView extends GifView {

    public MainActivityBackgroundView(Context context) throws IOException {
        super(context);
    }

    public MainActivityBackgroundView(Context context, AttributeSet attrs) throws IOException {
        super(context, attrs);
    }

    public MainActivityBackgroundView(Context context, AttributeSet attrs, int defStyle) throws IOException {
        super(context, attrs, defStyle);
    }

    @Override
    public void setValues(){
        setFullScreenMode();
        setImgID(R.raw.testbackground);
    }
}
