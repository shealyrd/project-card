package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;

public class NecromancerEffectFrame extends Frame {
	Card_Necromancer necromancer;

	public NecromancerEffectFrame(Game game, Card_Necromancer necromancer) {
		super(game,
				"NecromancerEffect");
		this.necromancer = necromancer;
	}

	@Override
	public void execute() {
		if(game.currentPlayer == game.players[0]){

		}
		else{
			final ChooseCardInDiscardPileFrame frame = new ChooseCardInDiscardPileFrame(this.game, necromancer.getCell().getDiscardPile());
			frame.setListener(new BasicListener() {
				@Override
				public void onEvent() {
					finishExecuting(frame.getChosen());
				}
			});
			frame.launch();
		}

	}

	public void finishExecuting(Card chosen){
		chosen.getCell().removeFromDiscardPile(chosen);
		game.addCardToHand(chosen, necromancer.getOwner().getHand());
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
