package com.example.evan.projectcard.Network;

import com.example.evan.projectcard.Engine.Direction;

/**
 * Created by Evan on 9/30/2016.
 */
public class PerceptionFlipper {

    public static Direction flipDirection(Direction dir){
        Direction result = null;
        switch(dir){
            case UP: result = Direction.DOWN; break;
            case RIGHT: result = Direction.LEFT; break;
            case DOWN: result = Direction.UP; break;
            case LEFT: result = Direction.RIGHT; break;
            default: result = Direction.None; break;
        }
        return result;
    }

    public static int flipIndex(int index){
        int result = 0;
        switch(index){
            case 1: result = 18; break;
            case 2: result = 17; break;
            case 3: result = 16; break;
            case 4: result = 15; break;
            case 5: result = 14; break;
            case 6: result = 13; break;
            case 7: result = 12; break;
            case 8: result = 11; break;
            case 9: result = 10; break;
            case 10: result = 9; break;
            case 11: result = 8; break;
            case 12: result = 7; break;
            case 13: result = 6; break;
            case 14: result = 5; break;
            case 15: result = 4; break;
            case 16: result = 3; break;
            case 17: result = 2; break;
            case 18: result = 1; break;
            default: result = 0; break;
        }
        return result;
    }
}
