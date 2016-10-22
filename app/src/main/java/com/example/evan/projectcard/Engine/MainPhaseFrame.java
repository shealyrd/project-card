package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.Network.NetworkGameActivity;

public class MainPhaseFrame extends Frame {
	Frame frame;

	public MainPhaseFrame(Game game) {
		super(game,
				"MainPhase");
	}

	@Override
	public void execute() {
		if(game.getActivity() instanceof NetworkGameActivity){

		}
		else{
			game.startNextFrame();
		}

	}

	@Override
	public Frame getNextFrame() {
		if(game.getActivity() instanceof NetworkGameActivity){
			return new EndPhaseFrame(game);
		}
		else {
			return new ChooseActionFrame(game);
		}
	}
}
