package com.example.evan.projectcard.Engine;

public class Card_Scout extends IdentityCard {
	
	public Card_Scout(Player owner) {
		super("Scout",
				new KeywordCollection(),
				owner,
				"Scout can not seize cells.",
				new ResourceCost(TestingSuite.TESTING_COST * 2, 0, 0, 0),
				5,
				5,
				5
				);
		canSeize = false;
	}
}
