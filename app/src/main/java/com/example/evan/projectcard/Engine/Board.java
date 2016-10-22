package com.example.evan.projectcard.Engine;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;

public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5260021415270569819L;
	Game game;
	Hand p1Hand;
	Hand p2Hand;
	Deck p1Deck;
	Deck p2Deck;
	CellGrid cellGrid;
	
	public Board(Game game){
		this.game = game;
		cellGrid = new CellGrid(game);
	}
	
	public Board(){
		
	}
	
	public Board applyMove(Move arg){
		BoardMovePair pair = new BoardMovePair(this, arg);
		BoardMovePair boardMove = null;
		try {
			boardMove = (BoardMovePair) Cloning.deepCopy(pair);
		} catch (Exception e) {
			Toast.makeText(game.getActivity(),  e.toString(), Toast.LENGTH_LONG).show();
		}
		Move move = boardMove.getMove();
		Board board = boardMove.getBoard();
		Game newGame = board.game;
		if(move instanceof PlayCardMove
				|| move instanceof PlayUnitMove
				|| move instanceof PlayConstructionMove
				|| move instanceof PlayItemMove
				|| move instanceof PlayCraftMove ){
			new PlayCardFrame(newGame, (PlayCardMove) move).launch();
		}/*
		if(move instanceof PlayUnitMove){
			Log.add("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
			new PlayUnitFrame(newGame, (PlayUnitMove) move).launch();
		}*/
		if(move instanceof RotateUnitMove){
			new RotateUnitFrame(newGame, (RotateUnitMove) move).launch();
		}
		if(move instanceof MoveCardMove){
			new MoveCardFrame(newGame, (MoveCardMove) move).launch();
		}
		if(move instanceof AttackMove){
			new AttackFrame(newGame, (AttackMove) move).launch();
		}
		if(move instanceof ActivateEffectMove){
			new ActivateEffectFrame(newGame, (ActivateEffectMove) move).launch();
		}
		if(move instanceof SeizeMove){
			new SeizeFrame(newGame, (SeizeMove) move).launch();
		}
		return board;
	}
	
	/*public String toString(){
		String result = "";
		try {
			result = Algorithmics.readFile("BoardToStringTemplate.txt", Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Cell[] cellGridArr = cellGrid.asArray();
		for(int i = 0; i < cellGridArr.length; i++){
			String tempTokenC = "<c" + (i + 1) + ">";
			String tempTokenD = "<d" + (i + 1) + ">";
			result = result.replace(tempTokenC, cellGridArr[i].owner.name);
			if (cellGridArr[i].hasUnit()){
				result = result.replace(tempTokenD, cellGridArr[i].getIdentity().getDirection().toArrow());
			}
			else{
				result = result.replace(tempTokenD, "");
			}

		}
		Player[] players = game.getPlayers();
		result = result.replace("<p1>", players[0].getName());
		result = result.replace("<p2>", players[1].getName());
		result = result.replace("<p1hand>", p1Hand.toString());
		result = result.replace("<p2hand>", p2Hand.toString());
		result = result.replace("<p1deck>", p1Deck.toString());
		result = result.replace("<p2deck>", p2Deck.toString());
		result = result.replace("<p1resource>", players[0].getResourcePool().toString());
		result = result.replace("<p2resource>", players[1].getResourcePool().toString());
		
		StringBuilder builder = new StringBuilder();
		builder.append(result);
		builder.append("\n\n");
		for(int i = 0; i < cellGridArr.length; i++){
			builder.append(cellGridArr[i].toString());
			builder.append("\n");
		}
		return builder.toString();
	}*/

	public CellGrid getCellGrid() {
		return cellGrid;
	}
	
	public CardCollection getCurrentUnits() {
		CardCollection result = new CardCollection();
		for(Cell cell: cellGrid){
			if(cell.hasUnit()){
				result.add(cell.getIdentity());
			}
		}
		return result;
	}

	public CardCollection getCurrentUnits(Player player) {
		CardCollection result = new CardCollection();
		CardCollection units = getCurrentUnits();
		for(Card card: units){
			if(card.getOwner() == player){
				result.add(card);
			}
		}
		return result;
	}

	public Cell getCellAtIndex(int index) {
		for(Cell cell: cellGrid){
			if(cell.index == index){
				return cell;
			}
		}
		Log.error("Board.getCellAtIndex -- queried index not found. "
				+ "\n Attempted index: " + index);
		return cellGrid.getAtArrayListIndex(1);
	}
	
	public CellCollection getCellsOwnerBy(Player player){
		CellCollection cells = new CellCollection();
		for(Cell cell: cellGrid){
			if(cell.getOwner() == player){
				cells.add(cell);
			}
		}
		return cells;
	}

	public CellCollection getRowAtIndex(int index) {
		CellCollection result = new CellCollection();
		int firstIndex = 1 + (3 *(index - 1));
		int secondIndex =  2 + (3 *(index - 1));
		int thirdIndex =  3 + (3 *(index - 1));
		result.add(getCellAtIndex(firstIndex));
		result.add(getCellAtIndex(secondIndex));
		result.add(getCellAtIndex(thirdIndex));
		return result;
	}
	
	public CellCollection getColumnByIndex(int index) {
		CellCollection result = new CellCollection();
		int rowIndex = Algorithmics.getColumn(index);
		switch(rowIndex){
		case 1: 
			result.add(getCellAtIndex(1));
			result.add(getCellAtIndex(4));
			result.add(getCellAtIndex(7));
			result.add(getCellAtIndex(10));
			result.add(getCellAtIndex(13));
			result.add(getCellAtIndex(16));
			break;
		case 2:
			result.add(getCellAtIndex(2));
			result.add(getCellAtIndex(5));
			result.add(getCellAtIndex(8));
			result.add(getCellAtIndex(11));
			result.add(getCellAtIndex(14));
			result.add(getCellAtIndex(17));
			break;
		case 3:
			result.add(getCellAtIndex(3));
			result.add(getCellAtIndex(6));
			result.add(getCellAtIndex(9));
			result.add(getCellAtIndex(12));
			result.add(getCellAtIndex(15));
			result.add(getCellAtIndex(18));
			break;
		}
		return result;
	}
	
	public CellCollection getRowForCellIndex(int index) {
		CellCollection result = new CellCollection();
		int rowIndex = Algorithmics.getRow(index);
		switch(rowIndex){
		case 1: 
			result.add(getCellAtIndex(1));
			result.add(getCellAtIndex(2));
			result.add(getCellAtIndex(3));
			break;
		case 2:
			result.add(getCellAtIndex(4));
			result.add(getCellAtIndex(5));
			result.add(getCellAtIndex(6));
			break;
		case 3:
			result.add(getCellAtIndex(7));
			result.add(getCellAtIndex(8));
			result.add(getCellAtIndex(9));
			break;
		case 4:
			result.add(getCellAtIndex(10));
			result.add(getCellAtIndex(11));
			result.add(getCellAtIndex(12));
			break;
		case 5:
			result.add(getCellAtIndex(13));
			result.add(getCellAtIndex(14));
			result.add(getCellAtIndex(15));
			break;
		case 6:
			result.add(getCellAtIndex(16));
			result.add(getCellAtIndex(17));
			result.add(getCellAtIndex(18));
			break;
		}
		return result;
	}
	
	public CardCollection getAllCardsOnField(){
		CardCollection result = new CardCollection();
		for(Cell cell: cellGrid){
			result.add(cell.getAllCardsOnField());
		}
		return result;
	}

	public CardCollection getCurrentPersistentsAndItems(Player player) {
		CardCollection result = new CardCollection();
		for(Cell cell: cellGrid){
			if(cell.hasUnit()){
				IdentityCard currentUnit = cell.getIdentity();
				result.add(currentUnit);
				if(currentUnit.hasItem())
				{
					result.add(currentUnit.getItems());
				}
			}
			if(cell.hasConstruction()){
				result.add(cell.getConstructions());
			}
		}
		return result;
	}

	public boolean allCellsSeized(Player owner) {
		for(Cell cell: cellGrid){
			if(cell.getOwner() != owner){
				return false;
			}
		}
		return true;
	}

	public CardCollection getCurrentConstructions(Player player) {
		CardCollection constructions = new CardCollection();
		for(Cell cell: cellGrid){
			if(cell.getOwner() == player){
				constructions.add(cell.getConstructions());
			}
		}
		return constructions;
	}

	public CardCollection getCurrentItems(Player player) {
		CardCollection items = new CardCollection();
		CardCollection units = getCurrentUnits(player);
		for(Card card: units){
			items.add(((IdentityCard) card).getItems());
		}
		return items;
	}

}
