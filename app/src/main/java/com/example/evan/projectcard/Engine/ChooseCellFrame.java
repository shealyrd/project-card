package com.example.evan.projectcard.Engine;

import android.widget.Toast;

import com.example.evan.projectcard.GameScreen.Listeners.CarrierListener;
import com.example.evan.projectcard.GameScreen.GameActivity;

public class ChooseCellFrame extends Frame {
	Cell chosenCell;

	public ChooseCellFrame(Game game) {
		super(game,
				"ChooseCell");
	}

	@Override
	public void execute() {
		int chosenIndex = 0;
		if(game instanceof TestGame){
			chosenIndex = ((TestGame) game).getNextInt();
			chosenCell = game.getBoard().getCellAtIndex(chosenIndex);
		}
		if(game instanceof ComVsComGame){
			Log.add("Choosing cell...");
			chosenIndex = GlobalScanner.getInt(17) + 1;
			Log.add(chosenIndex + " chosen.");
			chosenCell = game.getBoard().getCellAtIndex(chosenIndex);
		}
		else {
			Toast.makeText(game.getActivity(), "Choose a cell.", Toast.LENGTH_LONG);
			CarrierListener cListener = new CarrierListener() {
				@Override
				public void onEvent() {
					chosenCell = (Cell) getObject();
					listener.onEvent();
				}
			};
			((GameActivity) game.getActivity()).activateCellChoice(cListener);
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Cell getCell(){
		//this.launch();
		return chosenCell;

	}

}
