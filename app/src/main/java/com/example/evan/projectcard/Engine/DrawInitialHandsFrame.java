package com.example.evan.projectcard.Engine;

import android.os.Handler;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;

public class DrawInitialHandsFrame extends Frame {


	public DrawInitialHandsFrame(Game game) {
		super(game,
				"DrawInitialHands");
	}

	@Override
	public void execute() {
		for(Player player : game.players){
			DrawCardFrame drawFrame = new DrawCardFrame(player, 5);
			drawFrame.setListener(new BasicListener() {
				@Override
				public void onEvent() {
					game.startNextFrame();
				}
			});
			drawFrame.launch();
		}
	}

	@Override
	public Frame getNextFrame() {
		return new StartFirstTurnFrame(game);
	}

}
