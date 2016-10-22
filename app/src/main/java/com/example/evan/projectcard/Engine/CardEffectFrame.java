package com.example.evan.projectcard.Engine;

public class CardEffectFrame extends Frame {
	Frame frame;

	public CardEffectFrame(Game game, Frame frame) {
		super(game,
				"CardEffect");
		this.frame = frame;
	}

	@Override
	public void execute() {
		frame.launch();
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
