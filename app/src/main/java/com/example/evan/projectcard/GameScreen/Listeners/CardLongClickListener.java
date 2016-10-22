package com.example.evan.projectcard.GameScreen.Listeners;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View;
import android.widget.ImageView;

import com.example.evan.projectcard.Utilities.ImageDragShadowBuilder;

/**
 * Created by Evan on 8/16/2016.
 */
public class CardLongClickListener implements View.OnLongClickListener {


    @Override
    public boolean onLongClick(View v) {
        Log.d("Tag", "onTouch");
        Activity activity = (Activity) v.getContext();
        View.DragShadowBuilder shadowBuilder = ImageDragShadowBuilder.fromResource(activity, ((ImageView) v).getDrawable());
        v.startDrag(null, shadowBuilder, v, 0);
        v.setVisibility(View.GONE);
        return true;
    }
}
