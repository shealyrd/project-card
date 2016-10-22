package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.GameActivity;

/**
 * Created by Evan on 8/28/2016.
 */
public class PlayerMainPhaseFrame extends Frame{

    public PlayerMainPhaseFrame(Game game) {
        super(game, "PlayerMainPhaseFrame");
    }

    @Override
    public void execute() {
        GameActivity activity = (GameActivity) game.getActivity();
        activity.activateEndPhaseButton();
        activity.activateCells();
        activity.activateHand();
    }

    @Override
    public Frame getNextFrame() {
        return new EndPhaseFrame(game);
    }
}
