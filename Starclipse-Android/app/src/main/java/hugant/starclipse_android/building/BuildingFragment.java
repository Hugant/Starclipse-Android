package hugant.starclipse_android.building;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hugant.starclipse_android.R;
import hugant.starclipse_android.common.Resources;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuildingFragment extends Fragment {

	private Building building;
	private Resources resources;

	private Button claim;
	private boolean inWork = true;

	public BuildingFragment(Building building, Resources resources) {
		this.building = building;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inWork = true;

		class Updater1 extends AsyncTask<Void, Void, Void> {
			@Override
			protected Void doInBackground(Void... unused) {
				while (inWork) {
					publishProgress();
					android.util.Log.i("Hugant", "tick2");
					SystemClock.sleep(1000);
				}
				return null;
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				try {
					claim.setText(building.getTimer());
				} catch (UnsupportedOperationException e) {
					claim.setText("Build");
				}
			}
		}

		new Updater1().execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_building, container, false);

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

		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		inWork = false;
	}
}
