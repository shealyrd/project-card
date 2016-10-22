package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CellView;

import java.io.Serializable;

public class Cell implements Serializable{
	private static final long serialVersionUID = 7643859755281810474L;
	int index;
	IdentityCard unitIdentity;
	CardCollection constructions = new CardCollection();
	DiscardPile discardPile = new DiscardPile();
	Player owner;
	
	boolean immuneToMagic = false;
	
	CellSideStatus upSide = CellSideStatus.NORMAL;
	CellSideStatus rightSide = CellSideStatus.NORMAL;
	CellSideStatus downSide = CellSideStatus.NORMAL;
	CellSideStatus leftSide = CellSideStatus.NORMAL;

	public Cell(int i, Player owner) {
		index = i;
		this.owner = owner;
	}

	public Cell() {
		// TODO Auto-generated constructor stub
	}

	public void setUnit(IdentityCard playedCard) {
		unitIdentity = playedCard;
		playedCard.setCell(this);
	}

	public CellView getView(){
		if(owner == null){
			android.util.Log.d("Cell", "Owner null");
		}
		else if(owner.game == null){
			android.util.Log.d("Cell", "owner.game null");
		}
		else if(owner.game.activity == null){
			android.util.Log.d("Cell", "owner.game.activity null");
		}
		GameActivity act = (GameActivity) owner.getGame().getActivity();
		return act.getCellAtIndex(index);
	}

	public void addConstruction(ConstructionCard playedCard) {
		constructions.add(playedCard);
	}

	public void removeUnit() {
		unitIdentity = null;
	}

	public void addToDiscardPile(Card discarded) {
		discardPile.add(discarded);
		discarded.setFieldStatus(FieldStatus.DiscardPile);
	}

	public int getIndex() {
		return index;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("C" + index + ": ");
		if(hasUnit()){
			builder.append("\nIdentity: \n" + unitIdentity.toString());
		}
		if(!constructions.isEmpty()){
			builder.append("\nConstructions: " + constructions.toString());
		}
		if(!discardPile.isEmpty()){
			builder.append("\nDiscardPile: " + discardPile.toString());
		}
		return builder.toString();
	}

	public boolean hasUnit() {
		if(unitIdentity != null){
			return true;
		}
		else{
			return false;
		}
	}

	public Player getOwner() {
		return owner;
	}

	public IdentityCard getIdentity() {
		return unitIdentity;
	}
	
	public boolean canBeSeizedBy(IdentityCard card){
		if(card.canSeize(this) == false){
			return false;
		}
		if(card.getOwner() == getOwner()){
			return false;
		}
		return true;
	}

	public void destroyAllConstructions() {
		for(Card construction: constructions){
			addToDiscardPile(construction);
		}
		constructions.clear();
	}

	public DiscardPile getDiscardPile() {
		return discardPile;
	}

	public void destroyAllCards() {
		destroyAllConstructions();
		if(hasUnit()){
			destroyIdentity();
		}
	}

	public void destroyIdentity() {
		if(hasUnit()){
			unitIdentity.destroy();
			removeUnit();
		}
	}
	
	public void blockSide(Direction direction){
		switch(direction){
			case UP: upSide = CellSideStatus.BLOCKED; break;
			case RIGHT: rightSide = CellSideStatus.BLOCKED; break;
			case DOWN: downSide = CellSideStatus.BLOCKED; break;
			case LEFT: leftSide = CellSideStatus.BLOCKED; break;
		}
	}

	public void unblockAllSides() {
		upSide = CellSideStatus.NORMAL;
		rightSide = CellSideStatus.NORMAL;
		downSide = CellSideStatus.NORMAL;
		leftSide = CellSideStatus.NORMAL;
	}

	public void setImmuneToMagic() {
		immuneToMagic = true;
	}
	
	public void resetImmuneToMagic(){
		immuneToMagic = false;
	}
	
	public boolean containsConstruction(String arg){
		for(Card card: constructions){
			if(card.name == arg){
				return true;
			}
		}
		return false;
	}

	public void removeFromDiscardPile(Card chosen) {
		discardPile.remove(chosen);
	}

	public boolean hasConstruction() {
		if(constructions.size() > 0){
			return true;
		}
		return false;
	}
	
	public CardCollection getConstructions(){
		return constructions;
	}

	public boolean isEmpty() {
		if(hasUnit()){
			return false;
		}
		if(constructions.size() != 0){
			return false;
		}
		return true;
	}

	public boolean isImmuneToMagic() {
		return immuneToMagic;
	}

	public CardCollection getAllCardsOnField() {
		CardCollection result = new CardCollection();
		if(hasUnit()){
			result.add(unitIdentity.getAllCards());
		}
		result.add(constructions);
		return result;
	}

	public void setOwner(Player newOwner) {
		this.owner = newOwner;
	}

	public boolean hasOpenAdjacentCell() {
		CellCollection adjacent = Algorithmics.getAdjacentCells(this, owner.game.board);
		for(Cell cell: adjacent){
			if(!cell.hasUnit()){
				return true;
			}
		}
		return false;
	}


	

}
