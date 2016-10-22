package com.example.evan.projectcard.Engine;

public class Card_Assassin extends IdentityCard {
	
	public Card_Assassin(Player owner) {
		super(  "Assassin",
				new KeywordCollection("melee"),
				owner,
				"If Assassin attacks and survives, destroy the opponent.",
				new ResourceCost(TestingSuite.TESTING_COST * 2, 0, 0, 0),
				1,
				0,
				3
				);
	}
	
	@Override
	public void onBattleResolve(BattleFrame frame){
		if(this.currentHP > 0){
			new CardEffectFrame(
				this.game,
				new DestroyUnitFrame(game, frame.attacked)
				).launch();
		}
	}
}
