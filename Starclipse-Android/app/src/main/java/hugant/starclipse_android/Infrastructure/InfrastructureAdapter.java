package hugant.starclipse_android.infrastructure;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hugant.starclipse_android.MainActivity;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.R;
import hugant.starclipse_android.building.BuildingFragment;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;


/**
 * Created by Hugant on 21.04.2017.
 */

public class InfrastructureAdapter extends BaseAdapter {

	private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Building> buildings;
    private Resources resources;

    public InfrastructureAdapter(Context context, ArrayList<Building> buildings, Resources resources) {
	    this.context = context;
        this.resources = resources;
        this.buildings = buildings;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return buildings.size();
    }

    @Override
    public Building getItem(int position) {
        return buildings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.infrastructure_adapter_item, parent, false);
        }

	    final Building building = getItem(position);

	    ((TextView) view.findViewById(R.id.buildingName)).setText(building.getName());
	    ((ImageView) view.findViewById(R.id.buildingIcon)).setImageResource(building.getImage());
        final Button button = (Button) view.findViewById(R.id.button);

	    view.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View e) {
			    BuildingFragment fragment = new BuildingFragment(building, resources);
			    FragmentTransaction fragmentTransaction =
					    ((MainActivity) context).getSupportFragmentManager().beginTransaction();
			    fragmentTransaction.replace(R.id.content, fragment);
			    fragmentTransaction.addToBackStack(null);
			    fragmentTransaction.commit();
		    }
	    });

	    if (!(building.getName() == R.string.building_warehouse_name)) {
		    try {
			    button.setText(building.getTimer());
		    } catch (UnsupportedOperationException e) {
			    button.setText(R.string.button_build);
		    }

		    button.setOnClickListener( new View.OnClickListener() {
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
					    button.setText(building.getTimer());
				    } catch (UnsupportedOperationException ex) {}

				    notifyDataSetChanged();
			    }
		    });

		    try {
			    resources.subtract(building.getExpenses());
		    } catch (ArithmeticException e) {

		    }
	    } else {
		    button.setVisibility(View.INVISIBLE);
	    }

        return view;
    }
}
