package com.example.evan.projectcard.Engine;

public class TempLearner implements Learner{

	@Override
	public FeatureCollection getFeatures() {
		return UntrainedPolicy.getFeatures();
	}

	@Override
	public float getReward() {
		int result = 0;
		int epochNum = 10;
		int count = 0;
		while(count < epochNum){
			Log.supAdd("Game " + (count + 1));
			TrainingGame game = new TrainingGame();
			game.start();
			result = result + game.getReward();
			if(!game.draw){
				Log.supAdd("game " + count + " end: " + game.winner.name);
			}
			else{
				Log.supAdd("game " + count + " end: draw");
			}
			count++;
			System.gc();
		}
		return result/epochNum;
	}
	
}
