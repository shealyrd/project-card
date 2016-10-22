package com.example.evan.projectcard.Utilities;

import java.io.Serializable;

/**
 * Created by Evan on 8/28/2016.
 */
public class R2Point implements Serializable{
    public int x;
    public int y;

    public R2Point(){}
    public R2Point(int x, int y){this.x = x; this.y = y;};
    public String toString(){
        return x + ", " + y;
    }
}


