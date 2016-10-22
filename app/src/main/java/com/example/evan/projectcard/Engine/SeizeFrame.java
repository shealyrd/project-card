package com.example.evan.projectcard.Engine;

public class SeizeFrame extends Frame {
	SeizeMove move;

	public SeizeFrame(Game game, SeizeMove move) {
		super(game,
				"Seize");
		this.move = move;
	}

	@Override
	public void execute() {
		move.getSeizer().reduceAPBy(1);
		Cell cell = move.getCellToSeize();
		cell.setOwner(move.getOwner());
		cell.getView().changeAlignment();
		if(game.getBoard().allCellsSeized(move.getOwner())){
			Log.add(move.getOwner().getName() + " HAS SEIZED ALL CELLS");
			new WinGameFrame(game, move.getOwner()).launch();
		}
	}

	@Override
	public Frame getNextFrame() {
		
		return null;
	}

}
