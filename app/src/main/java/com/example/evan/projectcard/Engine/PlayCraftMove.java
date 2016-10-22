package com.example.evan.projectcard.Engine;

public class PlayCraftMove extends PlayCardMove {
	IdentityCard caster;
	CraftCard craft;

	public PlayCraftMove(Player owner, Card toPlay, IdentityCard caster) {
		super(owner, toPlay, toPlay.getCell());
		this.craft = (CraftCard) toPlay;
		this.caster = caster;
	}
	
	public IdentityCard getCaster(){
		return caster;
	}

	public CraftCard getCraft() {
		return craft;
	}
	
	public String toString(){
		return "Cast " + craft.getName() + " with " + caster.getName();
	}
	
	
}
