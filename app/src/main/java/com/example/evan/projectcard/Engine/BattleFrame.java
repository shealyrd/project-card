package com.example.evan.projectcard.Engine;

public class BattleFrame extends Frame {
	IdentityCard attacker;
	IdentityCard attacked;
	
	public BattleFrame(Game game, Card attacker, Card attacked) {
		super(game,
				"BattleFrame");
		this.attacker = (IdentityCard) attacker;
		this.attacked = (IdentityCard) attacked;
	}
	
	@Override
	public void execute() {
		game.battle(attacker, attacked);
		attacker.onBattleResolve(this);
		attacked.onBattleResolve(this);
	}
	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
