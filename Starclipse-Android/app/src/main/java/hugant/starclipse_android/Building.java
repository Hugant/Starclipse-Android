package hugant.starclipse_android;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hugant.starclipse_android.building.House;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Timer;
import hugant.starclipse_android.common.Subject;

/**
 * This is class the parents for buildings:
 * House, Warehouse, ResourcesFactory, StarshipFactory
 * 
 * @author Hugant MD
 *
 * @see hugant.starclipse_android.common.Timer
 * @see hugant.starclipse_android.common.Subject
 * @see hugant.starclipse_android.common.Resources
 * @see hugant.starclipse_android.building.House
 */
public abstract class Building {
	private String name = "";
	private String status = "";
	
	private Timer buildTimer = null;
	private Timer incomeTimer = null;
	
	private Subject residents = null;
	private int level = 0;
	private float k = 0.0f;
	
	private Resources income = null;
	private Resources expenses = null;
	
	private boolean start = false;

	public View.OnClickListener OnClick = new View.OnClickListener() {
		@Override
		public void onClick(View e) {
			try {
				if (getTimer().equals("Start")) {
					startWork();
				} else if (getTimer().equals("Claim")) {
					MainActivity.GLOBAL_RESOURCES.add(claim());
				}
			} catch (UnsupportedOperationException ex) {
				build();
			}
		}
	};

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
	
	public void upgrade() {
		this.residents.addToMaxValue("");
		this.expenses = expenses.multiply("");
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
		android.util.Log.i("House", "Used setIncome");
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
}
