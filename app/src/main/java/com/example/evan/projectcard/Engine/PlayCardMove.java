package com.example.evan.projectcard.Engine;

public class PlayCardMove extends Move{
	public Player owner;
	public Card toPlay;
	public Cell toPlayOn;

	public PlayCardMove(){}
	
	public PlayCardMove(Player owner, Card toPlay, Cell toPlayOn){
		super(owner);
		this.owner = owner;
		this.toPlay = toPlay;
		this.toPlayOn = toPlayOn;
	}
	
	public Card getPlayedCard(){
		return toPlay;
	}
	
	public Cell getCellPlayedIn() {
		return toPlayOn;
	}

	public boolean isFromHand() {
		if(toPlay.getFieldStatus() == FieldStatus.Hand){
			return true;
		}
		return false;
	}

	/*
	public String toString(){
		return "Play " + toPlay.getName() + " " + direction.toString() + " in cell " + toPlayOn.getIndex();
	}
	*/
}
