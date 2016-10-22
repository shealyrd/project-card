package com.example.evan.projectcard.Engine;

public class Card_Fireball extends CraftCard {
	public Card_Fireball(Player owner) {
		super("Fireball",
				new KeywordCollection("spell", "fire"),
				owner,
				"Target cell. Destroy all items and constructions within. Inflict 5 damage to unit if present. Requires [magical]",
				new ResourceCost(TestingSuite.TESTING_COST, 3, 0, 0)
				);
	}
	
	@Override
	public Frame activate(Card activator){
		return new CardEffectFrame(
				this.getGame(),
				new FireballEffectFrame(this.game));
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
