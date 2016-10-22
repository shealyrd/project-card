package com.example.evan.projectcard.Engine;

public class ResourcePopulationFrame extends Frame {
	boolean isFirstTurn;

	public ResourcePopulationFrame(Game game) {
		super(game,
				"ResourcePopulation");
	}
	
	public ResourcePopulationFrame(Game game, boolean isFirstTurn) {
		super(game,
				"ResourcePopulation");
		this.isFirstTurn = isFirstTurn;
	}

	@Override
	public void execute() {
		
		CellCollection cells = game.getBoard().getCellsOwnerBy(game.currentPlayer);
		game.currentPlayer.getResourcePool().addBasic(cells.size());
		
		CardCollection cards = game.getBoard().getAllCardsOnField();
		for(Card card: cards){
			card.onHarvestPhase();
		}
		game.startNextFrame();
	}

	@Override
	public Frame getNextFrame() {
		if(isFirstTurn){
			return new SetInitialFieldFrame(game);
		}
		return new StartTurnFrame(game);
	}

}
