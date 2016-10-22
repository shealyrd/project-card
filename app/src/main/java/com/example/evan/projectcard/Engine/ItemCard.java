package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class ItemCard extends Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6414461592507718132L;
	IdentityCard attachedTo;

	public ItemCard(String name, KeywordCollection keywords, Player owner, String text, ResourceCost cost) {
		super(name, keywords, owner, text, cost);
	}

	public boolean canAttachTo(IdentityCard unit) {return true;}
	public void onAttach(IdentityCard attachedTo) {}
	public void onUnattach(IdentityCard attachedTo) {}

	public IdentityCard getAttachedTo() {
		return attachedTo;
	}
	
	public void setAttachedTo(IdentityCard card){
		attachedTo = card;
	}

	public void removeAttachment() {
		attachedTo = null;
		
	}
}
