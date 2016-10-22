package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CellGrid extends CellCollection  implements Serializable{
	private static final long serialVersionUID = -5248694449156869953L;
	Game game;
	int length = 18;
	
	public CellGrid(Game game){
		this.game = game;
		Cell[] cellGrid = new Cell[18];
		for(int i = 0; i < cellGrid.length; i++){
			if(i < (9)){
				cellGrid[i] = new Cell(i + 1, game.players[1]);
			}
			else{
				cellGrid[i] = new Cell(i + 1, game.players[0]);
			}
		}
		cells = new ArrayList<Cell>(Arrays.asList(cellGrid));
	}

	public CellGrid() {
		// TODO Auto-generated constructor stub
	}

	public CellCollection getCellsForPlayer(Player player) {
		CellCollection result = new CellCollection();
		for(Cell cell: cells){
			if(cell.getOwner() == player){
				result.add(cell);
			}
		}
		return result;
	}
	

	


}
