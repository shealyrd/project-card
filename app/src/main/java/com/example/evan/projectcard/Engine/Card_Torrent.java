package com.example.evan.projectcard.Engine;

public class Card_Torrent extends CraftCard {

	public Card_Torrent(Player owner) {
		super(
				"Torrent",
				new KeywordCollection(),
				owner,
				"Pick a row. All constructions and items on that row are destroyed.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 3, 0));

	}

	@Override
	public Frame activate(Card activator){
		return new CardEffectFrame(
				this.getGame(),
				new TorrentEffectFrame(this.game));
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
