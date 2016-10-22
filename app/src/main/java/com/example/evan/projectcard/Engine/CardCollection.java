package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CardCollection implements Iterable<Card>, Serializable{
	private static final long serialVersionUID = 5433676273980227348L;
	ArrayList<Card> collection = new ArrayList<Card>();
	
	public void add(Card card){
		collection.add(card);
	}
	
	public void remove(Card card){
		if(collection.contains(card)){
			collection.remove(card);
		}
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(Card card: collection){
			builder.append(card.getName() + ", ");
		}
		return builder.toString();
	}

	@Override
	public Iterator<Card> iterator() {
		return collection.iterator();
	}

	public boolean isEmpty() {
		return collection.isEmpty();
	}

	public void clear() {
		collection.clear();
		
	}

	public Card getAtIndex(int index) {
		if(collection.get(index) != null){
			return collection.get(index);
		}
		else{
			return null;
		}
	}

	public int size() {
		return collection.size();
	}

	public void add(CardCollection cards) {
		if(cards.size() != 0){
			collection.addAll(cards.getArrayList());
		}
	}

	public Collection<? extends Card> getArrayList() {
		return collection;
	}

	public String toChoiceFormattedString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < collection.size(); i++){
			builder.append("(" + i + ") " + collection.get(i).getName() + "\n");
		}
		return builder.toString();
	}

	public ArrayList<Card> getCopyOfArrayList() {
		return (ArrayList<Card>) collection.clone();
	}


	
}
