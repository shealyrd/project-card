package com.example.evan.projectcard.Engine;

public class ChooseUnitInDiscardPileFrame extends Frame {
	Card chosen;
	DiscardPile discardPile;
	
	public ChooseUnitInDiscardPileFrame(Game game,  DiscardPile discardPile) {
		super(game,
				"ChooseUnitInDiscardPile");
		this.discardPile = discardPile;
	}

	@Override
	public void execute() {
		CardCollection units = discardPile.getUnits();
		Log.add(units.toString() + "\n");
		int chosenIndex = 0;
		while(true){
			if(game instanceof TestGame){
				chosenIndex = ((TestGame) game).getNextInt();
			}
			else{
				chosenIndex = GlobalScanner.getInt(units.size());
			}
			if(Algorithmics.isBetween(chosenIndex, 0, units.size()))
			{
				chosen = units.getAtIndex(chosenIndex);
				break;
			}
			Log.add("Incorrect index. Please try again.");
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Card getChoice(){
		this.launch();
		return chosen;
	}

}
