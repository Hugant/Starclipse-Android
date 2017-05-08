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


	public BuildingFragment(Building building) {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_building, container, false);
	}

}
