package com.example.evan.projectcard.Engine;

public class Card_Thunderbolt extends CraftCard {
	
	public Card_Thunderbolt(Player owner) {
		super(
				"Thunderbolt",
				new KeywordCollection("spell"),
				owner,
				"Select unit. Inflict 10 damage to that unit.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 3));
	}

	@Override
	public Frame activate(Card activator){
		return new CardEffectFrame(
				this.getGame(),
				new ThunderboltEffectFrame(this.game));
	}
	
	@Override
	public boolean canBeUsedBy(IdentityCard card) {
		if(card.hasKeyword("magical")){
			return true;
		}
		else{
			return false;
		}
	}

}
