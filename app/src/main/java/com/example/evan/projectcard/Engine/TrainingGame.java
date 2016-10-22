package com.example.evan.projectcard.Engine;

public class TrainingGame extends ComVsComGame {
	private static final long serialVersionUID = 2626616542243329361L;
	int reward;
	
	@Override
	public void start(){
		super.start();
		reward = calculatateReward();
	}

	private int calculatateReward() {
		int result = 0;
		result = result + turnNum;
		if(!draw){
			if(winner.name == "p1"){
				result = result * (-1);
				result = result + 100;
			}
			else{
				result = result - 100;
			}
		}
		return result;
	}
	
	public int getReward(){
		return reward;
	}
}
