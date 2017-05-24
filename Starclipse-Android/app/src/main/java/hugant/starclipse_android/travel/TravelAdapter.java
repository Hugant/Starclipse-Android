package hugant.starclipse_android.travel;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;

import hugant.starclipse_android.infrastructure.InfrastructureFragment;
import hugant.starclipse_android.planet.Planet;
import hugant.starclipse_android.MainActivity;
import hugant.starclipse_android.R;

/**
 * TODO: write javadoc
 *
 * Created by Hugant on 07.05.2017.
 */

class TravelAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Planet> planets;
	private LayoutInflater layoutInflater;

	TravelAdapter(Context context, ArrayList<Planet> planets) {
		this.context = context;
		this.planets = planets;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return planets.size();
	}

	@Override
	public Planet getItem(int position) {
		return planets.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = layoutInflater.inflate(R.layout.travel_adapter_item, parent, false);
		}

		final Planet planet = getItem(position);

		((TextView) view.findViewById(R.id.nameOfPlanet)).setText(planet.getName());
		((ImageView) view.findViewById(R.id.planetIcon)).setImageResource(planet.getImage());

		final Button button = (Button) view.findViewById(R.id.button);

		button.setText(R.string.travel_adapter_button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View e) {
				((MainActivity)context).setCurrentPlanet(position);
			}
		});

		if (position == ((MainActivity) context).getCurrentPlanetIndex()) {
			button.setText("Your here");
		}

		return view;
	}
}
