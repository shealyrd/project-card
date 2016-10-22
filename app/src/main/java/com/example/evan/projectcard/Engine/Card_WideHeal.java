package com.example.evan.projectcard.Engine;

public class Card_WideHeal extends CraftCard {

	public Card_WideHeal(Player owner) {
		super(
				"Wide Heal",
				new KeywordCollection(),
				owner,
				"Must have [holy]. Heal all of your units completely.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}

	@Override
	public Frame activate(Card activator){
		return new CardEffectFrame(
				this.getGame(),
				new WideHealEffectFrame(this.game, (IdentityCard) activator));
	}
	
	@Override
	public boolean canBeUsedBy(IdentityCard card) {
		if(card.hasKeyword("holy")){
			return true;
		}
		else{
			return false;
		}
	}

}
