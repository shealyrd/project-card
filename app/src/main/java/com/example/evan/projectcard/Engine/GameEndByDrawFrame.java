package com.example.evan.projectcard.Engine;

public class GameEndByDrawFrame extends Frame {

	public GameEndByDrawFrame(Game game) {
		super(game,
				"GameEndByDraw");
	}

	@Override
	public void execute() {
		Log.add("The game has ended in a draw.");
		game.endByDraw();
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
