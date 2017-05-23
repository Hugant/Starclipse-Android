package hugant.starclipse_android.planet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hugant.starclipse_android.R;
import hugant.starclipse_android.common.Subject;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanetFragment extends Fragment {

	private Planet planet;

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

		((TextView) view.findViewById(R.id.planetName)).setText(planet.getName());
		((TextView) view.findViewById(R.id.description)).setText(planet.getDescription());
		((ImageView) view.findViewById(R.id.image)).setImageResource(planet.getImage());

		final LinearLayout starhipsList = (LinearLayout) view.findViewById(R.id.starshipsList);
		final LinearLayout resourcesList = (LinearLayout) view.findViewById(R.id.resourcesList);

		for (Subject i : planet.getResources().getSubjects()) {
			View linearChild = inflater.inflate(R.layout.resources_item, resourcesList, false);

			((ImageView) linearChild.findViewById(R.id.resourceIcon)).setImageResource(i.getImage());
			((TextView) linearChild.findViewById(R.id.resourceName)).setText(i.getType());
			((TextView) linearChild.findViewById(R.id.values))
					.setText(i.getValue() + (i.getMaxValue() == null ? "" : " / " + i.getMaxValue()));

			resourcesList.addView(linearChild);
		}

		return view;
	}

}
