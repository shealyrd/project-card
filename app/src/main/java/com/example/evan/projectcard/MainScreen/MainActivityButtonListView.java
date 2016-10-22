package com.example.evan.projectcard.MainScreen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

/**
 * Created by Evan on 8/4/2016.
 */

public class MainActivityButtonListView extends LinearLayout {

    public MainActivityButtonListView(Context context) {
        super(context);
    }

    public MainActivityButtonListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainActivityButtonListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void fadeOut() {
        for (int index = 0; index < getChildCount(); ++index) {
            View nextChild = getChildAt(index);
            FadeOutAnimation alpha = new FadeOutAnimation(1.0f, 0.0f, nextChild);
            nextChild.startAnimation(alpha);
        }
    }


}
