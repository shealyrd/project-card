package com.example.evan.projectcard.Engine;

public class Card_GroveOfRite extends ConstructionCard {

	public Card_GroveOfRite(Player owner) {
		super(
				"Grove of Rite",
				new KeywordCollection(),
				owner,
				"If there is a card with [necromancy] in this cell, it gets the following card effect: \"2 AP: Play an identity card in this cell's discard pile in an adjacent cell with no cost.\"",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0)
				);
			setActivatable();
		}
	
	@Override
	public boolean activationConditionsMet(){
		if(getCell().hasUnit()){
			if(getCell().getDiscardPile().getUnits().size() > 0){
				if(getCell().hasOpenAdjacentCell()){
					if(getCell().getIdentity().hasKeyword("necromancy")){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public Frame activate(){
		return new CardEffectFrame(
				this.getGame(),
				new GroveOfRiteEffectFrame(this.game, this));
	}
	
}
