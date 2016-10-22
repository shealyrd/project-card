package com.example.evan.projectcard.Engine;

public class Card_BigassAnimeSword extends ItemCard {

	public Card_BigassAnimeSword(Player owner) {
		super(
				"Bigass Anime Sword",
				new KeywordCollection(),
				owner,
				"The unit's ATK is doubled, but after it attacks, its AP goes to 0. [melee] required.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}
	
	@Override
	public void onAttach(IdentityCard attachedTo){
		attachedTo.currentATK = attachedTo.currentATK * 2;
	}
	
	@Override
	public void onUnattach(IdentityCard attachedTo){
		if(!attachedTo.hasItem("Bigass Anime Sword")){
			attachedTo.currentATK = attachedTo.defaultATK;
		}
	}
	
	@Override
	public boolean canAttachTo(IdentityCard unit){
		if(unit.hasKeyword("melee")){
			return true;
		}
		else{
			return false;
		}
	}

}
