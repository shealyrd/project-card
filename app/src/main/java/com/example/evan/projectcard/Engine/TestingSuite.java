package com.example.evan.projectcard.Engine;
import java.util.ArrayList;

public class TestingSuite {
	static int TESTING_COST = 5;
	
	public static String testAncientSeal(){
		TestGame game = new TestGame();
		IdentityCard attachTo = new Card_RankAndFile(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer,  attachTo, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayItemMove(game.currentPlayer, new Card_AncientSeal(game.currentPlayer), attachTo));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(attachTo.hasKeyword("magical")){
			return "SUCCESS: Ancient Seal is working.";
		}
		return "ERROR: Ancient Seal is not working.";
	}
	
	public static String testGrasshopperGloves(){
		TestGame game = new TestGame();
		IdentityCard attachTo = new Card_RankAndFile(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer,  attachTo, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayItemMove(game.currentPlayer, new Card_GrasshopperGloves(game.currentPlayer), attachTo));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(attachTo.hasKeyword("melee")){
			return "SUCCESS: Grasshopper Gloves is working.";
		}
		return "ERROR: Grasshopper Gloves is not working.";
	}
	
	public static String testBigassAnimeSword(){
		TestGame game = new TestGame();
		IdentityCard attachTo = new Card_RankAndFile(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer,  attachTo, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayItemMove(game.currentPlayer, new Card_BigassAnimeSword(game.currentPlayer), attachTo));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(attachTo.currentATK == (attachTo.defaultATK * 2)){
			return "SUCCESS: Bigass Anime Sword is working.";
		}
		//TODO: add checks for AP and such
		return "ERROR: Bigass Anime Sword is not working.";
	}
	
	public static String testRingOfConstitution(){
		TestGame game = new TestGame();
		IdentityCard attachTo = new Card_RankAndFile(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer,  attachTo, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayItemMove(game.currentPlayer, new Card_RingOfConstitution(game.currentPlayer), attachTo));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(attachTo.getMaxHP() == (attachTo.defaultHP * 2)){
			return "SUCCESS: Ring of Constitution is working.";
		}
		//TODO: add checks for AP and such
		return "ERROR: Ring of Constitution is not working.";
	}
	
	public static String testButterflyBlade(){
		TestGame game = new TestGame();
		game.setTurnCount(2);
		IdentityCard attacker = new Card_RankAndFile(game.currentPlayer);
		IdentityCard attacked = new Card_RankAndFile(game.waitingPlayer);
		ItemCard butterflyBlade = new Card_ButterflyBlade(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, attacked, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayItemMove(game.currentPlayer, butterflyBlade, attacked));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.addMove(new PlayUnitMove(game.waitingPlayer, attacker, game.getBoard().getCellAtIndex(4), Direction.UP));
		game.addMove(new AttackMove(game.waitingPlayer, attacker, attacked));
		game.addMove(new EndMainPhaseMove(game.waitingPlayer));
		game.start();
		if(!attacker.isAlive()){
			return "SUCCESS: Butterfly Blade is working.";
		}
		return "ERROR: Butterfly Blade is not working.";
	}
	
	public static String testCastle(){
		TestGame game = new TestGame();
		game.setTurnCount(2);
		IdentityCard victim = new Card_RankAndFile(game.currentPlayer);
		ConstructionCard castle = new Card_Castle(game.currentPlayer);
		IdentityCard attacker = new Card_RankAndFile(game.waitingPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, victim, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayConstructionMove(game.currentPlayer, castle, game.getBoard().getCellAtIndex(1), Direction.DOWN));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.addMove(new PlayUnitMove(game.waitingPlayer, attacker, game.getBoard().getCellAtIndex(4), Direction.UP));
		game.addMove(new EndMainPhaseMove(game.waitingPlayer));
		game.start();
		//Log.supAdd(victim.getCell().downSide.toString());
		if(!attacker.canAttack(victim)){
			return "SUCCESS: Castle is working.";
		}
		return "ERROR: Castle is not working.";
	}
	
