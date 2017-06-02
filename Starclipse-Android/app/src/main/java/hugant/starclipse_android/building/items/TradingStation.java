package hugant.starclipse_android.building.items;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.common.Resources;

public class TradingStation extends Building {

	private Resources resources;
	private Resources offer;
	private Resources price;
	private int amountOfTrades = 0;
	private int status = 0;

	public TradingStation(Resources resources) {
		this.resources = resources;
		super.setName(R.string.building_trading_station_name);
		super.setDescription(R.string.building_trading_station_description);
		super.setImage(R.drawable.building_trade_station_150dp);
		super.setExpenses(new Resources());
		super.setResidents("0", "10");
	}

	public void setOffer(Resources offer) {
		this.offer = offer;
	}

	public Resources getOffer() {
		return offer;
	}

	public void setPrice(Resources price) {
		this.price = price;
	}

	public Resources getPrice() {
		return price;
	}

	public void trade() {
		amountOfTrades++;
	}

	public boolean canTrade() {
		// if resources - offer < 0
		return false;
	}

	@Override
	public Resources claim() {
//		resources.plus(offer * amountOFTrades);
		return null;
	}

	public int getStatus() {
		if (status == -1) {
			//return build
		} else if (status == 0) {
			// return no trades
		} else if (status > 0) {
			//return number of trades
		}
		return 0;
	}
}
