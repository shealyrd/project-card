package com.example.evan.projectcard.Engine;

public class ChooseAdjacentCellFrame extends Frame {
	Cell cell;
	Cell chosen;

	public ChooseAdjacentCellFrame(Game game, Cell cell) {
		super(game,
				"ChooseAdjacentCell");
		this.cell = cell;
	}

	@Override
	public void execute() {
		CellCollection adjacent = Algorithmics.getAdjacentCells(cell, game.getBoard());
		int choiceIndex = 0;
		if(game instanceof TestGame){
			choiceIndex = ((TestGame) game).getNextInt();
		}

		else{
			Log.add(adjacent.toString());
			choiceIndex = GlobalScanner.getInt(18);
		}
		chosen = game.board.getCellAtIndex(choiceIndex);
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
