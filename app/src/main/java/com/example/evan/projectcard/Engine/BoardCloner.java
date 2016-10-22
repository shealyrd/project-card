package com.example.evan.projectcard.Engine;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardCloner {
	HashMap<Object, Object> clonedObjectMapping = new HashMap<Object, Object>();
	
	public Board makeBoardClone(Board board){
		putAll(board);
		Board result = new Board();
		result.cellGrid = cloneCellGrid(board.cellGrid);
		result.p1Deck = cloneDeck(board.p1Deck);
		result.p2Deck = cloneDeck(board.p2Deck);
		result.p1Hand = (Hand) cloneCardCollection(board.p1Hand);
		result.p2Hand = (Hand) cloneCardCollection(board.p2Hand);
		return result;
	}
	

	private Deck cloneDeck(Deck deck) {
		Deck result = (Deck) get(deck);
		result.cardIDs = (ArrayList<Integer>) deck.cardIDs.clone();
		return result;
	}

	private CellGrid cloneCellGrid(CellGrid cellGrid) {
		CellGrid result = (CellGrid) get(cellGrid);
		for(Cell cell: cellGrid){
			result.add(cloneCell(cell));
		}
		return result;
	}

	private Cell cloneCell(Cell cell) {
		Cell result = (Cell) get(cell);
		result.immuneToMagic = cell.immuneToMagic;
		result.index = cell.index;
		result.upSide = cell.upSide;
		result.rightSide = cell.rightSide;
		result.downSide = cell.downSide;
		result.leftSide = cell.leftSide;
		result.constructions = cloneCardCollection(cell.constructions);
		result.discardPile = (DiscardPile) cloneCardCollection(cell.discardPile);
		result.unitIdentity = (IdentityCard) cloneIdentityCard(cell.unitIdentity);
		return result;
	}

	private CardCollection cloneCardCollection(CardCollection collection) {
		CardCollection result = (CardCollection) get(collection);
		for(Card card: collection){
			if(card instanceof IdentityCard){
				result.add(cloneIdentityCard((IdentityCard) card));	
			}
			if(card instanceof ItemCard){
				result.add(cloneItemCard((ItemCard) card));
			}
			if(card instanceof CraftCard){
				result.add(cloneCraftCard((CraftCard) card));
			}
			if(card instanceof ConstructionCard){
				result.add(cloneConstructionCard((ConstructionCard) card));
			}
		}
		return result;
	}

	private Card cloneConstructionCard(ConstructionCard card) {
		ConstructionCard result = (ConstructionCard) get(card);
		result.direction = card.direction;
		result.isActivatable = card.isActivatable;
		result.name = card.name;
		result.text = card.text;
		result.status = card.status;
		result.cost = cloneResourceCost(card.cost);
		result.currentCell = (Cell) get(card.currentCell);
		result.keywords = cloneKeywordCollection(card.keywords);
		return result;
	}

	private Card cloneCraftCard(CraftCard card) {
		CraftCard result = (CraftCard) get(card);
		result.activator = card.activator;
		result.isActivatable = card.isActivatable;
		result.name = card.name;
		result.text = card.text;
		result.status = card.status;
		result.cost = cloneResourceCost(card.cost);
		result.currentCell = (Cell) get(card.currentCell);
		result.keywords = cloneKeywordCollection(card.keywords);
		return result;
	}

	private Card cloneItemCard(ItemCard card) {
		ItemCard result = (ItemCard) get(card);
		result.attachedTo = (IdentityCard) get(card.attachedTo);
		result.status = card.status;
		result.isActivatable = card.isActivatable;
		result.name = card.name;
		result.text = card.text;
		result.cost = cloneResourceCost(card.cost);
		result.currentCell = (Cell) get(card.currentCell);
		result.keywords = cloneKeywordCollection(card.keywords);
		return result;
	}

	private Card cloneIdentityCard(IdentityCard card) {
		IdentityCard result = (IdentityCard) get(card);
		result.canSeize = card.canSeize;
		result.currentAP = card.currentAP;
		result.currentATK = card.currentATK;
		result.currentHP = card.currentHP;
		result.currentMaxHP = card.currentMaxHP;
		result.direction = card.direction;
		result.isActivatable = card.isActivatable;
		result.name = card.name;
		result.text = card.text;
		result.status = card.status;
		result.cost = cloneResourceCost(card.cost);
		result.currentCell = (Cell) get(card.currentCell);
		result.items = cloneCardCollection(card.items);
		result.keywords = cloneKeywordCollection(card.keywords);
		return result;
	}

	private KeywordCollection cloneKeywordCollection(KeywordCollection keywords) {
		KeywordCollection result = (KeywordCollection) get(keywords);
		for(String word: keywords.keywords){
			result.keywords.add(word);
		}
		return result;
	}

	private ResourceCost cloneResourceCost(ResourceCost cost) {
		ResourceCost result = (ResourceCost) get(cost);
		result.basics = cost.basics;
		result.fires = cost.fires;
		result.waters = cost.waters;
		result.lightnings = cost.lightnings;
		return result;
	}

	public void putAll(Board board){
		put(board.cellGrid, new CellGrid());
		put(board.p1Deck, new Deck());
		put(board.p2Deck, new Deck());
		put(board.p1Hand, new Hand());
		put(board.p2Hand, new Hand());
		for(Cell cell: board.cellGrid){
			put(cell, new Cell());
			put(cell.constructions, new CardCollection());
				for(Card card: cell.constructions){
					put(card, new Card());
				}
			put(cell.discardPile, new CardCollection());
			for(Card card: cell.discardPile){
				put(card, new Card());
			}
			if(cell.hasUnit()){
			put(cell.unitIdentity, new Card());
				put(cell.unitIdentity.cost, new ResourceCost());
				put(cell.unitIdentity.items, new CardCollection());
					for(Card card: cell.unitIdentity.items){
						put(card, new Card());
					}
			put(cell.unitIdentity.keywords, new KeywordCollection());
			}
		}
		for(Card card: board.p1Hand){
			put(card, new Card());
		}
		for(Card card: board.p2Hand){
			put(card, new Card());
		}
	}



	public void put(Object key, Object val){
		if(!clonedObjectMapping.containsKey(key)){
			clonedObjectMapping.put(key, val);
		}
	}
	
	public Object get(Object key){
		return clonedObjectMapping.get(key);
	}
	
	
}
