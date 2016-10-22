package com.example.evan.projectcard.Engine;

public class PlayConstructionMove extends PlayCardMove {
	public Direction direction;

	public PlayConstructionMove(){};

	public PlayConstructionMove(Player owner, Card toPlay, Cell toPlayOn, Direction direction) {
		super(owner, toPlay, toPlayOn);
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public String toString(){
		return "Play " + toPlay.getName() + " " + direction.toString() + " in cell " + toPlayOn.getIndex();
	}
	
}
