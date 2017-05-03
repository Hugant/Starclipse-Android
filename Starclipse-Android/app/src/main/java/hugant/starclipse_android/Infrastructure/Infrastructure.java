package hugant.starclipse_android.Infrastructure;

import android.widget.Button;

import java.util.ArrayList;

import hugant.starclipse_android.Building;
import hugant.starclipse_android.building.*;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.ScaleNumber;
import hugant.starclipse_android.common.Subject;

public class Infrastructure {
	private ArrayList<Building> infrastructure;
	
	public Infrastructure() {
		infrastructure = new ArrayList<Building>();
		infrastructure.add(new Warehouse(null, Subject.ALL_RESOURCES));
	}
	
	public Building add(Building building) {
		infrastructure.add(building);
		return building;
	}

	public ArrayList<Building> getInfrastructure() {
        return infrastructure;
	}
}
