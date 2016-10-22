package com.example.evan.projectcard.Utilities;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;

import com.example.evan.projectcard.GameScreen.CardDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Evan on 9/20/2016.
 */
public class DeckFileHandler {
    Context c;
    int size = CardDatabase.size();
    String deckFilename = "deck.txt";

    public DeckFileHandler(Context c){
        this.c = c;
    }

    public  void writeDefaultDeck(){
        DeckRepresentation deck = new DeckRepresentation();
        for(int i = 0; i < size; i++){
            deck.set(i, 3);
        }
        Log.d("DECK DEFAULT", deck.toString());
        writeDeckFile(deck);
    }

    public void writeDeckFile(DeckRepresentation input){
        Log.d("DECK INPUT", input.toString());
        FileOutputStream os = null;
        try {
            os = c.openFileOutput(deckFilename, c.MODE_PRIVATE);
            byte[] bytes = input.toString().getBytes("UTF-8");
            os.write(bytes.length);
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Integer> readDeckFromFile() {
        ArrayList<Integer> cardList = new ArrayList<Integer>();
        try {
            FileInputStream fileIn = c.openFileInput(deckFilename);
            int length = fileIn.read();
            byte[] bytes = new byte[length];
            fileIn.read(bytes);
            String text = new String(bytes, "UTF-8");
            Log.d("DECK READ", text);
            fileIn.close();
            String[] tokens = text.split(",");
            //Log.d("DECK", Arrays.toString(tokens));
            for(String token: tokens){
                int id = Integer.parseInt(token);
                cardList.add(id);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cardList;
    }

    public Integer[] readStoredDeckToIntArray(){
        ArrayList<Integer> list = readDeckFromFile();
        Integer[] result = new Integer[size];
        list.toArray(result);
        return result;
    }


}
