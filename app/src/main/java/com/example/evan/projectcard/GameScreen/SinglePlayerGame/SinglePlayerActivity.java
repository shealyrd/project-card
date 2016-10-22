package com.example.evan.projectcard.GameScreen.SinglePlayerGame;

import android.os.Bundle;

import com.example.evan.projectcard.GameScreen.GameActivity;

/**
 * Created by Evan on 9/23/2016.
 */
public class SinglePlayerActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startGame();
    }
}
