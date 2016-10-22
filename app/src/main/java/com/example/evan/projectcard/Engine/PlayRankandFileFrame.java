package com.example.evan.projectcard.Engine;

public class PlayRankandFileFrame extends Frame{
	Cell playedIn;
	Direction direction;

	public PlayRankandFileFrame(Game game, Cell playedIn, Direction direction) {
		super(game,
				"PlayRankandFile");
		this.playedIn = playedIn;
		this.direction = direction;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
