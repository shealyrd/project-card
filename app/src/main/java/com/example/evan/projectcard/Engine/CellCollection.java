package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class CellCollection implements Iterable<Cell>, Serializable {
	private static final long serialVersionUID = 728831099737251728L;
	ArrayList<Cell> cells = new ArrayList<Cell>();

	@Override
	public Iterator<Cell> iterator() {
		return cells.iterator();
	}
	
	public Cell[] asArray(){
		Cell[] result = new Cell[cells.size()];
		result = cells.toArray(result);
		return result;
	}

	public void add(Cell cell) {
		cells.add(cell);
	}
	
	public Cell getAtArrayListIndex(int index){
		return cells.get(index);
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(Cell cell: cells){
			builder.append("(C" + cell.getIndex() + "), ");
		}
		return builder.toString();
	}

	public boolean contains(Cell cell) {
		return cells.contains(cell);
	}

	public int size() {
		return cells.size();
	}
}
