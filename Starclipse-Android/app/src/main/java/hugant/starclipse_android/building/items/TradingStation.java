package hugant.starclipse_android.building.items;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Resources;

public class TradingStation extends Building {

	public TradingStation() {
		super.setName(R.string.building_trading_station_name);
		super.setDescription(R.string.building_trading_station_description);
		super.setImage(R.drawable.building_trade_station_150dp);
		super.setExpenses(new Resources());
		super.setResidents("0", "10");
	}

}
