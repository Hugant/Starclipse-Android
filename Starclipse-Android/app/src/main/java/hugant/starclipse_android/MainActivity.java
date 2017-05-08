package hugant.starclipse_android;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import java.util.ArrayList;

import hugant.starclipse_android.building.items.House;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.infrastructure.InfrastructureFragment;
import hugant.starclipse_android.planet.Planet;
import hugant.starclipse_android.planet.PlanetFragment;
import hugant.starclipse_android.travel.TravelFragment;

public class MainActivity extends Activity {

	private BottomNavigationView navigation;

	private TravelFragment travelFragment;
	private PlanetFragment planetFragment;
	private InfrastructureFragment infrastructureFragment;
	private SettingsFragment settingsFragment;

	private ArrayList<Planet> planets;

	private Planet planet;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			switch (item.getItemId()) {
				case R.id.navigation_travel:
					fragmentTransaction.replace(R.id.content, travelFragment);
					fragmentTransaction.commit();
					return true;
				case R.id.navigation_planet:
					fragmentTransaction.replace(R.id.content, planetFragment);
					fragmentTransaction.commit();
					return true;
				case R.id.navigation_infrastructure:
					fragmentTransaction.replace(R.id.content, infrastructureFragment);
					fragmentTransaction.commit();
					return true;
				case R.id.navigation_settings:
					fragmentTransaction.replace(R.id.content, settingsFragment);
					fragmentTransaction.commit();
					return true;
			}
			return false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		planets = new ArrayList<Planet>();
		//get date for db

		//for test
		planets.add(new Planet("Caroline", new Resources()));
		planets.add(new Planet("Amine", new Resources()));
		planets.add(new Planet("Kate", new Resources()));

		planet = planets.get(0);

		//for test
		for (Planet i: planets) {
			i.getInfrastructure().add(new House("small"));
			i.getInfrastructure().add(new House("small"));
			i.getInfrastructure().add(new House("big"));
			i.getInfrastructure().add(new House("average"));
		}


		travelFragment = new TravelFragment(planets);
		planetFragment = new PlanetFragment(planet);
		infrastructureFragment = new InfrastructureFragment(planet);
		settingsFragment = new SettingsFragment();

		navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		navigation.setSelectedItemId(R.id.navigation_planet);
	}

	public void setCurrentPlanet(int index) {
		planet = planets.get(index);
		planetFragment = new PlanetFragment(planet);
		infrastructureFragment = new InfrastructureFragment(planet);
		navigation.setSelectedItemId(R.id.navigation_planet);
	}

}
