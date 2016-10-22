package com.example.evan.projectcard.Engine;

public class Card_BlueObelisk extends ConstructionCard {

	public Card_BlueObelisk(Player owner) {
		super(
				"Blue Obelisk",
				new KeywordCollection(),
				owner,
				"Add 1 Water resource every turn.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}
	
	@Override
	public void onHarvestPhase(){
		if(game.currentPlayer == owner){
			owner.getResourcePool().addWater(1);
		}
	}

}
