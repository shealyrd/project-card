package com.example.evan.projectcard.Engine;

public class Card_RedKnight extends IdentityCard {

	public Card_RedKnight(Player owner) {
		super("Red Knight",
				new KeywordCollection("melee", "magical"),
				owner,
				"Red Knight can only use [spell] crafts if they have the [fire] keyword.",
				new ResourceCost(TestingSuite.TESTING_COST * 2, 0, 0, 0),
				10,
				5,
				2
				);
	}
	
	@Override
	public boolean canUseCraft(CraftCard craft){
		if(craft.canBeUsedBy(this)){
			if(craft.hasKeyword("spell") && !craft.hasKeyword("fire")){
				return false;
			}
			return true;
		}
		return false;
	}
}
