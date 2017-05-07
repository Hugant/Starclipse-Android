package hugant.starclipse_android.building.items;

import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.common.ScaleNumber;

/**
 * 
 * @author Hugant MD
 *
 */
public class Warehouse extends Building {
	
	private ScaleNumber volume = new ScaleNumber("1K");// the amount of resources per unit
	private Resources store;
	
	public Warehouse(ScaleNumber volume, String... cells) {
		this.store = new Resources(volume == null ? this.volume : volume, cells);
		super.setName("Warehouse");
		super.setResidents("10", "70");
		super.setExpenses(new Resources());
	}
	
	public void addToStore(Subject sub) {
		store.add(sub);
	}
	
	public void addToStore(Resources res) {
		store.add(res);
	}
	
	public Subject takeFromStore(Subject sub) {
		store.subtract(sub);
		return sub;
	}
	
	public Resources takeFromStore(Resources res) {
		store.subtract(res);
		return res;
	}
	
	public void fill() {
		store.fill();
	}

	public Resources getStore() {
		return store;
	}
}
