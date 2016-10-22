package com.example.evan.projectcard.GameScreen;

import java.util.LinkedList;

/**
 * Created by Evan on 8/8/2016.
 */
public class TextQueue extends LinkedList<String> {

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(String s: this){
            builder.append("> " + s + "\n");
        }
        return builder.toString();
    }
}
