package com.example.evan.projectcard.GameScreen;

/**
 * Created by Evan on 8/17/2016.
 */
public enum FrontEndDirection {
    None(
            0,
            null),
    Up(
            0,
            new int[] {20, 0, 20, 0}),
    Right(
            90,
            new int[] {-8, -20, 45, 28}),
    Down(
            180,
            new int[] {-28, 0, 68, 0}),
    Left(
            270,
            new int[] {0, 20, 35, -20});

    public float degree;
    public int[] offsets;

    FrontEndDirection(int degree, int[] offsets) {
        this.degree = degree;
        this.offsets = offsets;
    }

    public com.example.evan.projectcard.Engine.Direction toBackEndDirection(){
        return com.example.evan.projectcard.Engine.Direction.values()[ordinal()];
    }

    public float getDegree(){
        return degree;
    }

    public int[] getOffets(){
        return offsets;
    }
}
