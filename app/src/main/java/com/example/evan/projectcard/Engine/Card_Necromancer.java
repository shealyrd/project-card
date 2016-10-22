package com.example.evan.projectcard.Engine;

public class Card_Necromancer extends IdentityCard {
	
	public Card_Necromancer(Player owner) {
		super("Necromancer",
				new KeywordCollection("magical", "necromancy"),
				owner,
				"2 AP: Add a card in this cell's discard pile to your hand.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0),
				5,
				0,
				2
				);
		setActivatable();
	}
	
	@Override
	public Frame activate(){
		return new CardEffectFrame(
				this.game,
				new NecromancerEffectFrame(
						this.game,
						this));
	}
}
