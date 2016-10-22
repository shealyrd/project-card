package com.example.evan.projectcard.MainScreen;

import android.view.View;
import android.view.animation.Animation;

import java.util.HashMap;

/**
 * Created by Evan on 8/5/2016.
 */
public class ChainableAnimationListener implements Animation.AnimationListener {
    HashMap<String, Animation> map = new HashMap<String, Animation>();

    public void put(String name, Animation val){
        if(!map.containsKey(name)){
            map.put(name, val);
        }
    }

    public Animation get(String name){
        if(map.containsKey(name)){
            return map.get(name);
        }
        return null;
    }

    @Override
    public void onAnimationStart(Animation arg0) {
    }

    @Override
    public void onAnimationRepeat(Animation arg0) {
    }

    @Override
    public void onAnimationEnd(Animation arg0) {

    }
}
