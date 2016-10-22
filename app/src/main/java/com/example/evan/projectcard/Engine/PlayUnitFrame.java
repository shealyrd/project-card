package com.example.evan.projectcard.Engine;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Network.NetworkGameActivity;

public class PlayUnitFrame extends Frame {
	PlayUnitMove move;
	Cell playedIn;
	Direction direction;
	IdentityCard playedCard;

	public PlayUnitFrame(Game game, PlayUnitMove move) {
		super(game,
				"PlayUnit");
		this.move = move;
		this.playedCard = (IdentityCard) move.getPlayedCard();
		this.direction = move.getDirection();
		this.playedIn = move.getCellPlayedIn();
	}

	
	@Override
	public void execute() {
		//android.util.Log.d("Bluetooth", "Inside PlayUnitFrame");
		if(playedCard.getFieldStatus() == FieldStatus.Hand){
			playedCard.removeFromHand();
		}
		game.playUnit(playedCard, playedIn, direction);
		if(game.getActivity() instanceof NetworkGameActivity && move.overNetwork){
			Drawable img = game.getActivity().getResources().getDrawable(CardDatabase.getDrawableID(CardEngineLookup.getIDFromCard(playedCard)));
			CellView cell = playedIn.getView();
			if(cell == null){
				//android.util.Log.d("UnitFrame", "Cell is null");
			}
			cell.drawVisibleCard(img, direction.toFrontEndDirection());
			cell.visibleCardId = CardDatabase.getDrawableID(CardEngineLookup.getIDFromCard(playedCard));
			cell.direction = direction.toFrontEndDirection();
		}
		else{
			Frame onPlay = playedCard.onPlay(this);
			if(onPlay != null){
				onPlay.launch();
			}
		}

		//android.util.Log.d("Bluetooth", "Exiting PlayUnitFrame");
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
