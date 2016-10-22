package com.example.evan.projectcard.Engine;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Feature {
	double value;
	double weight;
	
	public Feature(int value, int weight){
		this.value = value;
		this.weight = weight;
	}

	public Feature(double weight){
		this.weight = weight;
	}
	
	public Feature(){
		value = 0;
		weight = 0;
	}
	
	public double calculate(){
		return value * weight;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public void setWeight(double weight){
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.weight = Double.parseDouble(df.format(weight));
	}
	
	public static double calculate(Feature...features){
		int result = 0;
		for(Feature feature: features){
			result += feature.calculate();
		}
		return result;
	}
	
	public void randomizeWeight(double lowerBound, double upperBound){
        setWeight(Math.random() * (upperBound - lowerBound) + lowerBound);
    }
}
