package com.example.evan.projectcard.GameScreen;

import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/18/2016.
 */
public enum CellAlignment {
    Green(R.raw.green_cell),
    Red(R.raw.red_cell);

    public int background;

    private CellAlignment(int background){
        this.background = background;
    }
}
