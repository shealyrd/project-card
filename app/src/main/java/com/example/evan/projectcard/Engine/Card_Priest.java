package com.example.evan.projectcard.Engine;

public class Card_Priest extends IdentityCard {
	
	public Card_Priest(Player owner) {
		super(  "Priest",
				new KeywordCollection("holy"),
				owner,
				"1 AP: Restore the HP of an adjacent unit.",
				new ResourceCost(TestingSuite.TESTING_COST, 0, 0, 0),
				5,
				0,
				2);
			setActivatable();
		}
	
	@Override
	public Frame activate(){
		return new PriestEffectActivateFrame(game, this);
	}
	
	@Override
	public boolean activationConditionsMet(){
		if(canPayAPCost(1)){
			CellCollection adjacent = Algorithmics.getAdjacentCells(currentCell, getGame().getBoard());
			for(Cell cell: adjacent){
				if(cell.hasUnit()){
					if(cell.getIdentity().getOwner() == getOwner()){
						return true;
					}
				}
			}
		}
		return false;
	}
				
}
