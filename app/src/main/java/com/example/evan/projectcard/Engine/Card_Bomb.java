package com.example.evan.projectcard.Engine;

public class Card_Bomb extends ItemCard {
	
	public Card_Bomb(Player owner) {
		super(
				"Bomb",
				new KeywordCollection(),
				owner,
				"Discard Bomb: destroy every card in adjacent cell.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0));
		setActivatable();
	}
	
	@Override
	public Frame activate(){
		return new CardEffectFrame(
				this.game,
				new BombEffectFrame(
						this.game,
						this));
	}
	
	@Override
	public boolean activationConditionsMet(){
		CellCollection adjacent = Algorithmics.getAdjacentCells(currentCell, getGame().getBoard());
		for(Cell cell: adjacent){
			if(!cell.isEmpty()){
					return true;
			}
		}
		return false;
	}

}
