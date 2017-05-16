package hugant.starclipse_android.building.items;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.common.ScaleNumber;

/**
 * TODO: write javadoc
 * @author Hugant MD
 *
 */
public class Warehouse extends Building {
	
	private ScaleNumber volume = new ScaleNumber("1K");
	private Resources store;
	
	public Warehouse(ScaleNumber volume, String... cells) {
		this.store = new Resources(volume == null ? this.volume : volume, cells);
		this.store.add(new Subject(Subject.RESIDENTS, "0"));
		super.setName(R.string.building_warehouse_name);
		super.setResidents("10", "70");
		super.setImage(R.drawable.buildings);
		super.setExpenses(new Resources(Subject.GOLD, "1K"));
	}

	public Resources getStore() {
		return store;
	}
}
