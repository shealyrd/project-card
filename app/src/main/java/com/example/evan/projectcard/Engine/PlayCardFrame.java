package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.Audio.Sounds;
import com.example.evan.projectcard.GameScreen.GameActivity;

public class PlayCardFrame extends Frame {
	PlayCardMove move;

	public PlayCardFrame(Game game, PlayCardMove move) {
		super(game,
				"PlayCard");
		this.move = move;
	}

	@Override
	public void execute() {
		android.util.Log.d("Bluetooth", "Inside play card");
		if(game.getActivity() != null){
			((GameActivity) game.getActivity()).playSound(Sounds.PLAY_CARD);
		}
		if(((PlayCardMove) move).isFromHand() && !((PlayCardMove) move).overNetwork){
			game.currentPlayer.getResourcePool().deductCost(move.toPlay.getCost());
			((PlayCardMove) move).getPlayedCard().removeFromHand();
		}
		if(move instanceof PlayUnitMove){
			new PlayUnitFrame(game, (PlayUnitMove) move).launch();
		}
		if(move instanceof PlayCraftMove){
			new PlayCraftFrame(game, (PlayCraftMove) move).launch();
		}
		if(move instanceof PlayConstructionMove){
			new PlayConstructionFrame(game, (PlayConstructionMove) move).launch();
		}
		if(move instanceof PlayItemMove){
			new PlayItemFrame(game, (PlayItemMove) move).launch();
		}
		if(!move.overNetwork){
			move.getPlayedCard().onPlay();
		}
		android.util.Log.d("Bluetooth", "play card exit");
	}

	@Override
	public Frame getNextFrame() {
		return null;
	}

}
