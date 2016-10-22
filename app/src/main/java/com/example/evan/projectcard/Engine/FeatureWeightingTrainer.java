package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;

public class FeatureWeightingTrainer {

	    public static void train(Learner learner, int numOfEpochs){
	        FeatureCollection features = learner.getFeatures();
	        //LearningVector result = new LearningVector(features.size());
	        int count = 0;
	        while(count < numOfEpochs){
	        	Log.supAdd("Epoch " + (count + 1));
	            features.randomizeWeights(0, 1);
	            float reward = learner.getReward();
	            //LearningVector toAddBy = new LearningVector(features.getWeights());
	            //toAddBy.scale(reward);
	            //result.add(toAddBy);
	            //ResultWriter.writeResult(features, reward);
	            count++;
	        }
	        //return result.asArray();
	    }
}
