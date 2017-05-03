package hugant.starclipse_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.TreeMap;


import hugant.starclipse_android.Infrastructure.Infrastructure;
import hugant.starclipse_android.Infrastructure.InfrastructureActivity;
import hugant.starclipse_android.Travel.TravelActivity;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

public class MainActivity extends AppCompatActivity {
    private static final TreeMap<String, TextView> textViews = new TreeMap<String, TextView>();

	private Planet planet;

    private static Resources GLOBAL_RESOURCES = new Resources();
    private Button infrastructureButton;
    private Button travelButton;
    private TextView planetName;

    public static Resources getRes() {
	    return GLOBAL_RESOURCES;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    planetName = (TextView) findViewById(R.id.planetName);

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

        infrastructureButton = (Button) findViewById(R.id.infrastructureButton);
        travelButton = (Button) findViewById(R.id.travel);

	    planetName.setText("Hugant's planet");

        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                startActivity(new Intent(MainActivity.this, TravelActivity.class));
				// travel date in Intent
            }
        });

        infrastructureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View e) {
                startActivity(new Intent(MainActivity.this, InfrastructureActivity.class));
	            // infrastructure date in Intent
            }
        });
    }

    public static void updateResources() {
        for (Subject i : GLOBAL_RESOURCES.getSubjects()) {
            try {
                textViews.get(i.getType()).setText(" " + GLOBAL_RESOURCES.get(i.getType()).getNumber());
            } catch (Exception e) {
                textViews.get(i.getType()).setText(" 0");
            }
        }
    }
}
