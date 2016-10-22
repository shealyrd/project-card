package com.example.evan.projectcard.Engine;

import android.graphics.drawable.Drawable;

import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Network.NetworkGameActivity;

public class PlayConstructionFrame extends Frame{
	PlayConstructionMove move;
	ConstructionCard toPlay;
	Cell toPlayOn;
	Direction direction;

	public PlayConstructionFrame(Game game, PlayConstructionMove move) {
		super(game,
				"PlayConstruction");
		this.move = move;
		this.toPlay = (ConstructionCard) move.getPlayedCard();
		this.toPlayOn = move.getCellPlayedIn();
		this.direction = move.getDirection();
	}

	@Override
	public void execute() {
		game.playConstruction(toPlay, toPlayOn, direction);
		if(game.getActivity() instanceof NetworkGameActivity && move.overNetwork){
			Drawable img = game.getActivity().getResources().getDrawable(CardDatabase.getDrawableID(CardEngineLookup.getIDFromCard(move.getPlayedCard())));
			CellView cell = move.toPlayOn.getView();
			if(cell == null){
				android.util.Log.d("ConstructionFrame", "Cell is null");
			}
			cell.drawVisibleCard(img, direction.toFrontEndDirection());
			cell.visibleCardId = CardDatabase.getDrawableID(CardEngineLookup.getIDFromCard(move.getPlayedCard()));
			cell.direction = direction.toFrontEndDirection();
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
