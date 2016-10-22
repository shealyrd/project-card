package com.example.evan.projectcard.GameScreen.BluetoothGame;

import com.example.evan.projectcard.Engine.Move;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Evan on 9/25/2016.
 */
public class BluetoothMove {
    Move move;

    public BluetoothMove(Move move){
        this.move = move;
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(move);
        return b.toByteArray();
    }

    //AbstractMessage was actually the message type I used, but feel free to choose your own type
    public static Move deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return (Move) o.readObject();
    }


}
