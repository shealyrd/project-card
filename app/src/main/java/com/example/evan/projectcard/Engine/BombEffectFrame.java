package com.example.evan.projectcard.Engine;

public class BombEffectFrame extends Frame {
	Cell activatedIn;
	Card_Bomb bomb;

	public BombEffectFrame(Game game, Card_Bomb bomb) {
		super(game,
				"BombEffect");
		this.bomb = bomb;
		this.activatedIn = bomb.getCell();
	}

	@Override
	public void execute() {
		Cell chosen =  new ChooseNonemptyAdjacentCellFrame(game, activatedIn).getChoice();
		chosen.destroyAllCards();
	}

	@Override
	public Frame getNextFrame() {
		return null;
	}

}
