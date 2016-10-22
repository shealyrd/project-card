package com.example.evan.projectcard.GameScreen;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.example.evan.projectcard.MainScreen.ChainableAnimationListener;
import com.example.evan.projectcard.MainScreen.FadeInAnimation;
import com.example.evan.projectcard.MainScreen.FadeOutAnimation;

/**
 * Created by Evan on 8/24/2016.
 */
public class OpeningSpinAnimationFactory {

    public static Animation create(View toSpin){
        Animation spinAnimation = new RotateAnimation(0, 2160, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        Animation alphaAnimation = new AlphaAnimation(0, 1);
        Animation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(spinAnimation);
        set.addAnimation(alphaAnimation);
        set.addAnimation(scaleAnimation);
        set.setDuration(2000);
        return set;
    }

    public static Animation createNoRotate(){
        Animation alphaAnimation = new AlphaAnimation(0, 1);
        Animation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(alphaAnimation);
        set.addAnimation(scaleAnimation);
        set.setDuration(2000);
        set.setInterpolator(new BounceInterpolator());
        return set;
    }

    public static Animation createAlphaCycle(){
        Animation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        return alphaAnimation;
    }

}
