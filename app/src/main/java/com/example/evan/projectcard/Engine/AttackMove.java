package com.example.evan.projectcard.Engine;

public class AttackMove extends Move {
	public IdentityCard attacker;
	public IdentityCard attacked;
	
	public AttackMove(Player owner, IdentityCard attacker, IdentityCard attacked){
		super(owner);
		this.attacker = attacker;
		this.attacked = attacked;
	}

	public AttackMove() {

	}

	public IdentityCard getAttacker() {
		return attacker;
	}
	
	public IdentityCard getAttacked() {
		return attacked;
	}

	public String toString(){
		return "Attack " + attacked.getName() + " with " + attacker.getName();
	}
}
