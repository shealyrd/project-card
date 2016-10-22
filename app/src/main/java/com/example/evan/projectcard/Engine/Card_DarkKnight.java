package com.example.evan.projectcard.Engine;

public class Card_DarkKnight extends IdentityCard {
	
	public Card_DarkKnight(Player owner) {
		super(	"Dark Knight",
				new KeywordCollection("melee"),
				owner,
				"OnDestroy: Draw 2 cards.",
				new ResourceCost(TestingSuite.TESTING_COST * 2, 0, 0, 0),
				5,
				5,
				2
				);
	}
	
	@Override
	public void onDestroy(){
		new DrawCardFrame(owner, 2).launch();
	}
}
