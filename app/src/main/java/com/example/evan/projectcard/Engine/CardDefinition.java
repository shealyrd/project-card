package com.example.evan.projectcard.Engine;

/*
 * CardDefinition is a struct which holds the immutable information about each card.
 * The struct exists to facilitate the CardEngineLookup hashmap with an encapsulated value type.
 * 
 */

public class CardDefinition {
	CardType type;
	String name;
	String[] keywords;

	String text;
	int HP;
	int ATK;
	int AP;
	
	public CardDefinition(CardType type, String name, String[] keywords, String text, int HP, int ATK, int AP){
		this.type = type;
		this.name = name;
		this.keywords = keywords;
		this.text = text;
		this.HP = HP;
		this.ATK = ATK;
		this.AP = AP;
	}
}
