package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.FrontEndDirection;

/**
 * Created by Evan on 8/17/2016.
 */
public enum Direction {
    None(
            0,
            null),
    UP(
            0,
            new int[] {20, 0, 20, 0}),
    RIGHT(
            90,
            new int[] {-8, -20, 45, 28}),
    DOWN(
            180,
            new int[] {-28, 0, 68, 0}),
    LEFT(
            270,
            new int[] {0, 20, 35, -20});

    public float degree;
    public int[] offsets;

    Direction(int degree, int[] offsets) {
        this.degree = degree;
        this.offsets = offsets;
    }

    public FrontEndDirection toFrontEndDirection(){
        return FrontEndDirection.values()[ordinal()];
    }

    public float getDegree(){
        return degree;
    }

    public int[] getOffets(){
        return offsets;
    }
}
