package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FeatureCollection implements Iterable<Feature>, Serializable{
	    HashMap<String, Feature> features = new HashMap<String, Feature>();
	    
	    public void put(String name, Feature input){
	        features.put(name, input);
	    }
	    
	    public void put(String name){
	    	features.put(name, new Feature());
	    }
	    
	    public void setValue(String name, double input){
	    	features.get(name).setValue(input);
	    }
	    
	    public void setWeight(String name, double input){
	    	features.get(name).setWeight(input);
	    }
	        
	    public double[] getWeights(){
	        double[] result = new double[features.size()];
	        for(int i = 0; i < features.size(); i++){
	            result[i] = features.get(i).weight;
	        }
	        return result;
	    }
	    
	    public void randomizeWeights(double lowerBound, double upperBound){
	        for(Feature feature: features.values()){
	            feature.randomizeWeight(lowerBound, upperBound);
	        }
	    }

		@Override
		public Iterator<Feature> iterator() {
			return features.values().iterator();
		}

		public int size() {
			return features.size();
		}
		
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			for(Feature feature: this){
				builder.append(feature.weight + ", ");
			}
			builder.append("}");
			return builder.toString();
		}
}

