package hugant.starclipse_android;

import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.building.House;

public class Planet {
	private Resources resources;
	private Resources effect;
	
	//private Infrastructure infrastructure;
	
	private Building[] infrastructure = new Building[5];
	private Subject residents;
	
	private String name;
	private boolean defend = false;
	
	
	public Planet(String name, Resources effect) {
		this.name = name;
		this.effect = effect;
		//this.infrastructure[0] = new House("Small House");
	}
}
