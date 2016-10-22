package com.example.evan.projectcard.Engine;

public class WinGameFrame extends Frame {
	Player winner;

	public WinGameFrame(Game game, Player winner) {
		super(game,
				"WinGame");
		this.winner = winner;
	}

	@Override
	public void execute() {
		Log.add(winner.getName() + " has won!");
		game.end();
		game.winner = winner;
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
