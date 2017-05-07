package hugant.starclipse_android.building.items.industry;

import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Timer;
import hugant.starclipse_android.common.Resources;

public class StarshipsFactory extends Building {
	private String name = "Starships Factory";
	private Starship starship = null;
	private int manpower = 0;
	private Resources expenses = null;
			
	public StarshipsFactory() {
		super.setName("Starships Factory");
		super.setBuildTimer(30 * Timer.DAY, "Start");
		super.setIncomeTimer(10 * Timer.DAY, "Claim");
		super.setResidents("0", "");
		super.setExpenses(new Resources());
	}
	
	
	
	public Starship getStarship() {
		return this.starship;
	}
	
	public String getName() {
		return this.name;
	}
}
