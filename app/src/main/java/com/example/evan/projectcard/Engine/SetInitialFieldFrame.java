package com.example.evan.projectcard.Engine;

public class SetInitialFieldFrame extends Frame {

	public SetInitialFieldFrame(Game game) {
		super(game,
				"SetInitialField");
	}

	@Override
	public void execute() {
		
	}

	@Override
	public Frame getNextFrame() {
		return new StartFirstTurnFrame(game);
	}

}
