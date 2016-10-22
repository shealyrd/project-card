package com.example.evan.projectcard.Engine;

public class CounterAttackFrame extends Frame {
	AttackMove move;
	IdentityCard counterAttacker;
	IdentityCard counterAttacked;

	public CounterAttackFrame(Game game, AttackMove move) {
		super(game,
				"CounterAttack");
		this.counterAttacker = move.getAttacked();
		this.counterAttacked = move.getAttacker();
	}

	@Override
	public void execute() {
		game.battle(counterAttacker, counterAttacked);
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
