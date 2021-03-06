package com.example.evan.projectcard.Engine;

import android.content.Context;
import android.view.animation.Animation;

import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.R;

public class StartFirstTurnFrame extends Frame {

	public StartFirstTurnFrame(Game game) {
		super(game,
				"StartFirstTurn");
	}

	@Override
	public void execute() {
		final Context context = game.getActivity();
		final GameActivity act = (GameActivity) context;
		int id = 0;
		if(game.currentPlayer == game.players[0]){
			id = R.raw.your_turn_text;
		}
		if(game.currentPlayer == game.players[1]){
			id = R.raw.com_turn_text;
		}
		act.growText(id, new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				game.startNextFrame();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
	}

	@Override
	public Frame getNextFrame() {
		return new DrawPhaseFrame(game);
	}

}
