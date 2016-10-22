package com.example.evan.projectcard.Engine;

public class Card_YellowObelisk extends ConstructionCard {

	public Card_YellowObelisk(Player owner) {
		super(
				"Yellow Obelisk",
				new KeywordCollection(),
				owner,
				"Add 1 Thunder resource every turn.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}

	@Override
	public void onHarvestPhase(){
		owner.getResourcePool().addLightning(1);
	}
}
