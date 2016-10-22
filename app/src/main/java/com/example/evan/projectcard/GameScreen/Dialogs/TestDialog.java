package com.example.evan.projectcard.GameScreen.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.evan.projectcard.GameScreen.OpeningSpinAnimationFactory;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/24/2016.
 */
public class TestDialog extends Dialog {
    public TestDialog(Context context) {
        super(context);
        setContentView(R.layout.test_dialog);
        ImageView testView = (ImageView) findViewById(R.id.test_spin);
        Animation anim = OpeningSpinAnimationFactory.create(testView);
        testView.startAnimation(anim);
    }

    public TestDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TestDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
