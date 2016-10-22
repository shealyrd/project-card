package com.example.evan.projectcard.GameScreen.Views;

import android.content.Context;
import android.util.AttributeSet;

import com.example.evan.projectcard.GameScreen.Dialogs.CoinFlipDialog;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.SimpleGestureFilter;
import com.example.evan.projectcard.Utilities.GifView;

import java.io.IOException;

/**
 * Created by Evan on 8/22/2016.
 */
public class CoinFlipView extends GifView {
    public CoinFlipDialog dialog;
    public boolean canFlip = false;
    private SimpleGestureFilter detector;

    public CoinFlipView(Context context) throws IOException {
        super(context);
        //setOnClickListener(new CoinFlipClickListener());
        //setGestureListener();
    }

    public CoinFlipView(Context context, AttributeSet attrs) throws IOException {
        super(context, attrs);
        //setOnClickListener(new CoinFlipClickListener());
        //setGestureListener();
    }

    public CoinFlipView(Context context, AttributeSet attrs, int defStyle) throws IOException {
        super(context, attrs, defStyle);
        //setOnClickListener(new CoinFlipClickListener());
        //setGestureListener();
    }



    @Override
    public void setValues(){
        setScale(10);
        setImgID(R.raw.card_flip_clear);
    }

}


