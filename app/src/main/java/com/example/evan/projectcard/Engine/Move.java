package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class Move implements Serializable{
	private static final long serialVersionUID = 8652542280802934777L;
	public Player owner;
	public Game game;
	public boolean overNetwork = false;

	public Move(){};
	public Move(Player owner){
		this.owner = owner;
	}
	public Move(Player owner, Game game){
		this.owner = owner;
		this.game = game;
	}
	

	public Game getGame(){
		return game;
	}
	public void setGame(Game game){
		this.game = game;
	}

	public Player getOwner() {
		return owner;
	}
	
	public String toString(){
		return null;
	}
}
