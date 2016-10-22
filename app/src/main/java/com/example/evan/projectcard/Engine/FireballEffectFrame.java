package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.GameActivity;

public class FireballEffectFrame extends Frame {
	int cellToDestroy = -1;

	public FireballEffectFrame(Game game) {
		super(game,
				"FireballEffect");
	}

	public FireballEffectFrame(Game game, int index) {
		super(game,
				"FireballEffect");
		cellToDestroy = index;
	}

	@Override
	public void execute() {
		
		Cell target;
		if(game.currentPlayer == game.players[1]) {
			if (cellToDestroy != -1) {
				target = game.board.getCellAtIndex(cellToDestroy);
			} else {
				ChooseFireballCellTree tree = new ChooseFireballCellTree(game.board);
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
			target = new ChooseCellFrame(this.game).getCell();
		}

	}

	private void finishExecuting(Cell target) {
		if(!target.isImmuneToMagic()){
			if(target.hasUnit()){
				IdentityCard victim = target.getIdentity();
				victim.inflictDamage(5);
				if(victim.isAlive()){
					target.getIdentity().destroyAllItems();
				}
				target.destroyAllConstructions();
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
