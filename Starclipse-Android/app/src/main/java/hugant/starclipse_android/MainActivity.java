package hugant.starclipse_android;

import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.TreeMap;


import hugant.starclipse_android.building.House;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

public class MainActivity extends AppCompatActivity {
    private static final TreeMap<String, TextView> textViews = new TreeMap<String, TextView>();
    private static final Infrastructure infrastructure = new Infrastructure();

    private Resources res = new Resources();
    private Button button;
    private Button button2;
    private TextView planetName;

    private House house;
    private House house2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextViews();
        initButtons();

        house = new House("small", res, button, planetName);
        house2 = new House("small", res, button2, planetName);

        button.setOnClickListener(house.OnClick);
        button2.setOnClickListener(house2.OnClick);

        class Updater extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... unused) {
                while (true) {
                    publishProgress();
                    SystemClock.sleep(1000);
                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                updateResources();

                try {
                    button.setText(house.getStatus());
                    button2.setText(house2.getStatus());
//                    android.util.Log.i("Main", house.getIncome().get(Subject.RESIDENTS).toString());
                } catch (UnsupportedOperationException e) {}
            }
        }

        new Updater().execute();
    }

    public void updateResources() {
//        for (String i : res.asTypeArray()) {
//            try {
//
//            }
//        }
        try {
            textViews.get(Subject.RESIDENTS).setText(" " + res.get(Subject.RESIDENTS).getNumber());
        } catch (Exception e) {
            textViews.get(Subject.RESIDENTS).setText(" 0");
        }

        try {
            textViews.get(Subject.MONEY).setText(" " + res.get(Subject.MONEY).getNumber());
        } catch (Exception e) {
            textViews.get(Subject.MONEY).setText(" 0");
        }
    }

    private void initTextViews() {
        planetName = (TextView) findViewById(R.id.planetName);
        planetName.setText("Hugant's planet");

        textViews.put(Subject.RESIDENTS,(TextView) findViewById(R.id.residents));
        textViews.put(Subject.OXYGEN,   (TextView) findViewById(R.id.oxygen));
        textViews.put(Subject.ENERGY,   (TextView) findViewById(R.id.energy));
        textViews.put(Subject.MONEY,    (TextView) findViewById(R.id.money));
        textViews.put(Subject.WATER,    (TextView) findViewById(R.id.water));
        textViews.put(Subject.STONE,    (TextView) findViewById(R.id.stone));
        textViews.put(Subject.GOLD,     (TextView) findViewById(R.id.gold));
        textViews.put(Subject.IRON,     (TextView) findViewById(R.id.iron));
        textViews.put(Subject.COAL,     (TextView) findViewById(R.id.coal));
        textViews.put(Subject.TREE,     (TextView) findViewById(R.id.tree));
        textViews.put(Subject.SOIL,     (TextView) findViewById(R.id.soil));
        textViews.put(Subject.FOOD,     (TextView) findViewById(R.id.food));
    }

    private void initButtons() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
    }
}
