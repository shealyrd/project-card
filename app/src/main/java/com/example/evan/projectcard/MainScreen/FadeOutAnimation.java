package com.example.evan.projectcard.MainScreen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by Evan on 8/5/2016.
 */
public class FadeOutAnimation extends AlphaAnimation {
    View subject = null;

    public FadeOutAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(null);
    }

    public FadeOutAnimation(float fromAlpha, float toAlpha) {
        super(fromAlpha, toAlpha);
        initialize(null);
    }
    public FadeOutAnimation(float fromAlpha, float toAlpha, View subject) {
        super(fromAlpha, toAlpha);
        initialize(subject);
    }

    public void setView(View subject){
        this.subject = subject;
    }

    public View getView(){
        return subject;
    }

    public void initialize(View subject){
        setView(subject);
        setDuration(1000);
        setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                ((FadeOutAnimation) arg0).getView().setVisibility(View.INVISIBLE);
            }
        });
    }
}
