package com.example.evan.projectcard.Engine;
import java.util.HashMap;

public class CardEngineLookup {
	public static final int MAX_ID = 26;

	public static Card getCardByID(int id, Player player, Game game){
		switch(id){
			case 1: return new Card_AncientSeal(player);
			case 2: return new Card_Assassin(player);
			case 3: return new Card_BigassAnimeSword(player);
			case 4: return new Card_BlueObelisk(player);
			case 5: return new Card_Bomb(player);
			case 6: return new Card_ButterflyBlade(player);
			case 7: return new Card_Castle(player);
			case 8: return new Card_Church(player);
			case 9: return new Card_DarkKnight(player);
			case 10: return new Card_Fireball(player);
			case 11: return new Card_GrasshopperGloves(player);
			case 12: return new Card_GroveOfRite(player);
			case 13: return new Card_Merchant(player);
			case 14: return new Card_Necromancer(player);
			case 15: return new Card_Priest(player);
			case 16: return new Card_RankAndFile(player);
			case 17: return new Card_RedKnight(player);
			case 18: return new Card_RedObelisk(player);
			case 19: return new Card_RingOfConstitution(player);
			case 20: return new Card_Scout(player);
			case 21: return new Card_Shove(player);
			case 22: return new Card_Thunderbolt(player);
			case 23: return new Card_Torrent(player);
			case 24: return new Card_WideHeal(player);
			case 25: return new Card_Wizard(player);
			case 26: return new Card_YellowObelisk(player);
			default: return new Card_RankAndFile(player);
		}
	}

	public static Card getCardByID(int id){
		return getCardByID(id, null, null);
	}

	public static int getIDFromCard(Card card){
		for(int i = 1; i <= 26; i++){
			Card c = getCardByID(i, new Player(card.getGame()), card.getGame());
			boolean result = c.getClass().equals(card.getClass());
			if(result){
				return i;
			}
		}
		card.getGame().log("CATASTROPHIC ERROR");
		return 0;
	}
}
