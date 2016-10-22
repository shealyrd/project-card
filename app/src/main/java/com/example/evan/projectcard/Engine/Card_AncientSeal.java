package com.example.evan.projectcard.Engine;

public class Card_AncientSeal extends ItemCard {

	public Card_AncientSeal(Player owner) {
		super(
				"Ancient Seal",
				new KeywordCollection(),
				owner,
				"This unit has [magical].",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}

	@Override
	public void onAttach(IdentityCard attachedTo){
		attachedTo.addKeyword("magical");
	}
	
	@Override
	public void onUnattach(IdentityCard attachedTo){
		attachedTo.removeKeyword("magical");
	}
}
