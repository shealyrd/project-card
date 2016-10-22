package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class ResourceCollection  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1245035209861161892L;
	int basics = 0;
	int fires = 0;
	int waters = 0;
	int lightnings = 0;
	
	public void addBasic(int i){
		basics = basics + i;
	}
	public void addFire(int i){
		fires = fires + i;
	}
	public void addWater(int i){
		waters = waters + i;
	}
	public void addLightning(int i){
		lightnings = lightnings + i;
	}
	public void setBasic(int i){
		basics = i;
	}
	public void setFire(int i){
		fires = i;
	}
	public void setWater(int i){
		waters = i;
	}
	public void setLightning(int i){
		lightnings = i;
	}
	public int getBasicCount(){
		return basics;
	}
	public int getFireCount(){
		return fires;
	}
	public int getWaterCount(){
		return waters;
	}
	public int getLightningCount(){
		return lightnings;
	}
	public int getSum(){
		return basics + fires + waters + lightnings;
	}
	
	public String toString(){
		return basics + ", " + fires + ", " + waters + ", " + lightnings;
	}
}
