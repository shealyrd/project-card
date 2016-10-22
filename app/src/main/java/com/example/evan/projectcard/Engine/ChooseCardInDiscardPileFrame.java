package com.example.evan.projectcard.Engine;

import android.view.View;

import com.example.evan.projectcard.GameScreen.Dialogs.ViewDiscardPileDialog;
import com.example.evan.projectcard.GameScreen.Listeners.CarrierListener;
import com.example.evan.projectcard.GameScreen.Views.CardView;

public class ChooseCardInDiscardPileFrame extends Frame {
	Card chosen;
	DiscardPile discardPile;

	public ChooseCardInDiscardPileFrame(Game game, DiscardPile discardPile) {
		super(game,
				"ChoseCardInDiscardPile");
		this.discardPile = discardPile;
	}

	@Override
	public void execute() {
		Log.add(discardPile.toString() + "\n");
		if(game instanceof TestGame){
			chosen = discardPile.getAtIndex(((TestGame) game).getNextInt());
		}
		else {
			ViewDiscardPileDialog dialog = new ViewDiscardPileDialog(game.getActivity());
			dialog.setCardClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					chosen = ((CardView) v).getCard();
					listener.onEvent();
				}
			});
		/*
		while(true){
				int chosenIndex = GlobalScanner.getInt(discardPile.size());
				if(Algorithmics.isBetween(chosenIndex, 1, discardPile.size()))
				{
					chosen = discardPile.getAtIndex(chosenIndex);
					break;
				}
				Log.add("Incorrect index. Please try again.");
			}
		}
		*/

		}}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

	public Card getChosen(){
		//this.launch();
		return chosen;
	}
}
