package hugant.starclipse_android.Infrastructure;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hugant.starclipse_android.Building;
import hugant.starclipse_android.BuildingActivity;
import hugant.starclipse_android.MainActivity;
import hugant.starclipse_android.R;

/**
 * Created by Hugant on 21.04.2017.
 */

public class InfrastructureAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<Building, Button> buildingsMap = new HashMap<Building, Button>();
    private ArrayList<Building> buildings;
    protected boolean inWork = false;

    public InfrastructureAdapter(Context context, ArrayList<Building> buildings) {
        this.context = context;
        this.buildings = buildings;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.inWork = true;

        class Updater extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... unused) {
                while (inWork) {
                    publishProgress();
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
            view = layoutInflater.inflate(R.layout.infrastructure_item, parent, false);
        }

        final Building building = getItem(position);

        ((TextView) view.findViewById(R.id.nameOfBuilding)).setText(building.getName());

        final Button button = (Button) view.findViewById(R.id.button);

        try {
            button.setText(building.getTimer());
        } catch (UnsupportedOperationException e) {
            button.setText("Build");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
//                Intent intent = new Intent(context, BuildingActivity.class);
//                intent.putExtra(Building.class.getCanonicalName(), );
                e.getContext().startActivity(new Intent(context, BuildingActivity.class));
            }
        });

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                try {
                    if (building.getTimer().equals("Start")) {
                        building.startWork();
                    } else if (building.getTimer().equals("Claim")) {
                        MainActivity.GLOBAL_RESOURCES.add(building.claim());
                    }
                } catch (UnsupportedOperationException ex) {
                    building.build();
                }

                try {
                    button.setText(building.getTimer());
                } catch (UnsupportedOperationException ex) {}

                MainActivity.updateResources();
                notifyDataSetChanged();
            }
        });

        for (Building i : buildings) {
            buildingsMap.put(i, button);
        }

        return view;
    }
}
