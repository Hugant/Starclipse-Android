package hugant.starclipse_android.infrastructure;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import hugant.starclipse_android.R;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.planet.Planet;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfrastructureFragment extends Fragment {

	private InfrastructureAdapter adapter;
	private Resources resources;
	private Infrastructure infrastructure;
	private String name;

	private Updater updater = new Updater();

	private class Updater extends AsyncTask<Adapter, Adapter, Void> {
		private boolean inWork = true;

		@Override
		protected void onProgressUpdate(Adapter... values) {
			for (Adapter i : values) {
				((InfrastructureAdapter)i).notifyDataSetChanged();
			}
		}

		@Override
		protected Void doInBackground(Adapter... params) {
			inWork = true;
			while (inWork) {
				publishProgress(params);
				SystemClock.sleep(1000);
			}
			return null;
		}

		private void stop() {
			inWork = false;
		}
	}

	public InfrastructureFragment(Planet planet) {
		this.infrastructure = planet.getInfrastructure();
		this.resources = planet.getResources();
		this.name = planet.getName();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_infrastructure, container, false);

		TextView planetName = (TextView) view.findViewById(R.id.planetName);
		planetName.setText(name);

		ListView listView = (ListView) view.findViewById(R.id.infrastructureAdapter);
		listView.setAdapter(adapter);

		return view;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new InfrastructureAdapter(getActivity(), infrastructure.getBuildings(), resources);
	}

	@Override
	public void onPause() {
		super.onPause();
		updater.stop();
	}

	@Override
	public void onResume() {
		super.onResume();
		updater = (Updater) new Updater().execute(adapter);
	}
}
