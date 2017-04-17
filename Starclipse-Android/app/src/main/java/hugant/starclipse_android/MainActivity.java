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

import hugant.starclipse_android.building.House;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

public class MainActivity extends AppCompatActivity {
    public Resources res = new Resources();
    public Button button;
    public TextView planetName;
    public TextView residents;
    public TextView money;
    public House house = new House("small");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        planetName = (TextView) findViewById(R.id.planetName);
        planetName.setText("Hugant's planet");
        residents = (TextView) findViewById(R.id.residents);
        money = (TextView) findViewById(R.id.money);
        try {
            residents.setText("Residents: " + res.get(Subject.RESIDENTS).getNumber());
        } catch (Exception e) {
            residents.setText("Residents: 0");
        }

        try {
            money.setText("Money: " + res.get(Subject.MONEY).getNumber());
        } catch (Exception e) {
            money.setText("Money: 0");
        }


        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (house.getStatus().equals("Start")) {
                        house.startWork();
                    } else if (house.getStatus().equals("Claim")) {
                        res.add(house.claim());
                    }
                } catch (UnsupportedOperationException e) {
                    house.build();
                }

                try {
                    residents.setText("Residents: " + res.get(Subject.RESIDENTS).getNumber());
                } catch (Exception e) {
                    residents.setText("Residents: 0");
                }


                try {
                    money.setText("Money: " + res.get(Subject.MONEY).getNumber());
                } catch (Exception e) {
                    money.setText("Money: 0");
                }
            }
        });

        class Updater extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... unused) {
                while (true) {
                    publishProgress();
                    SystemClock.sleep(1000);
                }
               // return (null);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                try {
                    button.setText(house.getStatus());
//                    android.util.Log.i("Main", house.getIncome().get(Subject.RESIDENTS).toString());
                } catch (UnsupportedOperationException e) {}
            }
        }

        new Updater().execute();
    }
}
