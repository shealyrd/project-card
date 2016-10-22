package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.GameScreen.FrontEndDirection;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Network.NetworkGameActivity;
import com.example.evan.projectcard.Network.PerceptionFlipper;

public class MoveCardFrame extends Frame {
	MoveCardMove move;
	IdentityCard moved;
	Cell toMoveTo;
	Direction direction;

	public MoveCardFrame(Game game, MoveCardMove move) {
		super(game,
				"MoveCard");
		this.move = move;
		this.moved = move.getMovedCard();
		this.toMoveTo = move.getCellMovedIn();
		this.direction = move.getDirection();
	}

	@Override
	public void execute() {
		if(game.getActivity() instanceof NetworkGameActivity && move.overNetwork){
			CellView cell = toMoveTo.getView();
			CellView cellFrom = moved.getCell().getView();
			game.moveUnit(moved, toMoveTo, direction);
			cell.visibleCardId = CardEngineLookup.getIDFromCard(moved);
			cell.direction = PerceptionFlipper.flipDirection(direction).toFrontEndDirection();
			cellFrom.visibleCardId = 0;
			cellFrom.direction = FrontEndDirection.None;
			cellFrom.removeUnitImage();
			cell.drawVisibleCard();
		}
		else{
			game.moveUnit(moved, toMoveTo, direction);
		}
	}

	@Override
	public Frame getNextFrame() {
		return null;
	}

}
