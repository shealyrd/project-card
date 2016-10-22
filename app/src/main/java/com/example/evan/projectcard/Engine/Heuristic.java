package com.example.evan.projectcard.Engine;

public class Heuristic {
	double value;
	boolean isMinimum;
	
	public Heuristic(double result){
		value = result;
	}
	
	public Heuristic(){}
	
	public boolean isBetterThan(Heuristic arg){
		if(isMinimum){
			return false;
		}
		if(!isMinimum && arg.isMinimum){
			return true;
		}
		if(value > arg.value){
			return true;
		}
		return false;
	}
	
	public static Heuristic getMinimum(){
		Heuristic result = new Heuristic();
		result.isMinimum = true;
		return result;
	}
	
}
