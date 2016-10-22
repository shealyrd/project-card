package com.example.evan.projectcard.Engine;

public class Card_RedObelisk extends ConstructionCard {

	public Card_RedObelisk(Player owner) {
		super(
				"Red Obelisk",
				new KeywordCollection(),
				owner,
				"Add 1 Fire resource every turn.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}
	
	@Override
	public void onHarvestPhase(){
		owner.getResourcePool().addFire(1);
	}

}
