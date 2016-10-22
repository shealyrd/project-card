package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;

public class Hand extends CardCollection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6308075406149643990L;
	PlayerID owner;
	
	public Hand(Player player) {
		owner = player.getID();
	}
	
	public Hand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Card card){
		card.setFieldStatus(FieldStatus.Hand);
		collection.add(card);
	}

}
