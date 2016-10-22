package com.example.evan.projectcard.Engine;

public class ChooseDirectionFrame extends Frame {
	Direction choice;

	public ChooseDirectionFrame(Game game) {
		super(
				game,
				"ChooseDirection");
	}

	@Override
	public void execute() {
		if(game instanceof TestGame){
			switch(((TestGame) game).getNextInt()){
			case 1: choice = Direction.UP; break;
			case 2: choice = Direction.RIGHT; break;
			case 3: choice = Direction.DOWN; break;
			case 4: choice = Direction.LEFT; break;
			}
		}
		else{
			choice = GlobalScanner.nextDirection();
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Direction getChoice(){
		this.launch();
		return choice;
	}

}
