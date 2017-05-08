package hugant.starclipse_android.building;


import java.io.Serializable;

import hugant.starclipse_android.building.items.House;
import hugant.starclipse_android.common.Timer;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.common.Resources;


/**
 * This is class the parents for buildings:
 * House, Warehouse, ResourcesFactory, StarshipFactory
 * 
 * @author Hugant MD
 *
 * @see hugant.starclipse_android.common.Timer
 * @see hugant.starclipse_android.common.Subject
 * @see hugant.starclipse_android.common.Resources
 * @see House
 */
public abstract class Building implements Serializable {
	private String name = "";
	private String status = "";

	private int descriptionId = -1;
	private int imageId = -1;

	private Timer buildTimer = null;
	private Timer incomeTimer = null;
	
	private Subject residents = null;
	private int level = 0;
	private float k = 1.5f;

	
	private Resources income = null;
	private Resources expenses = null;
	
	private boolean start = false;

//	public View.OnClickListener UpgradeOnClick = new View.OnClickListener() {
//		@Override
//		public void onClick(View e) {
//			try {
//				MainActivity.getRes().subtract(expenses);
//				upgrade();
//			} catch (ArithmeticException ex) {
//
//			}
//		}
//	};

	/**
	 * Begins to build the <b>Building</b>.
	 */
	public void build() {
		if (buildTimer != null) {
			buildTimer.start();
		}
	}
	
	/**
	 * Starts work in the <b>Building</b>.
	 */
	public void startWork() {
		if (incomeTimer != null) {
			incomeTimer.start();
			start = true;
		}
	}
	
	
	/**
	 * Return the <b>Resources</b> which was extracted.
	 * @return income is <b>Resources</b> which was extracted
	 */
	public Resources claim() {
		if (incomeTimer != null) {
			incomeTimer.start();
		}
		return this.income;
	}

	/**
	 * Upgrade the <b>Building</b>, it also increase
	 * maximum number of residents, expenses <b>Resources</b> for next upgrade
	 * and income <b>Resources</b>
	 */
	public void upgrade() {
		this.residents.addToMaxValue("10");
		this.expenses = expenses.multiply("2");
		this.income = income.multiply(level * k + "");
	}
	
	/**
	 * Return name of <b>Building</b>.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the <b>Resources</b> which are necessary for the
	 * construction of this <b>Building</b>.
	 * @return expenses is a <b>Resources</b> which are necessary for the
	 * construction
	 */
	public Resources getExpenses() {
		return expenses;
	}
	
	/**
	 * Return the maximum number of residents.
	 * @return maxResidents is maximum number of residents
	 */
	public String getMaxResidents() {
		return residents.getMaxValue();
	}
	
	/**
	 * Return the current number of residents.
	 * @return residents is number of residents
	 */
	public String getResidents() {
		return residents.getNumber();
	}
	
	/**
	 * Return status of the timer. If timer is work, then 
	 * will return time. Otherwise return <b>String</b> "Start" or "Claim".
	 * @return time or "Start" or "Claim"
	 * 
	 * @see hugant.starclipse_android.common.Timer
	 */
	public String getTimer() {
		if (buildTimer != null && incomeTimer != null) {
			if (((buildTimer.isWork() || incomeTimer.isWork()) || !incomeTimer.isWork()) && start) {
				return incomeTimer.toString();
			}
			return buildTimer.toString();
		}
		return "";
	}

	/**
	 * Return current status of this <b>Building</b>.
	 * @return one of the string: locked, unlocked
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the name of <b>Building</b>.
	 * @param name is name of <b>Building</b>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the build <b>Timer</b> of <b>Building</b>.
	 * @param buildTime is the <b>Timer</b> for which the 
	 * <b>Building</b> will be build.
	 */
	public void setBuildTimer(Timer buildTime) {
		this.buildTimer = buildTime;
	}

	/**
	 * Sets the build <b>Timer</b> of <b>Building</b>.
	 * @param duration is duration of timer
	 * @param phrase is phrase which will return
	 */
	public void setBuildTimer(Long duration, String phrase) {
		this.buildTimer = new Timer(duration, phrase);
	}
	
	/**
	 * Sets the income <b>Timer</b> of <b>Building</b>.
	 * @param incomeTime is the <b>Timer</b> for which will be
	 * received income
	 */
	public void setIncomeTimer(Timer incomeTime) {
		this.incomeTimer = incomeTime;
	}
	
	/**
	 * Sets the build <b>Timer</b> of <b>Building</b>.
	 * @param duration is duration of timer
	 * @param phrase is phrase which will return
	 */
	public void setIncomeTimer(Long duration, String phrase) {
		this.incomeTimer = new Timer(duration, phrase);
	}

	/**
	 * Sets the number of residents.
	 * @param currentResidents is number of residents
	 */
	public void setResidents(String currentResidents, String maximalResidents) {
		this.residents = new Subject(Subject.RESIDENTS, currentResidents, maximalResidents);
	}

	/**
	 * Sets the income <b>Resources</b>.
	 * @param income is <b>Resources</b> which will be obtained
	 */
	public void setIncome(Resources income) {
		this.income = income;
	}

	/**
	 * Sets the expenses which are necessary for the
	 * construction of this <b>Building</b>.
	 * @param expenses is <b>Resources</b> which are necessary for the
	 * construction of this <b>Building</b>
	 */
	public void setExpenses(Resources expenses) {
		this.expenses = expenses;
	}

	/**
	 * Sets the id of the description for this <b>Building</b>.
	 * @param descriptionId
	 */
	public void setDescription(int descriptionId) {
		this.descriptionId = descriptionId;
	}

	/**
	 * Return id of the description of this <b>Building</b>.
	 * @return
	 */
	public int getDescription() {
		if (descriptionId == -1) {
			// TODO: Add to R building_description_not_found
//			return R.string.building_description_not_found;
		}
		return descriptionId;
	}

	/**
	 * Sets the id of the image for this <b>Building</b>.
	 * @param imageId
	 */
	public void setImage(int imageId) {
		this.imageId = imageId;
	}

	/**
	 * Return id of the description of this <b>Building</b>.
	 * @return
	 */
	public int getImage() {
		return imageId;
	}
}