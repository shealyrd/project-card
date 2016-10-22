package com.example.evan.projectcard.Engine;

public class WideHealEffectFrame extends Frame {
	IdentityCard activator;

	public WideHealEffectFrame(Game game, IdentityCard activator) {
		super(
				game,
				"WideHealEffect");
		this.activator = activator;
	}

	@Override
	public void execute() {
		CardCollection units = activator.getGame().getBoard().getCurrentUnits(activator.getOwner());
		for(Card unit: units){
			((IdentityCard) unit).restoreAllHP();
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
