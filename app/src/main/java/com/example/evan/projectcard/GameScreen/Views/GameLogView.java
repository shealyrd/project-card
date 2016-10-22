package com.example.evan.projectcard.GameScreen.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.evan.projectcard.GameScreen.TextQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Evan on 8/8/2016.
 */
public class GameLogView extends TextView {
    public TextQueue textQueue = new TextQueue();

    public GameLogView(Context context) {
        super(context);
    }

    public GameLogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameLogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void truncateIfNeeded(){
        //Calculate the height of the text in the MULTILINE TextView
        int textHeight = getLineCount() * getLineHeight();
        if (textHeight > getHeight()) {
            textQueue.removeFirst();
            setText(textQueue.toString());
        }
    }

    public void appendToLog(String s){
        textQueue.add(s);
        setText(textQueue.toString());
        truncateIfNeeded();
    }

}
