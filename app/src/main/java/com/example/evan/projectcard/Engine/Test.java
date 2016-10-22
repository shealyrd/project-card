package com.example.evan.projectcard.Engine;

public class Test {

	public static void main(String[] args) {
		//new Game().start();
		Log.setTestingMode();
		Log.supAdd("start");
		FeatureWeightingTrainer.train(new TempLearner(), 5);
		/*boolean flag = false;
		int count = 0;
		int p1wins = 0;
		int p2wins = 0;
		while(count < 100){
			ComVsComGame game = new ComVsComGame();
			game.start();
			if(!game.draw){
				if(game.winner == game.players[0]){
					p1wins++;
				}
				else{
					p2wins++;
				}
				Log.supAdd("game " + count + " end: " + game.winner.name);
			}
			else{
				Log.supAdd("game " + count + " end: draw");
			}
			count++;
		}
		Log.supAdd("P1 wins: " + p1wins);
		Log.supAdd("P2 wins: " + p2wins);
		/*
		Board newBoard = null;
		Log.add("CLONED BOARD");
		try {
			newBoard = ((Game) ObjectCloner.deepCopy(game)).getBoard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newBoard.p1Hand.add(new Card_RankAndFile(new Player(game)));
		Log.add(newBoard.toString());
		Board appliedBoard = newBoard.applyMove(new PlayUnitMove(new Player(newBoard.game), new Card_RankAndFile(new Player(game)), newBoard.getCellAtIndex(1), Direction.DOWN));
		Log.add(appliedBoard.toString());
		*/
		//test();
	}
	
	public static void test(){
		Log.supAdd(TestingSuite.testAncientSeal());
		Log.supAdd(TestingSuite.testFireball());
		Log.supAdd(TestingSuite.testAssassin());
		Log.supAdd(TestingSuite.testBigassAnimeSword());
		Log.supAdd(TestingSuite.testButterflyBlade());
		Log.supAdd(TestingSuite.testCastle());
		Log.supAdd(TestingSuite.testChurch());
		Log.supAdd(TestingSuite.testDarkKnight());
		Log.supAdd(TestingSuite.testGrasshopperGloves());
		Log.supAdd(TestingSuite.testMerchant());
		Log.supAdd(TestingSuite.testPriest());
		Log.supAdd(TestingSuite.testNecromancer());
		Log.supAdd(TestingSuite.testThunderbolt());
		Log.supAdd(TestingSuite.testRedKnight());
		Log.supAdd(TestingSuite.testRingOfConstitution());
		Log.supAdd(TestingSuite.testShove());
		Log.supAdd(TestingSuite.testTorrent());
		Log.supAdd(TestingSuite.testWideHeal());
		Log.supAdd(TestingSuite.testGroveOfRite());
	}
	
	

}
