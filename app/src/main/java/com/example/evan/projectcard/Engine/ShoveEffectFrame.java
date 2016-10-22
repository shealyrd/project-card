package com.example.evan.projectcard.Engine;

public class ShoveEffectFrame extends Frame {
	IdentityCard activator;

	public ShoveEffectFrame(Game game, IdentityCard activator) {
		super(
				game,
				"ShoveEffect");
		this.activator = activator;
	}

	@Override
	public void execute() {
		IdentityCard unit = Algorithmics.getCellFacing((IdentityCard) activator).getIdentity();
		Cell toMoveTo = Algorithmics.getCellAtBoundary(unit.getCell(), activator.getDirection());
		game.moveUnit(unit, toMoveTo, unit.getDirection());
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
