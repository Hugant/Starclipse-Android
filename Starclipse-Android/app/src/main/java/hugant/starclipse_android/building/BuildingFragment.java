package hugant.starclipse_android.building;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import hugant.starclipse_android.R;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuildingFragment extends Fragment {

	private Building building;
	private Resources resources;

	private Button claim;

	private Updater updater = new Updater();

	private class Updater extends AsyncTask<Void, Void, Void> {
		private boolean inWork = true;

		@Override
		protected Void doInBackground(Void... unused) {
			inWork = true;

			while (inWork) {
				publishProgress();
				SystemClock.sleep(900);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			try {
				claim.setText(building.getTimer());
			} catch (UnsupportedOperationException e) {
				claim.setText(R.string.button_build);
			}
		}

		private void stop() {
			inWork = false;
		}
	}

	public BuildingFragment(Building building, Resources resources) {
		this.building = building;
		this.resources = resources;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view;
		if (building.getName() == R.string.building_warehouse_name) {
			view = inflater.inflate(R.layout.fragment_building_warehouse, container, false);

			BuildingWarehouseAdapter adapter = new BuildingWarehouseAdapter(getActivity(), resources);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final ListView listView = (ListView) view.findViewById(R.id.warehouseResourcesAdapter);
			final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);

			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// new Fragment
				}
			});

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());
			listView.setAdapter(adapter);

		} else if (building.getName() == R.string.building_trading_station_name) {
			view = inflater.inflate(R.layout.fragment_building_trading_station, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final ListView listView = (ListView) view.findViewById(R.id.warehouseResourcesAdapter);
//			final TextView upgraded = (TextView) view.findViewById(R.id.upgradedContent);
//			final TextView expenses = (TextView) view.findViewById(R.id.expensesContent);

			final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);
			claim = (Button) view.findViewById(R.id.claimButton);

			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});

			claim.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					building.claim();
				}
			});

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());

		} else if (building.getName() == R.string.building_starships_factory_name) {
			view = inflater.inflate(R.layout.fragment_building_starships_factory, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final ListView listView = (ListView) view.findViewById(R.id.warehouseResourcesAdapter);
//			final TextView upgraded = (TextView) view.findViewById(R.id.upgradedContent);
//			final TextView expenses = (TextView) view.findViewById(R.id.expensesContent);

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());

		} else if (building.getName() == R.string.building_resources_factory_name) {
			view = inflater.inflate(R.layout.fragment_building_resources_factory, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final ListView listView = (ListView) view.findViewById(R.id.warehouseResourcesAdapter);
//			final TextView upgraded = (TextView) view.findViewById(R.id.upgradedContent);
//			final TextView expenses = (TextView) view.findViewById(R.id.expensesContent);

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());

		} else {
			view = inflater.inflate(R.layout.fragment_building, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final TextView upgraded = (TextView) view.findViewById(R.id.upgradedContent);
			final TextView expenses = (TextView) view.findViewById(R.id.expensesContent);
			final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);
			claim = (Button) view.findViewById(R.id.claimButton);

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());

			String expensesText = "";

			for (Subject i : building.getExpenses().getSubjects()) {
				expensesText += i.getType() + "\t" + i.getValue() + "\n";
			}

			for (int i = 0; i < 40; i++) {
				expensesText += "\n";
			}

			expenses.setText(expensesText);

			String upgradedText = "";

			for (Subject i : building.getIncome().getSubjects()) {
				upgradedText += i.getType() + "\t" + i.getValue() + "\t-> " + i.getValue() + "\n";
			}


			upgraded.setText(upgradedText);

			upgrade.setEnabled(false);

			try {
				claim.setText(building.getTimer());
			} catch (UnsupportedOperationException e) {
				claim.setText("Build");
			}

			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View e) {

				}
			});

			claim.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View e) {
					try {
						if (building.getTimer().equals("Start")) {
							building.startWork();
						} else if (building.getTimer().equals("Claim")) {
							resources.add(building.claim());
						}
					} catch (UnsupportedOperationException ex) {
						building.build();
					}

					try {
						claim.setText(building.getTimer());
					} catch (UnsupportedOperationException ex) {}
				}
			});

		}

		return view;
	}

	@Override
	public void onPause() {
		super.onPause();
//		updater.stop();
	}

	@Override
	public void onResume() {
		super.onResume();
//		updater = (Updater) new Updater().execute();
	}
}
