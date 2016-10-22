package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.GameActivity;

public class ThunderboltEffectFrame extends Frame {
	int cellToDestroy = -1;
	
	public ThunderboltEffectFrame(Game game) {
		super(game,
				"ThunderboltEffect");
	}
	
	public ThunderboltEffectFrame(Game game, int index) {
		super(game,
				"FireballEffect");
		cellToDestroy = index;
	}

	@Override
	public void execute() {
		
		Cell target;
		//if(game instanceof ComVsComGame){
		if(game.currentPlayer == game.players[1]){
			if(cellToDestroy != -1){
				target = game.board.getCellAtIndex(cellToDestroy);
			}
			else{
				ChooseThunderboltCellTree tree = new ChooseThunderboltCellTree(game.board);
				int index = tree.getBestIndex();
				target = game.board.getCellAtIndex(index);
			}
		}
		else{
			final ChooseCellFrame frame = new ChooseCellFrame(this.game);
			frame.setListener(new BasicListener() {
				@Override
				public void onEvent() {
					finishExecuting(frame.getCell());
				}
			});
			frame.launch();
		}
		//target = new ChooseCellFrame(this.game).getCell();

	}

	public void finishExecuting(Cell target){
		if(!target.isImmuneToMagic()){
			if(target.hasUnit()){
				IdentityCard victim = target.getIdentity();
				victim.inflictDamage(10);
			}
		}
		((GameActivity) game.getActivity()).deactivateCells();
		((GameActivity) game.getActivity()).activateCells();
		((GameActivity) game.getActivity()).refreshCells();
	}
	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
