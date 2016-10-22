package com.example.evan.projectcard.Engine;
import java.util.ArrayList;

public class CounterAttackRightManager {
	ArrayList<CounterAttackRight> claims = new ArrayList<CounterAttackRight>();
	
	public void addRight(CounterAttackRight right){
		claims.add(right);
	}
	
	public void removeRight(CounterAttackRight right){
		claims.remove(right);
	}
	
	public CounterAttackRight getCurrentCounterAttackRight(){
		for(CounterAttackRight claim: claims){
			if(claim == CounterAttackRight.ALL){
				return CounterAttackRight.ALL;
			}
		}
		return CounterAttackRight.FRONT;
	}
}

