package com.example.evan.projectcard.Engine;


import java.io.Serializable;

public class PersistentCard extends Card implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5979376588283641117L;
	Direction direction;

	public PersistentCard(){}

	public PersistentCard(String name, KeywordCollection keywords, Player owner, String text, ResourceCost cost) {
		super(name, keywords, owner, text, cost);
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection(){
		return direction;
	}

	public boolean canPlayIn(Cell cell) {
		if(cell.getOwner() == owner){
			return true;
		}
		return false;
	}

	public boolean isFacing(Cell cell) {
		if(cell == Algorithmics.getCellFacing(this)){
			return true;
		}
		return false;
	}
}
