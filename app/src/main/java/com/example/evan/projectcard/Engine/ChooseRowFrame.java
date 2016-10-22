package com.example.evan.projectcard.Engine;

public class ChooseRowFrame extends Frame {
	CellCollection row;
	
	public ChooseRowFrame(Game game) {
		super(
				game,
				"ChooseRow");
	}

	@Override
	public void execute() {
		int index = 0;
		if(game instanceof TestGame){
			index = ((TestGame) game).getNextInt();
		}
		else{
			Log.add("Please enter valid row.\n");
			index = GlobalScanner.getInt(6);
			while(!Algorithmics.isBetween(index, 1, 6)){
				Log.add("Please enter valid row.\n");
				index = GlobalScanner.getInt(6);
			}
		}
		row = game.getBoard().getRowAtIndex(index);
	}

	@Override
	public Frame getNextFrame() {
		return null;
	}
	
	public CellCollection getRow(){
		this.launch();
		return row;
	}
	
	

}
