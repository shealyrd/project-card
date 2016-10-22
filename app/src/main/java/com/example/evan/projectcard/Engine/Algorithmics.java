package com.example.evan.projectcard.Engine;


import java.io.IOException;
import java.nio.charset.Charset;

public class Algorithmics {
	
	public static int getColumn(int index){
		switch (index % 3){
		case 1: return 1;
		case 2: return 2;
		case 0: return 3;
		default: return 0;
		}
	}
	
	public static int getRow(int index){
		switch ((index - 1)/3){
		case 0: return 1;
		case 1: return 2;
		case 2: return 3;
		case 3: return 4;
		case 4: return 5;
		case 5: return 6;
		default: return 0;
		}
	}
	
	public static boolean isFacingCell(IdentityCard card){
		int index = card.getCell().getIndex();
		Direction facing = card.getDirection();
		if((getColumn(index) == 1) && (facing == Direction.LEFT)){
			return false;
		}
		if((getColumn(index) == 3) && (facing == Direction.RIGHT)){
			return false;
		}
		if((getRow(index) == 1) && (facing == Direction.UP)){
			return false;
		}
		if((getRow(index) == 6) && (facing == Direction.DOWN)){
			return false;
		}
		return true;
	}
	
	public static Cell getCellFacing(PersistentCard card){
		int index = card.getCell().getIndex();
		int result = 0;
		Direction facing = card.getDirection();
		switch(facing){
			case UP: result = index - 3; break;
			case DOWN: result = index + 3; break;
			case RIGHT: result = index + 1; break;
			case LEFT: result = index - 1; break;
		}
		if(Algorithmics.isBetween(result, 1, 18)){
			return card.getGame().getBoard().getCellAtIndex(result);
		}
		return null;
	}
	

	public static Direction getDirectionNeededToFace(int indexOfOrigin, int indexRelative){
		int up, right, down, left;
		up = indexOfOrigin - 3;
		right = indexOfOrigin + 1;
		down = indexOfOrigin + 3;
		left = indexOfOrigin - 1;
		
		if(getColumn(indexOfOrigin) == 1){
			left = 0;
		}
		if(getColumn(indexOfOrigin) == 3){
			right = 0;
		}
		if(getRow(indexOfOrigin) == 1){
			up = 0;
		}
		if(getRow(indexOfOrigin) == 6){
			down = 0;
		}
		if(indexRelative == up){
			return Direction.UP;
		}
		if(indexRelative == right){
			return Direction.RIGHT;
		}
		if(indexRelative == down){
			return Direction.DOWN;
		}
		if(indexRelative == left){
			return Direction.LEFT;
		}
		return Direction.UP;
	}

	public static Direction getDirectionNeededToFace(Cell currentCell, Cell cell) {
		return getDirectionNeededToFace(currentCell.getIndex(), cell.getIndex());
	}
	


	public static CellCollection getAdjacentCells(Cell cell, Board board) {
		CellCollection result = new CellCollection();
		int indexOfOrigin = cell.getIndex();
		int up, right, down, left;
		up = indexOfOrigin - 3;
		right = indexOfOrigin + 1;
		down = indexOfOrigin + 3;
		left = indexOfOrigin - 1;
		if(getColumn(indexOfOrigin) == 1){
			left = 0;
		}
		if(getColumn(indexOfOrigin) == 3){
			right = 0;
		}
		if(getRow(indexOfOrigin) == 1){
			up = 0;
		}
		if(getRow(indexOfOrigin) == 6){
			down = 0;
		}
		if(up != 0){
			result.add(board.getCellAtIndex(up));
		}
		if(right != 0){
			result.add(board.getCellAtIndex(right));
		}
		if(left != 0){
			result.add(board.getCellAtIndex(left));
		}
		if(down != 0){
			result.add(board.getCellAtIndex(down));
		}
		return result;
	}
	
	public static boolean isInteger(String arg){
		 try { 
		        Integer.parseInt(arg); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    }
		    return true;
	}
	
	public static boolean isBetween(int arg, int lowerBound, int upperBound){
		if(arg < lowerBound){
			return false;
		}
		if(arg > upperBound){
			return false;
		}
		return true;
	}

	public static Cell getCellAtBoundary(Cell cell, Direction direction) {
		int column = getColumn(cell.index);
		int row = getRow(cell.index);
		switch(direction){
			case UP: row = 1; break;
			case DOWN: row = 6; break;
			case RIGHT: column = 1; break;
			case LEFT: column = 3; break;
		}
		Cell result = getCellForCartesian(row, column, cell.getOwner().getGame().getBoard());
		return result;
	}

	private static Cell getCellForCartesian(int row, int column, Board board) {
		int index = 1;
		index = ((row-1) * 3) + (column);
		return board.getCellAtIndex(index);
	}
}

