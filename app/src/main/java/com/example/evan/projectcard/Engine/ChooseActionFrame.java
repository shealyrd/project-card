package com.example.evan.projectcard.Engine;
import android.os.Handler;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.GameActivity;

import java.util.Random;

public class ChooseActionFrame extends Frame{
	boolean endMainPhase;
	Frame nextFrame;
	Move move;

	public ChooseActionFrame(Game game) {
		super(game,
				"ChooseActionFrame");
	}

	@Override
	public void execute() {
		Move move;
		if(game instanceof TestGame){
			move = ((TestGame) game).getNextMove();
		}
		//if(game instanceof ComVsComGame){

			final Handler handler = new Handler();
			final ChooseActionFrame frame = this;
			final Game game = this.game;
			final BasicListener listener = new BasicListener() {
				@Override
				public void onEvent() {
					frame.chooseAction(frame.move);
				}
			};
			new Thread() {
				@Override
				public void run() {
					android.util.Log.d("ehh", "I'm in!");
					Move move;
					android.util.Log.d("ehh", "Generating moves");
					PossibleMoveCollection moves = MoveGenerator.generatePossibleMoves(game.board, game.currentPlayer);
					android.util.Log.d("ehh", moves.toString());
					if(moves.containsSeizeMove()){
						android.util.Log.d("ehh", "Does contain seize move");
						move = moves.getFirstSeizeMove();
						frame.move = move;
						handler.post(new Runnable() {
							@Override
							public void run() {
								listener.onEvent();
							}
						});
					}
					else if(game.currentPlayer == game.players[1]){
						android.util.Log.d("ehh", "Movetree thinking...");
						MoveTree tree = new MoveTree(moves, game.board, 3);
						move = tree.getBestMove();
						frame.move = move;
						handler.post(new Runnable() {
							@Override
							public void run() {
								listener.onEvent();
							}
						});
					}
					else{
						//android.util.Log.d("ehh", "Does contain seize move");
						Random rn = new Random();
						int i = rn.nextInt(moves.size()) + 1;
						//Log.supAdd(i);
						move = moves.get(i);

					}
				}
			}.start();

			//game.log(move.toString());
		//}
		/*else{
			Log.add(game.board.toString() + "\n");
			Log.add(game.currentPlayer.getName() +", please make a move.");
			PossibleMoveCollection moves = MoveGenerator.generatePossibleMoves(game.board, game.currentPlayer);
			Log.add(moves.toString());
			move = moves.get(GlobalScanner.getInt());
		}*/

	}

	public void chooseAction(Move move){
		if(move instanceof PlayCardMove){
			new PlayCardFrame(game, (PlayCardMove) move).launch();
		}
		if(move instanceof RotateUnitMove){
			new RotateUnitFrame(game, (RotateUnitMove) move).launch();
		}
		if(move instanceof MoveCardMove){
			new MoveCardFrame(game, (MoveCardMove) move).launch();
		}
		if(move instanceof AttackMove){
			new AttackFrame(game, (AttackMove) move).launch();
		}
		if(move instanceof ActivateEffectMove){
			new ActivateEffectFrame(game, (ActivateEffectMove) move).launch();
		}
		if(move instanceof SeizeMove){
			new SeizeFrame(game, (SeizeMove) move).launch();
		}
		if(move instanceof EndMainPhaseMove){
			//new EndMainPhaseFrame(game).launch();
			endMainPhase = true;
		}
		if(game.getActivity() != null){
			((GameActivity) game.getActivity()).refreshCells();
			Handler handler = new Handler();
			final boolean end = endMainPhase;
			final ChooseActionFrame frame = this;
			final Game g = this.game;
			handler.postDelayed(new Runnable(){
				@Override
				public void run() {
					if(!end){
						frame.setNextFrame(new ChooseActionFrame(game));
						g.startNextFrame();
					}
					else{
						frame.setNextFrame(new EndMainPhaseFrame(game));
						g.startNextFrame();
					}
				}
			}, 1000);
		}
	}

	public void setNextFrame(Frame f){
		nextFrame = f;
	}

	@Override
	public Frame getNextFrame() {
		if(game.getActivity() == null){
			if(!endMainPhase){
				nextFrame = new ChooseActionFrame(game);
			}
			if(endMainPhase){
				nextFrame = new EndMainPhaseFrame(game);
			}
		}
		android.util.Log.d("asuo", nextFrame.frameName);
		return nextFrame;
	}

	
}
