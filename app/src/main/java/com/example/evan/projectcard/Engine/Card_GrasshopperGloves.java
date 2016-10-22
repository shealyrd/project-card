package com.example.evan.projectcard.Engine;

public class Card_GrasshopperGloves extends ItemCard {

	public Card_GrasshopperGloves(Player owner) {
		super(
				"Grasshopper Gloves",
				new KeywordCollection(),
				owner,
				"This unit has [melee].",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}

	@Override
	public void onAttach(IdentityCard attachedTo){
		attachedTo.addKeyword("melee");
	}
	
	@Override
	public void onUnattach(IdentityCard attachedTo){
		attachedTo.removeKeyword("melee");
	}
}
