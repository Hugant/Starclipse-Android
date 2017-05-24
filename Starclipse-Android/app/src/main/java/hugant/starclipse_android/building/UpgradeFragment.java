package hugant.starclipse_android.building;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

import hugant.starclipse_android.R;
import hugant.starclipse_android.building.items.Warehouse;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

/**
 *
 * Created by Hugant on 20.05.2017.
 */

public class UpgradeFragment extends Fragment {

	private Building building;
	private Resources resources;

	public UpgradeFragment(Building building, Resources resources) {
		this.building = building;
		this.resources = resources;
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_building_upgrade, container, false);

		final LinearLayout currentResourcesList =
				(LinearLayout) view.findViewById(R.id.currentResourcesList);
		final LinearLayout upgradedResourcesList =
				(LinearLayout) view.findViewById(R.id.upgradedResourcesList);
		final LinearLayout expensesResourcesList =
				(LinearLayout) view.findViewById(R.id.expensesResourcesList);
		final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);

		((TextView) view.findViewById(R.id.buildingName)).setText(building.getName());
		((TextView) view.findViewById(R.id.titleCurrent)).setText(R.string.title_current_capacity);
		((TextView) view.findViewById(R.id.titleUpgraded)).setText(R.string.title_upgraded_capacity);

		if (building.getName() == R.string.building_warehouse_name) {
			final Warehouse warehouse = (Warehouse) building;

			for (Subject i : warehouse.clone().getStore().getSubjects()) {
				if (!i.getType().equals(Subject.RESIDENTS)) {
					View linearChild = inflater.inflate(R.layout.resources_item_minimal, currentResourcesList, false);

					((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
					((TextView) linearChild.findViewById(R.id.values)).setText(i.getMaxValue());

					currentResourcesList.addView(linearChild);
				}
			}

			for (Subject i : warehouse.clone().upgrade().getStore().getSubjects()) {
				if (!i.getType().equals(Subject.RESIDENTS)) {
					View linearChild = inflater.inflate(R.layout.resources_item_minimal, upgradedResourcesList, false);

					((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
					((TextView) linearChild.findViewById(R.id.values)).setText(i.getMaxValue());

					upgradedResourcesList.addView(linearChild);
				}
			}

			for (Subject i : warehouse.clone().getExpenses().getSubjects()) {
				View linearChild = inflater.inflate(R.layout.resources_item, expensesResourcesList, false);

				((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
				((TextView) linearChild.findViewById(R.id.resourceName)).setText(i.getType());
				((TextView) linearChild.findViewById(R.id.values))
						.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

				expensesResourcesList.addView(linearChild);
			}


			if (!resources.canSubtract(warehouse.getExpenses())) {
				upgrade.setEnabled(false);
			}

			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (resources.canSubtract(warehouse.getExpenses())) {
						resources.subtract(warehouse.getExpenses());
						warehouse.upgrade();

						currentResourcesList.removeAllViews();
						for (Subject i : warehouse.clone().getStore().getSubjects()) {
							if (!i.getType().equals(Subject.RESIDENTS)) {
								View linearChild = inflater.inflate(R.layout.resources_item_minimal, currentResourcesList, false);

								((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
								((TextView) linearChild.findViewById(R.id.values)).setText(i.getMaxValue());

								currentResourcesList.addView(linearChild);
							}
						}

						upgradedResourcesList.removeAllViews();
						for (Subject i : warehouse.clone().upgrade().getStore().getSubjects()) {
							if (!i.getType().equals(Subject.RESIDENTS)) {
								View linearChild = inflater.inflate(R.layout.resources_item_minimal, upgradedResourcesList, false);

								((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
								((TextView) linearChild.findViewById(R.id.values)).setText(i.getMaxValue());

								upgradedResourcesList.addView(linearChild);
							}
						}

						expensesResourcesList.removeAllViews();
						for (Subject i : warehouse.clone().getExpenses().getSubjects()) {
							View linearChild = inflater.inflate(R.layout.resources_item, expensesResourcesList, false);

							((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
							((TextView) linearChild.findViewById(R.id.resourceName)).setText(i.getType());
							((TextView) linearChild.findViewById(R.id.values))
									.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

							expensesResourcesList.addView(linearChild);
						}
					}

					if (!resources.canSubtract(warehouse.getExpenses())) {
						upgrade.setEnabled(false);
					}
				}
			});
		} else {
			Building.fillList(getContext(), currentResourcesList,
					R.layout.resources_item_minimal, building, "income");

			Building.fillList(getContext(), upgradedResourcesList,
					R.layout.resources_item_minimal, building, "upgraded income");

			for (Subject i : building.clone().getExpenses().getSubjects()) {
				View linearChild = inflater.inflate(R.layout.resources_item, expensesResourcesList, false);

				((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
				((TextView) linearChild.findViewById(R.id.resourceName)).setText(i.getType());
				((TextView) linearChild.findViewById(R.id.values))
						.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

				expensesResourcesList.addView(linearChild);
			}

			if (!resources.canSubtract(building.getExpenses())) {
				upgrade.setEnabled(false);
			}

			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (resources.canSubtract(building.getExpenses())) {
						resources.subtract(building.getExpenses());
						building.upgrade();

						Building.fillList(getContext(), currentResourcesList,
								R.layout.resources_item_minimal, building, "income");

						Building.fillList(getContext(), upgradedResourcesList,
								R.layout.resources_item_minimal, building, "upgraded income");

						expensesResourcesList.removeAllViews();
						for (Subject i : building.clone().getExpenses().getSubjects()) {
							View linearChild = inflater.inflate(R.layout.resources_item, expensesResourcesList, false);

							((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
							((TextView) linearChild.findViewById(R.id.resourceName)).setText(i.getType());
							((TextView) linearChild.findViewById(R.id.values))
									.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

							expensesResourcesList.addView(linearChild);
						}
					}

					if (!resources.canSubtract(building.getExpenses())) {
						upgrade.setEnabled(false);
					}
				}
			});
		}


		return view;
	}
}
