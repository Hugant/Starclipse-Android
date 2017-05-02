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
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

public class MainActivity extends AppCompatActivity {
    private static final TreeMap<String, TextView> textViews = new TreeMap<String, TextView>();

    public static Resources GLOBAL_RESOURCES = new Resources();
    private Button infrastructureButton;
    private Button travelButton;
    private TextView planetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextViews();

        infrastructureButton = (Button) findViewById(R.id.infrastructureButton);
        travelButton = (Button) findViewById(R.id.travel);

        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                startActivity(new Intent(MainActivity.this, Infrastructure.class));
            }
        });

        infrastructureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View e) {
                startActivity(new Intent(MainActivity.this, InfrastructureActivity.class));
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
}
