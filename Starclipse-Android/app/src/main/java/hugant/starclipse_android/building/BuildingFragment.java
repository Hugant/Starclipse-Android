package hugant.starclipse_android.building;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
	Button addResident = null;
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
			final ListView listView = (ListView) view.findViewById(R.id.warehouseResourcesAdapter);
			final Button upgrade = (Button) view.findViewById(R.id.upgradeButton);

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
			listView.setAdapter(adapter);


		} else if (building.getName() == R.string.building_trading_station_name) {
			view = inflater.inflate(R.layout.fragment_building_trading_station, container, false);

			final TextView buildingName = (TextView) view.findViewById(R.id.buildingName);
			final TextView description = (TextView) view.findViewById(R.id.description);
			final ImageView image = (ImageView) view.findViewById(R.id.image);
			final ListView listView = (ListView) view.findViewById(R.id.warehouseResourcesAdapter);
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
			addResident = (Button) view.findViewById(R.id.residentsAddButton);
			final Button subtractResident = (Button) view.findViewById(R.id.residentsSubtractButton);


			buildingName.setText(building.getName());
			image.setImageResource(building.getImage());
			description.setText(building.getDescription());
			residents.setText(building.getResidents().getValue() + " / "
					+ building.getResidents().getMaxValue());
//			income.setText();

			addResident.setOnTouchListener(new View.OnTouchListener() {
				boolean pressed = false;

				Handler handler = new Handler();

				Runnable runnableCode = new Runnable() {
					@Override
					public void run() {
						int delay = 100;
						addResident.callOnClick();
						delay -= 10;
						handler.postDelayed(runnableCode, delay);
					}
				};

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (!pressed) {
						handler.post(runnableCode);
						pressed = false;
					} else {
						handler.removeCallbacks(runnableCode);
						pressed = true;
					}
					return false;
				}
			});

			addResident.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (building.getResidents()
							.compareTo(new Subject(building.getResidents().getMaxValue())) == -1) {
						if (resources.canSubtract(new Subject(Subject.RESIDENTS, "1"))) {
							resources.subtract(new Subject(Subject.RESIDENTS, "1"));
							building.getResidents().add(new Subject("1"));
							residents.setText(building.getResidents().getValue() + " / "
									+ building.getResidents().getMaxValue());
							subtractResident.setEnabled(true);
						} else {
							//TODO: To handle the event
						}
					}

					if (building.getResidents()
							.compareTo(new Subject(building.getResidents().getMaxValue())) > -1) {
						addResident.setEnabled(false);
					}
				}
			});

			subtractResident.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (building.getResidents().compareTo(new Subject("0")) == 1) {
						resources.add(new Subject(Subject.RESIDENTS, "1"));
						try {
							building.getResidents().subtract(new Subject("1"));
						} catch (ArithmeticException e) {
							// TODO: To handle the event
						}
						residents.setText(building.getResidents().getValue() + " / "
								+ building.getResidents().getMaxValue());
						addResident.setEnabled(true);
					}

					if (building.getResidents()
							.compareTo(new Subject("0")) < 1) {
						subtractResident.setEnabled(false);
					}
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

	public boolean getPressed() {
		return addResident.isPressed();
	}
}
