package com.example.evan.projectcard.GameScreen.Views;

import android.content.Context;
import android.util.AttributeSet;

import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.GifView;

import java.io.IOException;

/**
 * Created by Evan on 8/8/2016.
 */
public class GameScreenBackgroundView extends GifView {
    public GameScreenBackgroundView(Context context) throws IOException {
        super(context);
    }

    public GameScreenBackgroundView(Context context, AttributeSet attrs) throws IOException {
        super(context, attrs);
    }

    public GameScreenBackgroundView(Context context, AttributeSet attrs, int defStyle) throws IOException {
        super(context, attrs, defStyle);
    }

    @Override
    public void setValues(){
        setFullScreenMode();
        setImgID(R.raw.background_40);
    }
}
