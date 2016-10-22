package com.example.evan.projectcard.Engine;

public class DestroyItemFrame extends Frame {
	ItemCard destroyed;

	public DestroyItemFrame(Game game, ItemCard destroyed) {
		super(game,
				"DestroyItem");
		this.destroyed = destroyed;
	}

	@Override
	public void execute() {
		destroyed.getAttachedTo().removeItem(destroyed);
		destroyed.removeAttachment();
		destroyed.getCell().addToDiscardPile(destroyed);
		destroyed.setFieldStatus(FieldStatus.DiscardPile);
		destroyed.onDestroy();
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
