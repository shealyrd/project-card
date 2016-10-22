package com.example.evan.projectcard.Network;

import com.example.evan.projectcard.Engine.Game;
import com.example.evan.projectcard.Engine.Move;

/**
 * Created by Evan on 10/13/2016.
 */
public interface NetworkGameActivity {

    public void sendMove(Move move);
    public void recieveMove(Move move);

    Game getGame();

    boolean isFirstPlayer();
}
