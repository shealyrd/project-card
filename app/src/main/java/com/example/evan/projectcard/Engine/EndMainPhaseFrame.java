package com.example.evan.projectcard.Engine;

public class EndMainPhaseFrame extends Frame {

	public EndMainPhaseFrame(Game game) {
		super(game,
				"EndMainPhase");
	}

	@Override
	public void execute() {
		game.startNextFrame();
	}

	@Override
	public Frame getNextFrame() {
		return new EndPhaseFrame(game);
	}

}
