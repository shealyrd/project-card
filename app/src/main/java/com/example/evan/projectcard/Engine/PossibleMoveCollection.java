package com.example.evan.projectcard.Engine;
import java.util.ArrayList;
import java.util.Iterator;

public class PossibleMoveCollection implements Iterable<Move>{
	ArrayList<Move> moves = new ArrayList<Move>();
	
	public void append(PossibleMoveCollection moves) {
		this.moves.addAll(moves.getArrayList());
	}
	
	public int size(){
		return moves.size();
	}
	
	public ArrayList<Move> getArrayList(){
		return moves;
	}
	
	public void append(Move move){
		moves.add(move);
	}
	
	public Move get(int i){
		return moves.get(i - 1);
	}
	
	
	public void appendMoveCardMoves(Player owner, IdentityCard unit) {
		CellCollection adjacentCells = Algorithmics.getAdjacentCells(unit.getCell(), unit.getGame().getBoard());
		for(Cell cell: adjacentCells){
			if(!cell.hasUnit()){
				moves.add(new MoveCardMove(owner, unit, cell, Direction.UP));
				moves.add(new MoveCardMove(owner, unit, cell, Direction.RIGHT));
				moves.add(new MoveCardMove(owner, unit, cell, Direction.DOWN));
				moves.add(new MoveCardMove(owner, unit, cell, Direction.LEFT));
				}
		}

	}

	public void appendRotateUnitMoves(Player owner, IdentityCard unit) {
		Direction currentDirection = unit.getDirection();
		if(currentDirection != Direction.UP){
			moves.add(new RotateUnitMove(owner, unit, Direction.UP));
		}
		if(currentDirection != Direction.RIGHT){
			moves.add(new RotateUnitMove(owner, unit, Direction.RIGHT));
		}
		if(currentDirection != Direction.DOWN){
			moves.add(new RotateUnitMove(owner, unit, Direction.DOWN));
		}
		if(currentDirection != Direction.LEFT){
			moves.add(new RotateUnitMove(owner, unit, Direction.LEFT));
		}
	}
	
	public void appendPlayUnitMoves(Player owner, IdentityCard unit, Cell cell) {
		if(!cell.hasUnit()){
			moves.add(new PlayUnitMove(owner, unit, cell, Direction.UP));
			moves.add(new PlayUnitMove(owner, unit, cell, Direction.RIGHT));
			moves.add(new PlayUnitMove(owner, unit, cell, Direction.DOWN));
			moves.add(new PlayUnitMove(owner, unit, cell, Direction.LEFT));
		}
	}
	
	public void appendPlayConstructionMoves(Player owner, ConstructionCard unit, Cell cell) {
		moves.add(new PlayConstructionMove(owner, unit, cell, Direction.UP));
		moves.add(new PlayConstructionMove(owner, unit, cell, Direction.RIGHT));
		moves.add(new PlayConstructionMove(owner, unit, cell, Direction.DOWN));
		moves.add(new PlayConstructionMove(owner, unit, cell, Direction.LEFT));
	}
		
	public String toString(){
		int i = 1;
		StringBuilder builder = new StringBuilder();
		for(Move move: moves){
			builder.append("(" + i + ") " + move.toString() + "\n");
			i++;
		}
		return builder.toString();
	}

	public boolean containsSeizeMove(){
		for(Move move: moves){
			if(move instanceof SeizeMove){
				return true;
			}
		}
		return false;
	}
	
	public Move getFirstSeizeMove(){
		for(Move move: moves){
			if(move instanceof SeizeMove){
				return move;
			}
		}
		return null;
	}
	
	@Override
	public Iterator<Move> iterator() {
		return moves.iterator();
	}
	
	

}
