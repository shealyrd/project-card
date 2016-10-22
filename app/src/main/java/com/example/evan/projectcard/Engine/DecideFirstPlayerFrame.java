package com.example.evan.projectcard.Engine;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.GameScreen.Dialogs.CoinFlipDialog;
import com.example.evan.projectcard.GameScreen.Dialogs.CoinFlipListener;
import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CoinFlipView;
import com.example.evan.projectcard.Network.NetworkGameActivity;
import com.example.evan.projectcard.R;

import java.util.Random;

public class DecideFirstPlayerFrame extends Frame {

	public DecideFirstPlayerFrame(Game game) {
		super(game,
				"DecideFirstPlayer");
	}

	@Override
	public void execute() {
		final Context context = game.getActivity();
		if (game.getActivity() instanceof NetworkGameActivity) {
			NetworkGameActivity bluetoothGame = (NetworkGameActivity) game.getActivity();
			if(bluetoothGame.isFirstPlayer()){
				game.currentPlayer = game.players[0];
				game.waitingPlayer = game.players[1];
			}
			else{
				game.currentPlayer = game.players[1];
				game.waitingPlayer = game.players[0];
				//bluetoothGame.tagCellsInverse();
			}
			bluetoothGame.getGame().startNextFrame();
			ImageView testView = (ImageView) game.getActivity().findViewById(R.id.test_spin);
			testView.setVisibility(View.GONE);
		} else {
			final GameActivity act = (GameActivity) game.getActivity();
			CoinFlipListener listener = new CoinFlipListener() {
				@Override
				public void flipEvent(boolean firstTurn) {
					if (firstTurn) {
						game.currentPlayer = game.players[0];
						game.waitingPlayer = game.players[1];
					} else {
						game.currentPlayer = game.players[1];
						game.waitingPlayer = game.players[0];
					}
					game.log(game.currentPlayer.name + " goes first!");
				}
			};
			final CoinFlipDialog dialog = new CoinFlipDialog(context);
			BasicListener dismissListener = new BasicListener() {
				@Override
				public void onEvent() {
					act.game.startNextFrame();
				}
			};
			dialog.setOnDismissListener(dismissListener);
			dialog.setCoinFlipListener(listener);
			dialog.initialize();
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					dialog.show();
					CoinFlipView coinFlip = (CoinFlipView) dialog.findViewById(R.id.coin_flip);
					coinFlip.setX(coinFlip.getX() + 15);
					ImageView testView = (ImageView) act.findViewById(R.id.test_spin);
					testView.setVisibility(View.GONE);
				}
			}, 1000);
		}
	}

	@Override
	public Frame getNextFrame() {
		return new DrawInitialHandsFrame(game);
	}

}
