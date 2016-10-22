package com.example.evan.projectcard.Engine;

import android.graphics.drawable.Drawable;

import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.FrontEndDirection;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Network.NetworkGameActivity;

public class RotateUnitFrame extends Frame {
	RotateUnitMove move;
	IdentityCard toRotate;
	Direction direction;

	public RotateUnitFrame(Game game, RotateUnitMove move) {
		super(game,
				"RotateUnit");
		this.move = move;
		this.toRotate = move.getRotatedCard();
		this.direction = move.getDirection();
	}

	@Override
	public void execute() {
		toRotate.reduceAPBy(1);
		game.rotateUnit(toRotate, direction);
		if(game.getActivity() instanceof NetworkGameActivity && move.overNetwork){
			Drawable draw = game.getActivity().getResources().getDrawable(CardDatabase.getDrawableID(CardEngineLookup.getIDFromCard(toRotate)));
			if(toRotate.currentCell == null){
				android.util.Log.d("RotateFrame", "Cell null");
			}
			CellView view = toRotate.currentCell.getView();
			FrontEndDirection dir = direction.toFrontEndDirection();
			if(view == null){
				android.util.Log.d("RotateFrame", "view null");
			}
			view.drawVisibleCard(draw, dir);
		}
	}

	@Override
	public Frame getNextFrame() {
		return null;
	}

}
