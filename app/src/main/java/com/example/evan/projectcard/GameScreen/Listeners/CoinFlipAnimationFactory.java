package com.example.evan.projectcard.GameScreen.Listeners;

import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;

import com.example.evan.projectcard.GameScreen.Views.CoinFlipView;
import com.example.evan.projectcard.Utilities.ipfx.Interpolation;

/**
 * Created by Evan on 8/23/2016.
 */
public class CoinFlipAnimationFactory {

    public static Animation create(CoinFlipView view){
        final CoinFlipView v = view;
        final Interpolation interpolation = Interpolation.parseUrl("?p=7fff7efd8a8a7fffb81f7fffdea67fff&l=59835fbf538798aaa9386e297c7385e08bd4");
        TranslateAnimation goUpAnimation = new TranslateAnimation(0, 0, 0, -350);
        goUpAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return interpolation.calc(input);
            }
        });
        Animation.AnimationListener goUpListener = new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               v.dialog.makeDecision();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        };
        goUpAnimation.setAnimationListener(goUpListener);
        goUpAnimation.setDuration(2500);
        /*
        TranslateAnimation goUpAnimation = new TranslateAnimation(0, 0,0, -750);
        goUpAnimation.setInterpolator(new DecelerateInterpolator(2f));
        goUpAnimation.setDuration(3000);

        TranslateAnimation goDownAnimation = new TranslateAnimation(0, 0,0, 750);
        goDownAnimation.setInterpolator(new AccelerateInterpolator(2f));
        goDownAnimation.setDuration(1500);

        Animation.AnimationListener goDownListener = new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.INVISIBLE);
                v.setY(v.getY() + 750);
                v.clearAnimation();
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        };

        goDownAnimation.setAnimationListener(goDownListener);

        ChainableAnimationListener listener = new ChainableAnimationListener(){
            @Override
            public void onAnimationEnd(Animation arg0){
                v.setY(v.getY() - 750);
                TranslateAnimation fadeIn = (TranslateAnimation) get("goDown");
                v.startAnimation(fadeIn);
            }
        };
        listener.put("goDown", goDownAnimation);
        goUpAnimation.setAnimationListener(listener);
        return goUpAnimation;
        */
        return goUpAnimation;
    }
}
