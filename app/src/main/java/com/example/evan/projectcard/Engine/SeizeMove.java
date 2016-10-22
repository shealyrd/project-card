package com.example.evan.projectcard.Engine;

public class SeizeMove extends Move {
	public IdentityCard seizer;
	public Cell toSeize;

	public SeizeMove(){}
	public SeizeMove(Player owner, IdentityCard seizer, Cell toSeize) {
		super(owner);
		this.seizer = seizer;
		this.toSeize = toSeize;
	}

	public IdentityCard getSeizer() {
		return seizer;
	}

	public Cell getCellToSeize() {
		return toSeize;
	}
	
	public String toString(){
		return "Seize cell " + toSeize.getIndex() + " with " + seizer.getName();
	}

}
