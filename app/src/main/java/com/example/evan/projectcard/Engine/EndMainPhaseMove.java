package com.example.evan.projectcard.Engine;

public class EndMainPhaseMove extends Move{

	public EndMainPhaseMove(){}
	public EndMainPhaseMove(Player owner) {
		super(owner, owner.getGame());
	}
	
	public String toString(){
		return "End main phase.";
	}

}
