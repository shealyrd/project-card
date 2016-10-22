package com.example.evan.projectcard.Engine;

public class ActivateEffectFrame extends Frame {

	ActivateEffectMove move;
	Card activator;

	public ActivateEffectFrame(Game game, ActivateEffectMove move) {
		super(game,
				"ActivateEffect");
		this.move = move;
		this.activator = move.getActivator();
	}

	@Override
	public void execute() {
		game.activateEffect(activator);
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
