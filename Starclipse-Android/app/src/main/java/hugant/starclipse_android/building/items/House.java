package hugant.starclipse_android.building.items;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.common.Timer;

/**
 * This class implements an object <b>House</b> that has three types:
 * small, average, big. Depending on the type of <b>House</b> 
 * after a certain period of time brings income: residents, money.
 * Also depending on the type of <b>House</b> contains a certain 
 * number of residents.
 * 
 * @author Hugant MD
 * 
 * @see Building
 *
 */
public class House extends Building {
	/**
	 * Create a <b>House</b> where the parameters specified a type
	 * of <b>House</b>.
	 * @param type is type of <b>House</b>
	 */
	public House(String type) {
		switch (type.toLowerCase()) {
			case "big":
				super.setResidents("0", "100");
				super.setName(R.string.building_big_house_name);
				super.setBuildTimer(Timer.MINUTE * 30, "Start");
				super.setIncomeTimer(Timer.SECOND * 20, "Claim");
				super.setDescription(R.string.building_big_house_description);
				super.setImage(R.drawable.building_big_house_150dp);
				super.setIncome(new Resources(Subject.RESIDENTS, "10", Subject.MONEY, "100"));
				super.setExpenses(new Resources(Subject.STONE,  "100",
											  	Subject.TREE,   "50", 
											  	Subject.MONEY,  "1000", 
											  	Subject.IRON,   "10",  
											  	Subject.WATER,  "10", 
											  	Subject.ENERGY, "10"  ));
				return;
				
			case "average":
				super.setResidents("0", "50");
				super.setName(R.string.building_average_house_name);
				super.setBuildTimer(Timer.MINUTE * 5, "Start");
				super.setIncomeTimer(Timer.SECOND * 20, "Claim");
				super.setDescription(R.string.building_average_house_description);
				super.setImage(R.drawable.building_average_house_150dp);
				super.setIncome(new Resources(Subject.RESIDENTS, "5", Subject.MONEY, "50"));
				super.setExpenses(new Resources(Subject.STONE,  "50", 
											  	Subject.TREE,   "25", 
											  	Subject.MONEY,  "500",
											  	Subject.IRON,   "5" , 
											  	Subject.WATER,  "5" , 
											  	Subject.ENERGY, "5"  ));	
				return;
				
			case "small":
				super.setResidents("0", "10");
				super.setName(R.string.building_small_house_name);
				super.setBuildTimer(Timer.SECOND * 10, "Start");
				super.setIncomeTimer(Timer.SECOND * 5, "Claim");
				super.setDescription(R.string.building_small_house_description);
				super.setImage(R.drawable.building_small_house_150dp);
				super.setIncome(new Resources(Subject.RESIDENTS, "2", Subject.MONEY, "25"));
				super.setExpenses(new Resources(Subject.STONE,  "12", 
												Subject.TREE,   "6", 
											  	Subject.MONEY,  "125",
											  	Subject.IRON,   "1" , 
											  	Subject.WATER,  "1", 
											  	Subject.ENERGY, "1"  ));

		}
	}
}
