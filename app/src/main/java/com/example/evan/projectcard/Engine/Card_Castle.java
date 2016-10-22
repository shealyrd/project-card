package com.example.evan.projectcard.Engine;

public class Card_Castle extends ConstructionCard {

	public Card_Castle(Player owner) {
		super(
				"Castle",
				new KeywordCollection(),
				owner,
				"This cell can only be entered, exited, or attacked from this card's bottom side.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}
	
	@Override
	public void onPlay(){
		Cell thisCell = getCell();
		switch(getDirection()){
		case UP: 
			thisCell.blockSide(Direction.UP);
			thisCell.blockSide(Direction.LEFT);
			thisCell.blockSide(Direction.RIGHT);
			break;
		case DOWN:
			thisCell.blockSide(Direction.DOWN);
			thisCell.blockSide(Direction.LEFT);
			thisCell.blockSide(Direction.RIGHT);
			break;
		case RIGHT:
			thisCell.blockSide(Direction.DOWN);
			thisCell.blockSide(Direction.UP);
			thisCell.blockSide(Direction.RIGHT);
			break;
		case LEFT:
			thisCell.blockSide(Direction.DOWN);
			thisCell.blockSide(Direction.UP);
			thisCell.blockSide(Direction.LEFT);
			break;
		}
	}
	
	@Override
	public void onDestroy(){
		getCell().unblockAllSides();
	}

}
