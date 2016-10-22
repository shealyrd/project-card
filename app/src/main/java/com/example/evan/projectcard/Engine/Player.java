package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.Audio.Sounds;
import com.example.evan.projectcard.GameScreen.GameActivity;

import java.io.Serializable;

public class Player implements Serializable{
	private static final long serialVersionUID = 2755040905646001347L;
	public String name;
	public Hand hand;
	public Deck deck;
	public ResourcePool pool = new ResourcePool();
	Game game;
	PlayerID id;


	public Player(Game game) {
		this.game = game;
	}

	public String getName() {
		return name;
	}

	public Game getGame() {
		return game;
	}
	
	public PlayerID getID(){
		return id;
	}
	
	public void draw(){
		if(game.bothDecksEmpty()){
			new DeckOutFrame(game).launch();
		}
		else if(getDeckCount() > 0){
			int c = deck.draw();
			Card drawn = CardEngineLookup.getCardByID(c, this, getGame());
			hand.add(drawn);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hand getHand() {
		return hand;
	}

	public boolean canPayCost(ResourceCost cost) {
		return pool.canPayResourceCost(cost);
	}

	public ResourcePool getResourcePool() {
		return pool;
	}
	
	public int getDeckCount(){
		return deck.size();
	}

	public Player getOpponent() {
		if(this == game.currentPlayer){
			return game.waitingPlayer;
		}
		return game.currentPlayer;
	}

}
