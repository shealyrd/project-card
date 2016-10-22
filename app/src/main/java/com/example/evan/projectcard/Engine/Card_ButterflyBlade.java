package com.example.evan.projectcard.Engine;

public class Card_ButterflyBlade extends ItemCard {

	public Card_ButterflyBlade(Player owner) {
		super(
				"Butterfly Blade",
				new KeywordCollection(),
				owner,
				"This unit can counterattack from any side. [melee] required.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
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
	
	/*
	@Override
	public void onAttach(IdentityCard attachedTo){
		attachedTo.addCounterAttackRight(CounterAttackRight.ALL);
	}
	
	@Override
	public void onUnattach(IdentityCard attachedTo){
		attachedTo.removeCounterAttackRight(CounterAttackRight.ALL);
	}
	*/

}
