package com.example.evan.projectcard.Engine;

public class Card_RingOfConstitution extends ItemCard {

	public Card_RingOfConstitution(Player owner) {
		super(
				"Ring of Constitution",
				new KeywordCollection(),
				owner,
				"This unit's max HP is doubled.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}
	
	@Override
	public void onAttach(IdentityCard card){
		card.setMaxHP(card.getMaxHP() * 2);
	}
	
	@Override
	public void onUnattach(IdentityCard card){
		card.setMaxHP(card.getMaxHP());
	}

}