	public static String testGroveOfRite(){
		TestGame game = new TestGame();
		IdentityCard necromancer = new Card_Necromancer(game.currentPlayer);
		ConstructionCard grove = new Card_GroveOfRite(game.currentPlayer);
		IdentityCard dead = new Card_Wizard(game.currentPlayer);
		CraftCard spell = new Card_Fireball(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, dead, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayCraftMove(game.currentPlayer, spell, dead));
		game.addIntResponse(1);
		game.addMove(new PlayConstructionMove(game.currentPlayer, grove, game.getBoard().getCellAtIndex(1), Direction.DOWN));
		game.addMove(new PlayUnitMove(game.currentPlayer, necromancer, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new ActivateEffectMove(game.currentPlayer, grove));
		game.addIntResponse(0);
		game.addIntResponse(4);
		game.addIntResponse(1);
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		//Log.supAdd(victim.getCell().downSide.toString());
		if(!game.getBoard().getCellAtIndex(4).isEmpty()){
			return "SUCCESS: Grove of Rite is working.";
		}
		return "ERROR: Grove of Rite is not working.";
	}
	
	public static String testChurch(){
		TestGame game = new TestGame();
		game.setTurnCount(2);
		IdentityCard victim = new Card_RankAndFile(game.currentPlayer);
		ConstructionCard church = new Card_Church(game.currentPlayer);
		IdentityCard attacker = new Card_Wizard(game.waitingPlayer);
		CraftCard spell = new Card_Fireball(game.waitingPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, victim, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayConstructionMove(game.currentPlayer, church, game.getBoard().getCellAtIndex(1), Direction.DOWN));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.addMove(new PlayUnitMove(game.waitingPlayer, attacker, game.getBoard().getCellAtIndex(4), Direction.UP));
		game.addMove(new PlayCraftMove(game.waitingPlayer, spell, attacker));
		game.addIntResponse(1);
		game.addMove(new EndMainPhaseMove(game.waitingPlayer));
		game.start();
		if(victim.isAlive()){
			return "SUCCESS: Church is working.";
		}
		return "ERROR: Church is not working.";
	}
	
	public static String testShove(){
		TestGame game = new TestGame();
		IdentityCard shover = new Card_RankAndFile(game.currentPlayer);
		IdentityCard shoved = new Card_RankAndFile(game.currentPlayer);
		CraftCard shove = new Card_Shove(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, shover, game.getBoard().getCellAtIndex(1), Direction.DOWN));
		game.addMove(new PlayUnitMove(game.currentPlayer, shoved, game.getBoard().getCellAtIndex(4), Direction.UP));
		game.addMove(new PlayCraftMove(game.currentPlayer, shove, shover));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(shoved.getCell().getIndex() == 16){
			return "SUCCESS: Shove is working.";
		}
		return "ERROR: Shove is not working.";
	}
	
	public static String testFireball(){
		TestGame game = new TestGame();
		IdentityCard attacker = new Card_Wizard(game.currentPlayer);
		CraftCard spell = new Card_Fireball(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, attacker, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayCraftMove(game.currentPlayer, spell, attacker));
		game.addIntResponse(1);
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(!attacker.isAlive()){
			return "SUCCESS: Fireball is working.";
		}
		return "ERROR: Fireball is not working.";
	}
	
	public static String testTorrent(){
		TestGame game = new TestGame();
		IdentityCard attacker = new Card_Wizard(game.currentPlayer);
		CraftCard spell = new Card_Torrent(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, attacker, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayConstructionMove(game.currentPlayer, new Card_Castle(game.currentPlayer), game.getBoard().getCellAtIndex(4), Direction.UP));
		game.addMove(new PlayConstructionMove(game.currentPlayer, new Card_Castle(game.currentPlayer), game.getBoard().getCellAtIndex(5), Direction.UP));
		game.addMove(new PlayConstructionMove(game.currentPlayer, new Card_Castle(game.currentPlayer), game.getBoard().getCellAtIndex(6), Direction.UP));
		game.addMove(new PlayCraftMove(game.currentPlayer, spell, attacker));
		game.addIntResponse(2);
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(game.getBoard().getCellAtIndex(4).isEmpty() && game.getBoard().getCellAtIndex(5).isEmpty() && game.getBoard().getCellAtIndex(6).isEmpty()){
			return "SUCCESS: Torrent is working.";
		}
		return "ERROR: Torrent is not working.";
	}
	
	public static String testWideHeal(){
		TestGame game = new TestGame();
		IdentityCard priest = new Card_Priest(game.currentPlayer);
		IdentityCard toBeHealed1 = new Card_RankAndFile(game.currentPlayer);
		IdentityCard toBeHealed2 = new Card_RankAndFile(game.currentPlayer);
		toBeHealed1.inflictDamage(1);
		toBeHealed2.inflictDamage(1);
		CraftCard spell = new Card_WideHeal(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, priest, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayUnitMove(game.currentPlayer, toBeHealed1, game.getBoard().getCellAtIndex(3), Direction.UP));
		game.addMove(new PlayUnitMove(game.currentPlayer, toBeHealed2, game.getBoard().getCellAtIndex(2), Direction.UP));
		game.addMove(new PlayCraftMove(game.currentPlayer, spell, priest));
		game.addIntResponse(2);
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if((toBeHealed1.currentHP == toBeHealed1.defaultHP) && (toBeHealed2.currentHP == toBeHealed2.defaultHP)){
			return "SUCCESS: Wide Heal is working.";
		}
		return "ERROR: Wide Heal is not working.";
	}
	
