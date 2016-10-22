package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public abstract class CraftCard extends Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8610663300206433817L;
	public Card activator;
	
	public CraftCard(String name, KeywordCollection keywords, Player owner, String text, ResourceCost cost) {
		super(name, keywords, owner, text, cost);
	}


	public abstract Frame activate(Card activator);


	public boolean canBeUsedBy(IdentityCard card) {
		return true;
	}
}
