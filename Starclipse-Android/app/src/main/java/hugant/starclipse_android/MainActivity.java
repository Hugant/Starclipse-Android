package hugant.starclipse_android;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        house.build();
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
            money.setText("Residents: " + res.get(Subject.MONEY).getNumber());
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
                button.setText(house.getStatus());
                try {
                    residents.setText("Residents: " + res.get(Subject.RESIDENTS).getNumber());
                } catch (Exception e) {
                    residents.setText("Residents: 0");
                }

                try {
                    money.setText("Residents: " + res.get(Subject.MONEY).getNumber());
                } catch (Exception e) {
                    money.setText("Money: 0");
                }
            }
        });

        class Updater extends Thread {
            public void run() {
                while(true) {
                    button.setText("Hugant");
                   // button.setText(getHouse().getStatus());

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {}
                }
            }
        }

        Updater up = new Updater();
        up.start();
    }

    public Button getButton() {
        return this.button;
    }

    public Building getHouse() {
        return this.house;
    }
}
