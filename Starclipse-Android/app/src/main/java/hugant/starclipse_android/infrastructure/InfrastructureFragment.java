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
		android.util.Log.i("Hugant", "onCreate");

		adapter = new InfrastructureAdapter(getActivity(), infrastructure.getBuildings(), resources);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		android.util.Log.i("Hugant", "onDestroy");
		InfrastructureAdapter.setInWork(false);
	}

	@Override
	public void onPause() {
		super.onPause();
		android.util.Log.i("Hugant", "onPause");
		InfrastructureAdapter.setInWork(false);
	}

	@Override
	public void onStart() {
		super.onStart();
		android.util.Log.i("Hugant", "onStart");
		InfrastructureAdapter.setInWork(true);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		android.util.Log.i("Hugant", "onDetach");
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		android.util.Log.i("Hugant", "onAttach");
	}

	@Override
	public void onResume() {
		super.onResume();
		android.util.Log.i("Hugant", "onResume");
		InfrastructureAdapter.setInWork(true);
	}

	@Override
	public void onStop() {
		super.onStop();
		android.util.Log.i("Hugant", "onStop");
		InfrastructureAdapter.setInWork(false);
	}


}
