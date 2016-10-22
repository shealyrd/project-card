package com.example.evan.projectcard.Engine;
import android.view.View;
import android.widget.ImageView;

import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.OpeningSpinAnimationFactory;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.AnonymousContainer;

import java.io.Serializable;

public class DrawPhaseFrame extends Frame  implements Serializable {

	private static final long serialVersionUID = 3045843070259268442L;

	public DrawPhaseFrame(Game game) {
		super(game,
				"DrawPhase");

	}

	@Override
	public void execute() {
		if(game.currentPlayer == game.players[0]){
			final AnonymousContainer i = new AnonymousContainer();
			i.setValue(new Integer(1));
			final Game gameFinal = this.game;
			final ImageView draw_alert = (ImageView) game.getActivity().findViewById(R.id.draw_alert);
			draw_alert.setVisibility(View.VISIBLE);
			draw_alert.startAnimation(OpeningSpinAnimationFactory.createAlphaCycle());
			final ImageView deck = (ImageView) game.getActivity().findViewById(R.id.deck);
			View.OnClickListener deckClick = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					DrawCardFrame drawFrame = new DrawCardFrame(gameFinal, gameFinal.currentPlayer);
					drawFrame.setListener(new BasicListener() {
						@Override
						public void onEvent() {
							game.startNextFrame();
						}
					});
					drawFrame.launch();
					//gameFinal.draw(gameFinal.players[0], 1, listener);
					i.setValue(new Integer((Integer) i.getValue() - 1));
					if((Integer) i.getValue() <= 0){
						draw_alert.clearAnimation();
						draw_alert.setVisibility(View.GONE);
						deck.setOnClickListener(null);
					}
				}
			};
			deck.setOnClickListener(deckClick);
		}
		else{
			new DrawCardFrame(game, game.currentPlayer).launch();
			game.startNextFrame();
		}
	}

	@Override
	public Frame getNextFrame() {
		if(game.currentPlayer == game.players[0]){
			return new PlayerMainPhaseStartFrame(game);
		}
		else
		{

			return new MainPhaseFrame(game);
		}

	}

}
