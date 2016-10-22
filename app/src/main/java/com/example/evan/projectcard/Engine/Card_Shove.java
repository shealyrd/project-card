package com.example.evan.projectcard.Engine;

public class Card_Shove extends CraftCard {

	public Card_Shove(Player owner) {
		super(
				"Shove",
				new KeywordCollection(),
				owner,
				"Activate only when you are facing a unit. Send that unit forward all the way in the direction that you're facing.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}

	@Override
	public Frame activate(Card activator) {
		return new CardEffectFrame(
				this.getGame(),
				new ShoveEffectFrame(this.game, (IdentityCard) activator));
	}
	
	@Override
	public boolean canBeUsedBy(IdentityCard card) {
		if(card.isFacingAUnit()){
			return true;
		}
		else{
			return false;
		}
	}

}
