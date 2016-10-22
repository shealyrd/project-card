package com.example.evan.projectcard.Engine;
import java.util.ArrayList;

public class TestGame extends Game {
	ArrayList<Move> moves = new ArrayList<Move>();
	ArrayList<Integer> ints = new ArrayList<Integer>();
	int turnCount = 1;
	boolean turnFlag = false;
	
	public TestGame(){
		currentPlayer = players[0];
		waitingPlayer = players[1];
		currentPlayer.name = "p1";
		waitingPlayer.name = "p2";
	}
	
	public void start(){
		//registerPlayers();
		syncPlayersAndBoard();
		initializeDecks();
		players[0].deck.fillRandomly(20);
		players[1].deck.fillRandomly(20);
		startFrameLoop();
	}
	
	protected void startFrameLoop() {
		int counter = 1;
		currentFrame = new StartGameFrame(this);
		while(!turnFlag){
			currentFrame.launch();
			currentFrame = currentFrame.getNextFrame();
			if(currentFrame instanceof EndPhaseFrame){
				if(counter >= turnCount){
					turnFlag = true;
				}
				else{
					counter++;
				}

			}
		}
		
	}
	
	public void addMove(Move move){
		moves.add(move);
	}
	
	public void addIntResponse(int i){
		ints.add(i);
	}
	
	public int getNextInt(){
		//Log.supAdd("int queried");
		int result = ints.get(0);
		ints.remove(0);
		return result;
	}
	
	public Move getNextMove() {
		Move result = moves.get(0);
		moves.remove(0);
		return result;
	}
	
	public void setTurnCount(int i){
		turnCount = i;
	}
}
