package com.example.evan.projectcard.Engine;

import java.util.Random;

public class ChooseNonemptyAdjacentCellFrame extends Frame {
	Cell cell;
	Cell chosen;

	public ChooseNonemptyAdjacentCellFrame(Game game, Cell cell) {
		super(game,
				"ChooseNonemptyAdjacentCell");
		this.cell = cell;
	}

	@Override
	public void execute() {
		CellCollection adjacent = Algorithmics.getAdjacentCells(cell, game.getBoard());
		CellCollection result = new CellCollection();
		for(Cell cell: adjacent){
			if(!cell.isEmpty()){
				result.add(cell);
			}
		}
		if(game.currentPlayer == game.players[1]) {
			Random rand = new Random();
			int choiceIndex = rand.nextInt(result.size());
			chosen = result.getAtArrayListIndex(choiceIndex);
		}
		//Log.add(result.toString());
		//int choiceIndex = GlobalScanner.getInt(result.size());
		//chosen = result.getAtArrayListIndex(choiceIndex);
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Cell getChoice(){
		this.launch();
		return chosen;
	}


}
