package com.example.evan.projectcard.Engine;

import android.content.Context;
import android.view.animation.Animation;

import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.R;

public class StartGameFrame extends Frame{

	public StartGameFrame(Game game) {
		super(game,
				"StartGame");
	}

	@Override
	public void execute() {
		game.log("Begin game");
		final Context context = game.getActivity();
		final GameActivity act = (GameActivity) context;
		Animation.AnimationListener listener = new Animation.AnimationListener(){
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
		};
		act.spinText(R.raw.game_start_text, listener);

	}

	@Override
	public Frame getNextFrame() {
		return new DecideFirstPlayerFrame(game);
	}

}
