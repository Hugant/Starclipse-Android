package hugant.starclipse_android.travel;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hugant.starclipse_android.R;
import hugant.starclipse_android.planet.Planet;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends Fragment {

	private ArrayList<Planet> planets;
	private TravelAdapter adapter;

	public TravelFragment(ArrayList<Planet> planets) {
		this.planets = planets;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_travel, container, false);
	}

}
