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

import hugant.starclipse_android.R;
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

		Building.fillList(getContext(), currentResourcesList,
				R.layout.resources_item_minimal, building, "income");

		Building.fillList(getContext(), upgradedResourcesList,
				R.layout.resources_item_minimal, building, "upgraded income");

		Building.fillList(getContext(), expensesResourcesList,
				R.layout.resources_item_minimal, building, "expenses");

		if (!resources.canSubtract(building.getExpenses())) {
			upgrade.setEnabled(false);
		}

			upgrade.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (resources.canSubtract(building.getExpenses())) {
					building.upgrade();

					Building.fillList(getContext(), currentResourcesList,
							R.layout.resources_item_minimal, building, "income");

					Building.fillList(getContext(), upgradedResourcesList,
							R.layout.resources_item_minimal, building, "upgraded income");

					Building.fillList(getContext(), expensesResourcesList,
							R.layout.resources_item_minimal, building, "expenses");
				} else {
					upgrade.setEnabled(false);
				}
			}
		});


//		for (Subject i : cloneBuilding
//				.getIncome().multiply(building.getResidents().getValue()).getSubjects()) {
//			View linearChild = inflater.inflate(R.layout.resources_item_minimal, currentResourcesList, false);
//
//			((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
//			((TextView) linearChild.findViewById(R.id.values))
//					.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));
//
//			currentResourcesList.addView(linearChild);
//		}


//		for (Subject i : cloneBuilding.upgrade()
//				.getIncome().multiply(building.getResidents().getValue()).getSubjects()) {
//			View linearChild = inflater.inflate(R.layout.resources_item_minimal, upgradedResourcesList, false);
//
//			((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
//			((TextView) linearChild.findViewById(R.id.values))
//					.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));
//
//			upgradedResourcesList.addView(linearChild);
//		}

//		for (Subject i : cloneBuilding.getExpenses().getSubjects()) {
//			View linearChild = inflater.inflate(R.layout.resources_item, expensesResourcesList, false);
//
//			((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
//			((TextView) linearChild.findViewById(R.id.resourceName)).setText(i.getType());
//			((TextView) linearChild.findViewById(R.id.values))
//					.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));
//
//			expensesResourcesList.addView(linearChild);
//		}


		return view;
	}
}
