package hugant.starclipse_android.travel;


import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import hugant.starclipse_android.MainActivity;
import hugant.starclipse_android.common.Timer;
import hugant.starclipse_android.infrastructure.InfrastructureAdapter;
import hugant.starclipse_android.infrastructure.InfrastructureFragment;
import hugant.starclipse_android.planet.Planet;
import hugant.starclipse_android.R;


/**
 *
 * TODO: write javadoc
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends Fragment {
	private ArrayList<Planet> planets;
	private Updater updater = new Updater();
	private Timer planetIncomeTimer = new Timer(Timer.SECOND * 10, "Claim");
	private Button findButton;

	private class Updater extends AsyncTask<Void, Void, Void> {
		private boolean inWork = true;

		@Override
		protected void onProgressUpdate(Void... values) {
			try {
				findButton.setText(planetIncomeTimer.toString());
			} catch (UnsupportedOperationException e) {
				findButton.setText(R.string.button_find);
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			inWork = true;
			while (inWork) {
				publishProgress(params);
				SystemClock.sleep(500);
			}
			return null;
		}

		private void stop() {
			inWork = false;
		}
	}

	public TravelFragment(ArrayList<Planet> planets) {
		this.planets = planets;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_travel, container, false);

		final TravelAdapter adapter = new TravelAdapter(getActivity(), planets);
		final ListView listView = (ListView) view.findViewById(R.id.travelAdapter);
		listView.setAdapter(adapter);

		LinearLayout searchPlanetList = (LinearLayout) view.findViewById(R.id.searchPlanetList);

		View linearChild = inflater.inflate(R.layout.search_planet_item, listView, false);

		((ImageView) linearChild.findViewById(R.id.searchIcon)).setImageResource(R.drawable.starship3_30dp);
		findButton = (Button) linearChild.findViewById(R.id.findButton);

		findButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (planetIncomeTimer.toString().equals("Claim")) {
						Planet planet = ((MainActivity) getContext()).getNewPlanet();
						if (planet != null) {
							planet.getInfrastructure();
							planets.add(planet);
							adapter.notifyDataSetChanged();
						} else {
							findButton.setEnabled(false);
						}

						planetIncomeTimer = new Timer(Timer.SECOND * 10, "Claim");
					}
				} catch (UnsupportedOperationException e) {
					if (!planetIncomeTimer.isWork()) {
						planetIncomeTimer.start();
					}
				}

				try {
					findButton.setText(planetIncomeTimer.toString());
				} catch (UnsupportedOperationException e) {
					findButton.setText(R.string.button_find);
				}
			}
		});

		searchPlanetList.addView(linearChild);

		return view;
	}

	@Override
	public void onPause() {
		super.onPause();
		updater.stop();
	}

	@Override
	public void onResume() {
		super.onResume();
		updater = (TravelFragment.Updater) new TravelFragment.Updater().execute();
	}
}
