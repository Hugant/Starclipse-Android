package hugant.starclipse_android;

import java.util.ArrayList;
import hugant.starclipse_android.building.*;
import hugant.starclipse_android.common.ScaleNumber;
import hugant.starclipse_android.common.Subject;

public class Infrastructure {
	private ArrayList<Building> infrastructure;
	
	public Infrastructure() {
		infrastructure = new ArrayList<Building>();
	}
	
	public Building addBuilding(Building building) {
		this.infrastructure.add(building);
		return building;
	}
}
