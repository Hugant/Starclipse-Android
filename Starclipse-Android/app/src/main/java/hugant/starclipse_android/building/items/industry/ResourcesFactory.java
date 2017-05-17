package hugant.starclipse_android.building.items.industry;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Timer;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.ScaleNumber;


// TODO: remove extends serializable
public class ResourcesFactory extends Building {
	private static final ScaleNumber INCOME_PER_RESIDENT = new ScaleNumber("10");
	
	public ResourcesFactory(String type) {
		Resources expenses = new Resources();
		super.setResidents("0", "1K");
		
		switch (type) {
			case Subject.TREE:
				expenses.add(new Resources(	Subject.SOIL, 	"0",
											Subject.WATER, 	"0",
											Subject.OXYGEN, "0",
											Subject.MONEY, 	"0"));
				super.setIncome(new Resources(
					Subject.TREE, new ScaleNumber(super.getResidents().getValue()).multiply(INCOME_PER_RESIDENT).toString()));
				break;
			
			case Subject.STONE:
				expenses.add(new Resources(	Subject.SOIL,	"0",
											Subject.TREE,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.STONE, new ScaleNumber(super.getResidents().getValue()).multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.COAL:
				expenses.add(new Resources(	Subject.STONE,	"0",
											Subject.WATER,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.COAL, new ScaleNumber(super.getResidents().getValue()).multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.IRON:
				expenses.add(new Resources(	Subject.STONE,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.IRON, new ScaleNumber(super.getResidents().getValue()).multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.GOLD:
				expenses.add(new Resources( Subject.STONE,	"0",
											Subject.IRON,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.GOLD, new ScaleNumber(super.getResidents().getValue())
						.multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.ENERGY:
				expenses.add(new Resources(	Subject.OXYGEN,	"0",
											Subject.WATER,	"0",
											Subject.FOOD,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.ENERGY, new ScaleNumber(super.getResidents().getValue())
						.multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.WATER:
				expenses.add(new Resources(	Subject.OXYGEN,	"0",
											Subject.SOIL,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.WATER, new ScaleNumber(super.getResidents().getValue())
						.multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.OXYGEN:
				expenses.add(new Resources(	Subject.WATER,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.OXYGEN, new ScaleNumber(super.getResidents().getValue())
						.multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.FOOD:
				expenses.add(new Resources(	Subject.SOIL,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.FOOD, new ScaleNumber(super.getResidents().getValue())
						.multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			case Subject.SOIL:
				expenses.add(new Resources(	Subject.STONE, 	"0",
											Subject.WATER,	"0",
											Subject.OXYGEN,	"0",
											Subject.MONEY,	"0"));
				super.setIncome(new Resources(
						Subject.SOIL, new ScaleNumber(super.getResidents().getValue())
						.multiply(INCOME_PER_RESIDENT).toString()));
				break;
				
			default:
				throw new IllegalArgumentException("Such a resource does not exist");
		}
		
		super.setName(R.string.building_resources_factory_name);
		super.setDescription(R.string.building_resources_factory_description);
		super.setImage(R.drawable.building_resources_factory_150dp);
		super.setBuildTimer(Timer.DAY * 4, "Start");
		super.setIncomeTimer(Timer.DAY, "Claim");

		super.setExpenses(expenses);
	}
}
