package com.example.evan.projectcard.Engine;

public class PlayCraftFrame extends Frame{
	PlayCraftMove move;
	IdentityCard caster;
	CraftCard craft;

	public PlayCraftFrame(Game game, PlayCraftMove move) {
		super(game,
				"PlayCraft");
		this.move = move;
		this.craft = (CraftCard) move.getPlayedCard();
		this.caster = move.getCaster();
	}

	@Override
	public void execute() {
		//game.playCraft(craft, caster);
		/*if(caster.canUseCraft(craft)){
			craft.activate();
		}*/
		craft.activate(caster).launch();
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
