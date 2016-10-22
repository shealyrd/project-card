package com.example.evan.projectcard.Engine;

public class Card_Merchant extends IdentityCard {
	
	public Card_Merchant(Player owner){
	super(
			"Merchant",
			new KeywordCollection(),
			owner,
			"OnPlay: Draw a card. You gain one extra resource per turn.",
			new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0),
			5,
			0,
			2);
		}

	@Override
	public void onPlay(){
		new DrawCardFrame(owner).launch();
	}
	
	@Override
	public void onHarvestPhase(){
		//add a resource
	}
}
