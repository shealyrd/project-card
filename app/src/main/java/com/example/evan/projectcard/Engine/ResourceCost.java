package com.example.evan.projectcard.Engine;
import java.io.Serializable;

public class ResourceCost extends ResourceCollection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -272230756005950775L;

	public ResourceCost(int basic, int fire, int water, int lightning){
		addBasic(basic);
		addFire(fire);
		addWater(water);
		addLightning(lightning);
	}

	public ResourceCost() {
		// TODO Auto-generated constructor stub
	}
}
