package hugant.starclipse_android.infrastructure;

import java.util.ArrayList;

import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.building.items.House;
import hugant.starclipse_android.building.items.TradingStation;
import hugant.starclipse_android.building.items.Warehouse;
import hugant.starclipse_android.building.items.industry.ResourcesFactory;
import hugant.starclipse_android.building.items.industry.StarshipsFactory;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

public class Infrastructure {
	private ArrayList<Building> buildings;
	
	public Infrastructure() {
		buildings = new ArrayList<>();
		buildings.add(new Warehouse(null, Subject.ALL_RESOURCES));
		buildings.add(new House("small"));
		buildings.add(new House("average"));
		buildings.add(new House("big"));
		buildings.add(new TradingStation(getResources()));
		buildings.add(new StarshipsFactory());
		buildings.add(new ResourcesFactory());

		getResources().add(new Resources(Subject.RESIDENTS, "10"));
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
