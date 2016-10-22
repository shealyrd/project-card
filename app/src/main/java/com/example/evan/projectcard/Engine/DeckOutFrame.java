package com.example.evan.projectcard.Engine;

public class DeckOutFrame extends Frame {

	public DeckOutFrame(Game game) {
		super(game,
				"DeckOut");
	}

	@Override
	public void execute() {
		CellCollection cells1 = game.getBoard().getCellsOwnerBy(game.currentPlayer);
		CellCollection cells2 = game.getBoard().getCellsOwnerBy(game.waitingPlayer);
		if(cells1.size() == cells2.size()){
			new GameEndByDrawFrame(game).launch();
		}
		if(cells1.size() > cells2.size()){
			new WinGameFrame(game, game.currentPlayer).launch();
		}
		if(cells1.size() < cells2.size()){
			new WinGameFrame(game, game.waitingPlayer).launch();
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
