package com.example.evan.projectcard.Engine;

public class PlayItemMove extends PlayCardMove {
	public IdentityCard attachedTo;
	public ItemCard toPlay;

	public PlayItemMove(Player owner, Card toPlay, IdentityCard attachedTo) {
		super(owner, toPlay, attachedTo.getCell());
		this.attachedTo = attachedTo;
		this.toPlay = (ItemCard) toPlay;
	}

	public PlayItemMove() {

	}

	@Override
	public boolean isFromHand() {
		if(toPlay.getFieldStatus() == FieldStatus.Hand){
			return true;
		}
		return false;
	}

	public ItemCard getCardPlayed(){
		return this.toPlay;
	}

	public void setCardPlayed(ItemCard item){
		this.toPlay = item;
	}

	public IdentityCard getCardToAttachTo(){
		return attachedTo;
	}
	
	public String toString(){
		return "Attach " + toPlay.getName() + " to " + attachedTo.getName();
	}

}
