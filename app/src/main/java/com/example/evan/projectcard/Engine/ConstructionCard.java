package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class ConstructionCard extends PersistentCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2020805710796914322L;

	public ConstructionCard(String name, KeywordCollection keywords, Player owner, String text, ResourceCost cost) {
		super(name, keywords, owner, text, cost);
	}
	
}
