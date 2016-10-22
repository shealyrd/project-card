package com.example.evan.projectcard.Engine;

import java.util.Random;

public class ChooseAdjacentUnitFrame extends Frame {
	Cell cell;
	IdentityCard chosen;

	public ChooseAdjacentUnitFrame(Game game, Cell cell) {
		super(game,
				"ChooseAdjacentUnit");
		this.cell = cell;
	}

	@Override
	public void execute() {
	if(game.currentPlayer == game.players[1]){
		CellCollection adjacent = Algorithmics.getAdjacentCells(cell, game.getBoard());
		CardCollection choices = new CardCollection();
		for(Cell cell: adjacent){
			if(cell.hasUnit()){
				choices.add(cell.getIdentity());
			}
		}
		Log.add(choices.toString());
		Random rand = new Random();
		int choiceIndex = rand.nextInt(choices.size());
		chosen = (IdentityCard) choices.getAtIndex(choiceIndex);
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IdentityCard getChoice(){
		this.launch();
		return chosen;
	}

}
