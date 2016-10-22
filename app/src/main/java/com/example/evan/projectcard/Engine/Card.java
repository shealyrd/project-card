package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class Card  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3557280040390201451L;
	public Game game;
	String name;
	KeywordCollection keywords;
	public Player owner;
	String text;
	public Cell currentCell;
	FieldStatus status;
	ResourceCost cost;
	
	boolean isActivatable = false;
	
	public Card(String name, KeywordCollection keywords, Player owner, String text, ResourceCost cost){
		if(owner != null){
			this.game = owner.getGame();
		}
		this.name = name;
		this.keywords = keywords;
		this.owner = owner;
		this.text = text;
		this.cost = cost;
	}
	
	public Card() {
		// TODO Auto-generated constructor stub
	}

	public ResourceCost getResourceCost(){
		return cost;
	}
	public Frame activate(){
		return null;
	}
	
	public void setCell(Cell cell) {
		currentCell = cell;
	}

	public Cell getCell() {
		return currentCell;
	}
	
	public void setFieldStatus(FieldStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}

	public Game getGame() {
		return game;
	}
	
	public boolean hasKeyword(String word){
		return keywords.contains(word);
	}
	
	public void addKeyword(String word){
		keywords.add(word);
	}
	
	public void removeKeyword(String string) {
		keywords.remove("magical");
	}
	
	public void setActivatable(){
		isActivatable = true;
	}
	
	
	public FieldStatus getFieldStatus() {
		return status;
	}
	
	public void removeFromHand(){
		if(getFieldStatus() == FieldStatus.Hand){
			getOwner()
				.getHand()
					.remove(this);
		}
	}

	public boolean canActivate(){
		if(isActivatable){
			if(activationConditionsMet()){
				return true;
			}
		}
		return false;
	}
	
	public boolean activationConditionsMet() { return false;}

	public void onPlay(){}
	public void onDestroy(){}
	public void onHarvestPhase(){}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Name: " + name + "\n");
		builder.append("Owner: " + owner.getName() + "\n");
		builder.append("Cell: " + currentCell.getIndex() + "\n");
		builder.append("Keywords: " + keywords.toString() + "\n");
		builder.append("Status: " + status.toString() + "\n");
		return builder.toString();
	}


	public ResourceCost getCost() {
		return cost;
	}


}
