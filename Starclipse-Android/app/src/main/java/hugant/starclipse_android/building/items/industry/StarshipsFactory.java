package hugant.starclipse_android.building.items.industry;

import java.util.ArrayList;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Timer;
import hugant.starclipse_android.common.Resources;

public class StarshipsFactory extends Building {
			
	public StarshipsFactory() {
		super.setName(R.string.building_starships_factory_name);
		super.setDescription(R.string.building_starships_factory_description);
		super.setImage(R.drawable.building_starship_factory_150dp);
		super.setBuildTimer(30 * Timer.DAY, "Start");
		super.setIncomeTimer(10 * Timer.DAY, "Claim");
		super.setExpenses(new Resources());
	}


	@Deprecated
	@Override
	public Resources claim() {
		return super.claim();
	}


//	public Starship createStarship(Starship starship) {
//		ret
//	}
}
