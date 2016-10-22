package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class BoardMovePair implements Serializable {
	private static final long serialVersionUID = -1655825919843055137L;
	Board board;
	Move move;
	
	public BoardMovePair(Board board, Move move) {
		this.board = board;
		this.move = move;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move = move;
	}
}
