package com.example.evan.projectcard.Engine;

public class DestroyUnitFrame extends Frame {
	IdentityCard destroyed;

	public DestroyUnitFrame(Game game, IdentityCard destroyed) {
		super(game,
				"DestroyUnit");
		this.destroyed = destroyed;
	}

	@Override
	public void execute() {
		destroyed.getCell().removeUnit();
		destroyed.getCell().addToDiscardPile(destroyed);
		destroyed.setFieldStatus(FieldStatus.DiscardPile);
		destroyed.onDestroy();
		//game.destroyUnit(destroyed);
	}

	@Override
	public Frame getNextFrame() {
		return null;
	}

}
