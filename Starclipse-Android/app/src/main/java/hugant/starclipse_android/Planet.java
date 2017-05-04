package hugant.starclipse_android;

import hugant.starclipse_android.Infrastructure.Infrastructure;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.building.House;

public class Planet {
	private Resources resources;
	private Resources effect;
	
	private Infrastructure infrastructure;
	private Subject freeResidents;
	
	private String name;
	private boolean defend = false;
	
	
	public Planet(String name, Resources effect) {
		this.name = name;
		this.effect = effect;
		this.infrastructure = new Infrastructure();
		this.resources = infrastructure.getResources();
	}

	public boolean isDefend() {
		return defend;
	}

	public Resources getResources() {
		return resources;//may be clone
	}

	public Subject getFreeResidents() {
		return freeResidents;//may be clone
	}

	public String getName() {
		return name;//may be clone
	}

	public Infrastructure getInfrastructure() {
		return infrastructure;
	}
}
