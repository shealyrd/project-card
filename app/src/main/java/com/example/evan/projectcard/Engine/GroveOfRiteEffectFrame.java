package com.example.evan.projectcard.Engine;

public class GroveOfRiteEffectFrame extends Frame {
	Card_GroveOfRite activator;
	int cellToSummonIn = -1;

	public GroveOfRiteEffectFrame(Game game, Card_GroveOfRite activator) {
		super(
				game,
				"GroveOfRiteEffect");
		this.activator = activator;
	}
	
	public GroveOfRiteEffectFrame(Game game, Card_GroveOfRite activator, int cellToSummonIn) {
		super(
				game,
				"GroveOfRiteEffect");
		this.activator = activator;
		this.cellToSummonIn = cellToSummonIn;
	}
	
	

	@Override
	public void execute() {
		Cell cellChoice;
		IdentityCard chosen = (IdentityCard) new ChooseUnitInDiscardPileFrame(game, activator.getCell().getDiscardPile()).getChoice();
		if(game instanceof ComVsComGame){
			if(cellToSummonIn != -1){
				cellChoice = game.board.getCellAtIndex(cellToSummonIn);
			}
			else{
				ChooseGroveOfRiteCellTree tree = new ChooseGroveOfRiteCellTree(game.board, this.activator);
				int index = tree.getBestIndex();
				cellChoice = game.board.getCellAtIndex(index);
			}
		}
		else{
			cellChoice = new ChooseAdjacentCellFrame(game, activator.getCell()).getChoice();
		}
		activator.getCell().getIdentity().reduceAPBy(2);
		Direction directionChoice = new ChooseDirectionFrame(game).getChoice();
		game.playUnit(chosen, cellChoice, directionChoice);
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
