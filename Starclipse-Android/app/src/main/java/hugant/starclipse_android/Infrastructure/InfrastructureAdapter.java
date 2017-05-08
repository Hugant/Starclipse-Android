package hugant.starclipse_android.infrastructure;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import hugant.starclipse_android.MainActivity;
import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.R;
import hugant.starclipse_android.building.BuildingFragment;
import hugant.starclipse_android.common.Resources;


/**
 * Created by Hugant on 21.04.2017.
 */

public class InfrastructureAdapter extends BaseAdapter {

	private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Building> buildings;
    private Resources resources;
    private static boolean inWork = false;

    public InfrastructureAdapter(Context context, ArrayList<Building> buildings, Resources resources) {
	    this.context = context;
        this.resources = resources;
        this.buildings = buildings;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.inWork = true;

        class Updater extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... unused) {
                while (inWork) {
                    publishProgress();
	                android.util.Log.i("Hugant", "tick");
                    SystemClock.sleep(1000);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                notifyDataSetChanged();
            }
        }

        new Updater().execute();
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

        ((TextView) view.findViewById(R.id.nameOfBuilding)).setText(building.getName());

        final Button button = (Button) view.findViewById(R.id.button);

	    view.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View e) {
			    BuildingFragment fragment = new BuildingFragment(building);
			    FragmentTransaction fragmentTransaction = ((Activity) context).getFragmentManager().beginTransaction();
			    fragmentTransaction.replace(R.id.content, fragment);
			    fragmentTransaction.addToBackStack(null);
			    fragmentTransaction.commit();
		    }
	    });

	    if (!building.getName().equals("Warehouse")) {
		    try {
			    button.setText(building.getTimer());
		    } catch (UnsupportedOperationException e) {
			    button.setText("Build");
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
	    } else {
		    button.setVisibility(View.INVISIBLE);
	    }


        return view;
    }

    public static void setInWork(boolean work) {
	    inWork = work;
    }
}
