package hugant.starclipse_android.Infrastructure;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hugant.starclipse_android.Building;
import hugant.starclipse_android.MainActivity;
import hugant.starclipse_android.R;
import hugant.starclipse_android.building.House;

/**
 * Created by Hugant on 21.04.2017.
 */

public class InfrastructureActivity extends Activity {
    ArrayList<Building> buildings = new ArrayList<Building>();
    InfrastructureAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infrastructure);

        adapter = new InfrastructureAdapter(this, buildings);
        ListView listView = (ListView) findViewById(R.id.adapter);
        listView.setAdapter(adapter);
        android.util.Log.i("Hugant", "onCreate");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.inWork = false;
        android.util.Log.i("Hugant", "onDestroy");
    }
}
