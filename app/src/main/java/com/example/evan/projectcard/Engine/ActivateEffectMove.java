package com.example.evan.projectcard.Engine;

public class ActivateEffectMove extends Move {
	public Card activator;
	
	public ActivateEffectMove(Player owner, Card activator){
		super(owner);
		this.activator = activator;
	}
	
	public Card getActivator(){
		return activator;
	}
	
	public String toString(){
		return "Activate " + activator.getName() + "'s effect.";
	}
}