	public static String testThunderbolt(){
		TestGame game = new TestGame();
		IdentityCard wizard = new Card_Wizard(game.currentPlayer);
		IdentityCard redKnight = new Card_RedKnight(game.currentPlayer);
		CraftCard spell = new Card_Thunderbolt(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, wizard, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayUnitMove(game.currentPlayer, redKnight, game.getBoard().getCellAtIndex(2), Direction.UP));
		game.addMove(new PlayCraftMove(game.currentPlayer, spell, wizard));
		game.addIntResponse(2);
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(!redKnight.isAlive()){
			return "SUCCESS: Thunderbolt is working.";
		}
		return "ERROR: Thunderbolt is not working.";
	}
	
	public static String testRedKnight(){
		TestGame game = new TestGame();
		IdentityCard redKnight = new Card_RedKnight(game.currentPlayer);
		CraftCard fireball = new Card_Fireball(game.currentPlayer);
		CraftCard thunderbolt = new Card_Thunderbolt(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, redKnight, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(redKnight.canUseCraft(fireball) && !redKnight.canUseCraft(thunderbolt)){
			return "SUCCESS: Red Knight is working.";
		}
		return "ERROR: Red Knight is not working.";
	}
	
	public static String testAssassin(){
		TestGame game = new TestGame();
		game.setTurnCount(2);
		IdentityCard assassin = new Card_Assassin(game.currentPlayer);
		IdentityCard priest = new Card_Priest(game.waitingPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, priest, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.addMove(new PlayUnitMove(game.waitingPlayer, assassin, game.getBoard().getCellAtIndex(4), Direction.UP));
		game.addMove(new AttackMove(game.waitingPlayer, assassin, priest));
		game.addMove(new EndMainPhaseMove(game.waitingPlayer));
		game.start();
		if(!priest.isAlive()){
			return "SUCCESS: Assassin is working.";
		}
		return "ERROR: Assassin is not working.";
	}
	
	public static String testMerchant(){
		TestGame game = new TestGame();
		Player p1 =  game.currentPlayer;
		Player p2 =  game.waitingPlayer;
		IdentityCard merchant = new Card_Merchant(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, merchant, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(p1.getHand().size() > p2.getHand().size()){
			return "SUCCESS: Merchant is working.";
		}
		return "ERROR: Merchant is not working.";
	}
	
	public static String testPriest(){
		TestGame game = new TestGame();
		Player p1 =  game.currentPlayer;
		Player p2 =  game.waitingPlayer;
		IdentityCard priest = new Card_Priest(game.currentPlayer);
		IdentityCard toBeHealed = new Card_RankAndFile(game.currentPlayer);
		toBeHealed.inflictDamage(1);
		game.addMove(new PlayUnitMove(game.currentPlayer, priest, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayUnitMove(game.currentPlayer, toBeHealed, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new ActivateEffectMove(game.currentPlayer, priest));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.addIntResponse(1);
		game.start();
		if(toBeHealed.currentHP == toBeHealed.defaultHP){
			return "SUCCESS: Priest is working.";
		}
		return "ERROR: Priest is not working.";
	}
	
	public static String testNecromancer(){
		TestGame game = new TestGame();
		Player p1 =  game.currentPlayer;
		Player p2 =  game.waitingPlayer;
		IdentityCard necromancer = new Card_Necromancer(game.currentPlayer);
		IdentityCard wizard = new Card_Wizard(game.currentPlayer);
		CraftCard spell = new Card_Fireball(game.currentPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, wizard, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new PlayCraftMove(game.waitingPlayer, spell, wizard));
		game.addIntResponse(1);
		game.addMove(new PlayUnitMove(game.currentPlayer, necromancer, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new ActivateEffectMove(game.currentPlayer, necromancer));
		game.addIntResponse(0);
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.start();
		if(p1.getHand().size() > p2.getHand().size()){
			return "SUCCESS: Necromancer is working.";
		}
		return "ERROR: Necromancer is not working.";
	}
	
	
	public static String testDarkKnight(){
		TestGame game = new TestGame();
		game.setTurnCount(2);
		Player p1 =  game.currentPlayer;
		Player p2 =  game.waitingPlayer;
		IdentityCard darkKnight = new Card_DarkKnight(game.currentPlayer);
		IdentityCard attacker = new Card_RankAndFile(game.waitingPlayer);
		game.addMove(new PlayUnitMove(game.currentPlayer, darkKnight, game.getBoard().getCellAtIndex(1), Direction.UP));
		game.addMove(new EndMainPhaseMove(game.currentPlayer));
		game.addMove(new PlayUnitMove(game.waitingPlayer, attacker, game.getBoard().getCellAtIndex(4), Direction.UP));
		game.addMove(new AttackMove(game.waitingPlayer, attacker, darkKnight));
		game.addMove(new EndMainPhaseMove(game.waitingPlayer));
		game.start();
		if(p1.getHand().size() > p2.getHand().size()){
			return "SUCCESS: Dark Knight is working.";
		}
		return "ERROR: Dark Knight is not working.";
	}
	
}
