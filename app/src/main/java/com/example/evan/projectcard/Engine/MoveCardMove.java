package com.example.evan.projectcard.Engine;

public class MoveCardMove extends Move {
	public IdentityCard toMove;
	public Cell toMoveTo;
	public Direction direction;

	public MoveCardMove(){}
	public MoveCardMove(Player owner, IdentityCard toMove, Cell toMoveTo, Direction direction){
		super(owner);
		this.toMove = toMove;
		this.toMoveTo = toMoveTo;
		this.direction = direction;
	}
	
	public IdentityCard getMovedCard(){
		return toMove;
	}
	
	public Cell getCellMovedIn() {
		return toMoveTo;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public String toString(){
		return "Move " + toMove.getName() + " to cell " + toMoveTo.getIndex() + " placed " + direction.toString();
	}
	
	
}
