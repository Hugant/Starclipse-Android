package hugant.starclipse_android.building;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hugant.starclipse_android.MainActivity;
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
		boolean first = false;
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
//			final ListView listView = (ListView) view.findViewById(R.id.warehouseResourcesAdapter);
			final LinearLayout linear = (LinearLayout) view.findViewById(R.id.linear);
			final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);

			View child = inflater.inflate(R.layout.warehouse_adapter_item, linear, false);


			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					FragmentTransaction fragmentTransaction =
							((MainActivity)getContext()).getSupportFragmentManager().beginTransaction();
					fragmentTransaction.replace(R.id.content,
							new UpgradeFragment(building, resources));
					fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
				}
			});

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());
//			listView.setAdapter(adapter);


		} else if (building.getName() == R.string.building_trading_station_name) {
			view = inflater.inflate(R.layout.fragment_building_trading_station, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);
			claim = (Button) view.findViewById(R.id.claimButton);

			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					FragmentTransaction fragmentTransaction =
							((MainActivity)getContext()).getSupportFragmentManager().beginTransaction();
					fragmentTransaction.replace(R.id.content,
							new UpgradeFragment(building, resources));
					fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
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

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());

		} else if (building.getName() == R.string.building_resources_factory_name) {
			view = inflater.inflate(R.layout.fragment_building_resources_factory, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final TextView description = (TextView) view.findViewById(R.id.description);

			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());

		} else {
			view = inflater.inflate(R.layout.fragment_building, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final TextView income = (TextView) view.findViewById(R.id.income);
			final TextView residents = (TextView) view.findViewById(R.id.residents);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);
			final Button addButton = (Button) view.findViewById(R.id.residentsAddButton);
			final Button subtractButton = (Button) view.findViewById(R.id.residentsSubtractButton);


			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());
			residents.setText(building.getResidents().getValue() + " / "
					+ building.getResidents().getMaxValue());
//			income.setText();

			addButton.setOnTouchListener(new View.OnTouchListener() {
				Handler handler = new Handler();

				Runnable addRunnable = new Runnable() {
					@Override
					public void run() {
						subtractButton.setEnabled(true);

						try {
							building.addResidents(resources, "1");
						} catch (ArithmeticException e) {
							addButton.setEnabled(false);
						}

						residents.setText(building.getResidents().getValue() + " / "
								+ building.getResidents().getMaxValue());

						if (addButton.isEnabled()) {
							handler.post(addRunnable);
						}
					}
				};

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							handler.postDelayed(addRunnable, 500);
							return false;

						case MotionEvent.ACTION_UP:
							handler.removeCallbacks(addRunnable);
							return false;
					}

					return false;
				}
			});

			addButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						building.addResidents(resources, "1");
						subtractButton.setEnabled(true);
					} catch (ArithmeticException e) {
						addButton.setEnabled(false);
					}

					residents.setText(building.getResidents().getValue() + " / "
							+ building.getResidents().getMaxValue());
				}
			});

			subtractButton.setOnTouchListener(new View.OnTouchListener() {
				Handler handler = new Handler();

				Runnable subtractRunnable = new Runnable() {
					@Override
					public void run() {
						addButton.setEnabled(true);

						try {
							building.takeResidents(resources, "1");
						} catch (ArithmeticException e) {
							subtractButton.setEnabled(false);
						}

						residents.setText(building.getResidents().getValue() + " / "
								+ building.getResidents().getMaxValue());

						if (subtractButton.isEnabled()) {
							handler.postDelayed(subtractRunnable, 0);
						}
					}
				};

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							handler.postDelayed(subtractRunnable, 500);
							return false;

						case MotionEvent.ACTION_UP:
							handler.removeCallbacks(subtractRunnable);
							return false;
					}

					return false;
				}
			});

			subtractButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						building.takeResidents(resources, "1");
						addButton.setEnabled(true);
					} catch (ArithmeticException e) {
						subtractButton.setEnabled(false);
					}

					residents.setText(building.getResidents().getValue() + " / "
							+ building.getResidents().getMaxValue());
				}
			});

			claim = (Button) view.findViewById(R.id.claimButton);

			try {
				claim.setText(building.getTimer());
			} catch (UnsupportedOperationException e) {
				claim.setText(R.string.button_build);
			}

			upgrade.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View e) {
					FragmentTransaction fragmentTransaction =
							((MainActivity)getContext()).getSupportFragmentManager().beginTransaction();
					fragmentTransaction.replace(R.id.content,
							new UpgradeFragment(building, resources));
					fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
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
