package hugant.starclipse_android.Infrastructure;

import android.widget.Button;

import java.util.ArrayList;

import hugant.starclipse_android.Building;
import hugant.starclipse_android.building.*;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.ScaleNumber;
import hugant.starclipse_android.common.Subject;

public class Infrastructure {
	private ArrayList<Building> buildings;
	
	public Infrastructure() {
		buildings = new ArrayList<Building>();
		buildings.add(new Warehouse(null, Subject.ALL_RESOURCES));
	}
	
	public Building add(Building building) {
		buildings.add(building);
		return building;
	}

	public ArrayList<Building> getBuildings() {
        return buildings;
	}

	public Resources getResources() {
		// Always first element this is warehouse, bug or feature
		return ((Warehouse) buildings.get(0)).getStore();
	}
}
