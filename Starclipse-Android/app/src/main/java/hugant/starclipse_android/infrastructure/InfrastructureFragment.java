package hugant.starclipse_android.infrastructure;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import hugant.starclipse_android.MainActivity;
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

	public InfrastructureFragment(Planet planet) {
		this.infrastructure = planet.getInfrastructure();
		this.resources = planet.getResources();
		this.name = planet.getName();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_infrastructure, container, false);

		infrastructurePlanetName = (TextView) view.findViewById(R.id.infrastructurePlanetName);
		infrastructurePlanetName.setText(name);

		listView = (ListView) view.findViewById(R.id.infrastructureAdapter);
		listView.setAdapter(adapter);

		return view;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new InfrastructureAdapter(getActivity(), infrastructure.getBuildings(), resources);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		InfrastructureAdapter.setInWork(false);
	}
}
