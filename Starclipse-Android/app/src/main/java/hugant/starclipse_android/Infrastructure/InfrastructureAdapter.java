package hugant.starclipse_android.Infrastructure;

import android.content.Context;
import android.os.AsyncTask;
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
import hugant.starclipse_android.R;

/**
 * Created by Hugant on 21.04.2017.
 */

public class InfrastructureAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<Building, Button> buildingsMap = new HashMap<Building, Button>();
    private ArrayList<Building> buildings;
    Updater updater = new Updater();
    private boolean inWork = false;

    public InfrastructureAdapter(Context context, ArrayList<Building> buildings) {
        this.context = context;
        this.buildings = buildings;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.inWork = true;
        updater.execute();
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
            button.setText(building.getStatus());
        } catch (UnsupportedOperationException e) {
            button.setText("Build");
        }

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                try {
                    if (building.getStatus().equals("Start")) {
                        building.startWork();
                    } else if (building.getStatus().equals("Claim")) {
                        building.claim();
                    }
                } catch (UnsupportedOperationException ex) {
                    building.build();
                }

                try {
                    button.setText(building.getStatus());
                } catch (UnsupportedOperationException ex) {}
            }
        });

        for (Building i : buildings) {
            buildingsMap.put(i, button);
        }

        return view;
    }

    private class Updater extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            while (inWork) {
                publishProgress();
                android.os.SystemClock.sleep(1000);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            for (Map.Entry<Building, Button> entry : buildingsMap.entrySet()) {
                try {
                    entry.getValue().setText(entry.getKey().getStatus());
                } catch (UnsupportedOperationException e) {}
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.inWork = false;
    }
}
