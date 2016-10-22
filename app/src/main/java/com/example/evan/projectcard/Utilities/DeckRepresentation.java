package com.example.evan.projectcard.Utilities;

import com.example.evan.projectcard.GameScreen.CardDatabase;

/**
 * Created by Evan on 9/20/2016.
 */
public class DeckRepresentation {
    int[] counts = new int[CardDatabase.size()];

    public DeckRepresentation() {}

    public DeckRepresentation(Integer[] inputs) {
        for(int i = 0; i < counts.length; i++){
            counts[i] = inputs[i];
        }
    }

    public void set(int index, int count){
        counts[index] = count;
    }

    public void increment(int index){
        counts[index] = counts[index]++;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        String prefix = "";
        for(int i: counts){
            builder.append(prefix);
            prefix = ",";
            builder.append(i);
        }
        return builder.toString();
    }
}
