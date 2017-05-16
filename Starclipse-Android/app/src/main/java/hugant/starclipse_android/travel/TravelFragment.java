package hugant.starclipse_android.travel;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import hugant.starclipse_android.planet.Planet;
import hugant.starclipse_android.R;


/**
 *
 * TODO: write javadoc
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends Fragment {
	private ArrayList<Planet> planets;

	public TravelFragment(ArrayList<Planet> planets) {
		this.planets = planets;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_travel, container, false);

		TravelAdapter adapter = new TravelAdapter(getActivity(), planets);
		ListView listView = (ListView) view.findViewById(R.id.travelAdapter);
		listView.setAdapter(adapter);

		return view;
	}
}
