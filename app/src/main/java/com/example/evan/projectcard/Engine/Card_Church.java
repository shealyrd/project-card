package com.example.evan.projectcard.Engine;

public class Card_Church extends ConstructionCard {

	public Card_Church(Player owner) {
		super(
				"Church",
				new KeywordCollection(),
				owner,
				"This cell is unaffected by magic.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
	}
	
	@Override
	public void onPlay(){
		getCell().setImmuneToMagic();
	}
	
	@Override
	public void onDestroy(){
		if(!getCell().containsConstruction("Church")){
			getCell().resetImmuneToMagic();
		}

	}

}
