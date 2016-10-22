package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class DiscardPile extends CardCollection implements Serializable{
	private static final long serialVersionUID = 2858051129603635816L;

	public CardCollection getUnits(){
		CardCollection result = new CardCollection();
		for(Card card: collection){
			if(card instanceof IdentityCard){
				result.add(card);
			}
		}
		return result;
	}
}
