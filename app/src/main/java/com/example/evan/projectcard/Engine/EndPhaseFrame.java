package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.GameActivity;

public class EndPhaseFrame extends Frame {

	public EndPhaseFrame(Game game) {
		super(game,
				"EndPhase");
	}

	@Override
	public void execute() {
		((GameActivity) game.getActivity()).deactivateCells();
		game.swapPlayers();
		game.startNextFrame();
	}

	@Override
	public Frame getNextFrame() {
		return new ResourcePopulationFrame(game);
	}

}
