package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.GameActivity;

public class TorrentEffectFrame extends Frame {
	int rowToDestroy = -1;
	
	public TorrentEffectFrame(Game game) {
		super(
				game,
				"TorrentEffect");
	}

	public TorrentEffectFrame(Game game, int index) {
		super(
				game,
				"TorrentEffect");
		rowToDestroy = index;
	}

	@Override
	public void execute() {
		CellCollection row;
		if(game.currentPlayer == game.players[1]) {
			if(rowToDestroy != -1){
				row = game.board.getRowAtIndex(rowToDestroy);
			}
			else{
				ChooseTorrentRowTree tree = new ChooseTorrentRowTree(game.board);
				int index = tree.getBestIndex();
				row = game.board.getRowAtIndex(index);
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

		}

	private void finishExecuting(Cell target) {
		CellCollection row = game.board.getRowAtIndex(Algorithmics.getRow(target.getIndex()));
		for(Cell cell: row){
			cell.destroyAllConstructions();
			if(cell.hasUnit()){
				cell.getIdentity().destroyAllItems();
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
