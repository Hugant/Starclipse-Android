package hugant.starclipse_android.building.items.industry;

import java.util.ArrayList;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Timer;
import hugant.starclipse_android.common.Resources;

public class StarshipsFactory extends Building {
	private ArrayList<Starship> starships;
	private Starship starship = null;
	private Resources expenses = null;
			
	public StarshipsFactory(ArrayList<Starship> starships) {
		super.setName(R.string.building_starships_factory_name);
		super.setDescription(R.string.building_starships_factory_description);
		super.setImage(R.drawable.building_starship_factory_150dp);
		super.setBuildTimer(30 * Timer.DAY, "Start");
		super.setIncomeTimer(10 * Timer.DAY, "Claim");
		super.setResidents("0", "100");
		super.setExpenses(new Resources());

		this.starships = starships;
	}
	
	
	
	public Starship getStarship() {
		return this.starship;
	}
}
