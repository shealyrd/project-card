package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.GameActivity;

public class AttackFrame extends Frame {
	AttackMove move;
	IdentityCard attacker;
	IdentityCard attacked;

	public AttackFrame(Game game, AttackMove move) {
		super(game,
				"Attack");
		this.move = move;
		this.attacker = move.getAttacker();
		this.attacked = move.getAttacked();
	}

	@Override
	public void execute() {
		attacker.reduceAPBy(1);
		new BattleFrame(game, attacker, attacked).launch();
		//can counterattack logic
		//if the attacked survives AND (is facing the opponent OR has butterfly blade)
		if(attacker.isAlive() && ((attacked.isFacing(attacker) || attacked.hasItem("Butterfly Blade"))))
		{
			new CounterAttackFrame(game, move).launch();
		}
		if(game.getActivity() != null){
			((GameActivity) game.getActivity()).refreshCells();
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
