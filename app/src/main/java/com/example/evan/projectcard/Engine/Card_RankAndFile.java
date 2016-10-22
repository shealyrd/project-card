package com.example.evan.projectcard.Engine;

public class Card_RankAndFile extends IdentityCard{

	public Card_RankAndFile(Player owner) {
		super(
				"Rank and File",
				new KeywordCollection("melee"),
				owner,
				"",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0),
				5,
				5,
				2);
	}

}
