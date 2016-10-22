package com.example.evan.projectcard.MainScreen;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Evan on 8/5/2016.
 */
public class ChangeButtonListAnimationFactory {

    public static Animation create(View toFadeOut, View toFadeIn){
        FadeOutAnimation fadeOut = new FadeOutAnimation(1.0f, 0.0f, toFadeOut);
        FadeInAnimation fadeIn = new FadeInAnimation(0.0f, 1.0f, toFadeIn);
        ChainableAnimationListener listener = new ChainableAnimationListener(){
            @Override
            public void onAnimationEnd(Animation arg0){
                ((FadeOutAnimation) arg0).getView().setVisibility(View.GONE);
                FadeInAnimation fadeIn = (FadeInAnimation) get("fadeIn");
                fadeIn.getView().startAnimation(fadeIn);
            }
        };
        listener.put("fadeIn", fadeIn);
        fadeOut.setAnimationListener(listener);
        return fadeOut;
    }




}

