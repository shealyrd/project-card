package com.example.evan.projectcard.Engine;

public class Card_Wizard extends IdentityCard{
	

	public Card_Wizard(Player owner) {
		super("Wizard",
				new KeywordCollection("magical"),
				owner,
				"",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0),
				5,
				5,
				2
				);
	}
	
	
}
