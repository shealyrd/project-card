package com.example.evan.projectcard.Engine;

import android.widget.Toast;

import com.example.evan.projectcard.GameScreen.Listeners.CarrierListener;

public class PriestEffectActivateFrame extends Frame {
	Card_Priest priest;

	public PriestEffectActivateFrame(Game game, Card_Priest priest) {
		super(game,
				"PriestEffectActivate");
		this.priest = priest;
	}

	@Override
	public void execute() {
		if(game.currentPlayer == game.players[0]){

		}
		else{
			pickCell();
		}
	}

	public void pickCell(){
		ChooseCellFrame frame = new ChooseCellFrame(game);
		frame.setListener(new CarrierListener(){
			@Override
			public void onEvent() {
				Cell cell = (Cell) getObject();
				if(cell.hasUnit()){
					finishExecuting(cell.getIdentity());
				}
				else{
					Toast.makeText(game.getActivity(), "Pick an adjacent unit.", Toast.LENGTH_SHORT);
					pickCell();
				}
			}
		});
		frame.launch();
	}

	private void finishExecuting(IdentityCard identity) {
		IdentityCard healed = new ChooseAdjacentUnitFrame(game, priest.getCell()).getChoice();
		healed.restoreAllHP();
		priest.reduceAPBy(1);
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
