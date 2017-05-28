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
	private float kof = 1.2f;
	private int levelW = 1;
	
	public Warehouse(ScaleNumber volume, String... cells) {
		this.store = new Resources((volume == null ? this.volume : volume), cells);
		this.store.add(new Subject(Subject.RESIDENTS, "0"));
		super.setName(R.string.building_warehouse_name);
		super.setImage(R.drawable.buildings);
		super.setExpenses(new Resources(Subject.MONEY, "1K"));
	}

	public Resources getStore() {
		return store;
	}

	@Override
	public Warehouse upgrade() {
		for (Subject i : store.getSubjects()) {
			try {
				i.setMaxValue(new Subject(i.getMaxValue() == null ? "0" : i.getMaxValue()).multiply(new Subject(levelW * kof + "")));
			} catch (NullPointerException e) {}
		}
		return this;
	}

	@Override
	public Warehouse clone() {
		return (Warehouse) super.clone();
	}
}
