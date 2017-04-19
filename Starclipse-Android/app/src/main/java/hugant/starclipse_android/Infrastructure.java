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
	
	public Building add(Building building) {
		infrastructure.add(building);
		return building;
	}

	public ArrayList<Building> getInfrastructure() {
        return infrastructure;
	}
}
