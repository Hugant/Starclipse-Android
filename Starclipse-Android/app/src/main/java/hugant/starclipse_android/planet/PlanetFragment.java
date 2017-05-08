package hugant.starclipse_android.planet;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hugant.starclipse_android.R;
import hugant.starclipse_android.common.Subject;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanetFragment extends Fragment {

	private Planet planet;

	private TextView planetTitleName;

	public PlanetFragment(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_planet, container, false);

		planetTitleName = (TextView) view.findViewById(R.id.planetTitleName);
		planetTitleName.setText(planet.getName());

		TextView a = (TextView) view.findViewById(R.id.textView);

		String text = "";
		for (Subject i : planet.getResources().getSubjects()) {
			text += i.toString() + " / " + i.getMaxValue() + "\n";
		}
		a.setText(text);

		return view;
	}

}
