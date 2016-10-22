package com.example.evan.projectcard.Engine;

public class PlayItemFrame extends Frame{
	PlayItemMove move;
	ItemCard toPlay;
	IdentityCard attachedTo;

	public PlayItemFrame(Game game, PlayItemMove move) {
		super(game,
				"PlayItem");
		this.move = move;
		this.toPlay = (ItemCard) move.getCardPlayed();
		this.attachedTo = move.getCardToAttachTo();
	}

	@Override
	public void execute() {
		attachedTo.addItem(toPlay);
		toPlay.setAttachedTo(attachedTo);
		toPlay.setCell(attachedTo.getCell());
		toPlay.setFieldStatus(FieldStatus.Field);
		toPlay.onAttach(attachedTo);
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
