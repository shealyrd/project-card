package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Deck implements Serializable{
	private static final long serialVersionUID = 7116095088575526001L;
	ArrayList<Integer> cardIDs = new ArrayList<Integer>();
	Player owner;

	public Deck(Player owner){
		this.owner = owner;
	}
	
	public Deck() {
		// TODO Auto-generated constructor stub
	}

	public void fillDeck(Integer[] ints){
		for(int i = 0; i < ints.length; i++){
			for(int j = 0; j < ints[i]; j++){
				cardIDs.add(i + 1);
			}
		}
		Collections.shuffle(cardIDs);
		//android.util.Log.d("INSIDE DECK", Arrays.toString(cardIDs.toArray()));
	}


	public void fillRandomly(int deckSize) {
		Random rand = new Random();
		//cardIDs.add(1);
		for(int i = 0; i < deckSize; i++){
			//cardIDs.add(13);
			int card = rand.nextInt(CardEngineLookup.MAX_ID) + 1;
			if(card == 21 || card == 12 || card == 15){
				i--;
			}
			else{
				cardIDs.add(card);
			}
		}
	}

	public int draw(){
		int id = cardIDs.get(0);
		cardIDs.remove(0);
		return id;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(Integer i: cardIDs){
			builder.append(i + ", ");
		}
		return builder.toString();
	}
	
	public int size(){
		return cardIDs.size();
	}

}
