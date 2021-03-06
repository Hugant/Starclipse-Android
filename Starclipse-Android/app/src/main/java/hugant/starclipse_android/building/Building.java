package hugant.starclipse_android.building;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.lang.SerializationUtils;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.items.House;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.common.Timer;


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
public class Building implements Serializable, Cloneable {
	private int description = -1;
	private int image = -1;
	private int name = -1;

	private Timer buildTimer = null;
	private Timer incomeTimer = null;
	
	private Subject residents = null;
	private Subject lastResidents = null;
	private int level = 1;
	private float k = 1.5f;

	
	private Resources income = null;
	private Resources expenses = null;
	
	private boolean start = false;

	public Building() {}

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

			try {
				lastResidents = residents.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Return the <b>Resources</b> which was extracted.
	 * @return income is <b>Resources</b> which was extracted
	 */
	public Resources claim() {
		Resources cloneIncome = null;

		if (incomeTimer != null) {
			incomeTimer.start();
		}

		try {
			cloneIncome = income.clone().multiply(lastResidents.getValue());
			lastResidents = residents.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return cloneIncome;
	}

	/**
	 * Upgrade the <b>Building</b>, it also increase
	 * maximum number of residents, expenses <b>Resources</b> for next upgrade
	 * and income <b>Resources</b>
	 */
	public Building upgrade() {
		this.expenses = expenses.multiply("1.2");
		this.income = income.multiply(level * k + "");
		this.level++;
		return this;
	}

	public Resources getIncome() {
		return income;
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
	 * Return the <b>Subject</b> of residents.
	 * @return residents is number of residents
	 */
	public Subject getResidents() {
		return residents;
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
	 * Sets the name of <b>Building</b>.
	 * @param nameId is name of <b>Building</b>
	 */
	public void setName(int nameId) {
		this.name = nameId;
	}

	/**
	 * Return name of <b>Building</b>.
	 * @return
	 */
	public int getName() {
		if (name == -1) {
			return R.string.name_not_found;
		}
		return name;
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
		this.description = descriptionId;
	}

	/**
	 * Return id of the description of this <b>Building</b>.
	 * @return
	 */
	public int getDescription() {
		if (description == -1) {
			// TODO: Add to R building_description_not_found
			return R.string.description_not_found;
		}
		return description;
	}

	/**
	 * Sets the id of the image for this <b>Building</b>.
	 * @param imageId
	 */
	public void setImage(int imageId) {
		this.image = imageId;
	}

	/**
	 * Return id of the description of this <b>Building</b>.
	 * @return
	 */
	public int getImage() {
		if (image == -1) {
			return R.drawable.ic_dashboard_black_24dp;
		}
		return image;
	}

	public void addResidents(Resources resources, String amount) {
		if (residents.compareTo(new Subject(residents.getMaxValue())) == -1) {
			if (resources.canSubtract(new Subject(Subject.RESIDENTS, amount))) {
				resources.subtract(new Subject(Subject.RESIDENTS, amount));
				residents.add(new Subject(amount));
			} else {
				throw new ArithmeticException("Insufficient number of residents");
			}
		} else {
			throw new ArithmeticException("Number of residents equals maximum");
		}
	}

	public void takeResidents(Resources resources, String amount) {
		if (residents.compareTo(new Subject("0")) == 1) {
			residents.subtract(new Subject(amount));
			resources.add(new Subject(Subject.RESIDENTS, amount));
		} else {
			throw new ArithmeticException("Number of residents equals minimum");
		}
	}

	public static void fillList(Context context, LinearLayout list, int item, Building building, String type) {
		list.removeAllViews();

		building = building.clone();

		switch (type.toLowerCase()) {
			case "income":
				for (Subject i : building.getIncome()
						.multiply(building.getResidents().getValue()).getSubjects()) {
					View linearChild = View.inflate(context, item, null);

					((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
					((TextView) linearChild.findViewById(R.id.values))
							.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

					list.addView(linearChild);
				}
				return;

			case "upgraded income":
				for (Subject i : building.upgrade().getIncome()
						.multiply(building.getResidents().getValue()).getSubjects()) {
					View linearChild = View.inflate(context, item, null);

					((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
					((TextView) linearChild.findViewById(R.id.values))
							.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

					list.addView(linearChild);
				}
				return;

			case "expenses":
				for (Subject i : building.getExpenses().getSubjects()) {
					View linearChild = View.inflate(context, item, null);

					((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
					((TextView) linearChild.findViewById(R.id.values))
							.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

					list.addView(linearChild);
				}
				return;

			default:
				throw new UnsupportedOperationException("");
		}

	}

	@Override
	public Building clone() {
		try {
			super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return (Building) SerializationUtils.deserialize(SerializationUtils.serialize(this));
	}
}
