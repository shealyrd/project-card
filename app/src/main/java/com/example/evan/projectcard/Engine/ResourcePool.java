package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class ResourcePool extends ResourceCollection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9091366566898333337L;
	Player owner;
	
	public boolean canPayResourceCost(ResourceCost cost){
		if(getBasicCount() < cost.getBasicCount()){
			return false;
		}
		if(getFireCount() < cost.getFireCount()){
			return false;
		}
		if(getWaterCount() < cost.getWaterCount()){
			return false;
		}
		if(getLightningCount() < cost.getLightningCount()){
			return false;
		}
		return true;
	}
	
	public void deductCost(ResourceCost cost){
		setBasic(getBasicCount() - cost.getBasicCount());
		setFire(getFireCount() - cost.getFireCount());
		setWater(getWaterCount() - cost.getWaterCount());
		setLightning(getLightningCount() - cost.getLightningCount());
	}
}
