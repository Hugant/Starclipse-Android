package hugant.starclipse_android;

import android.app.Activity;
//import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hugant.starclipse_android.building.Building;
import hugant.starclipse_android.building.items.House;
import hugant.starclipse_android.building.items.TradingStation;
import hugant.starclipse_android.building.items.industry.ResourcesFactory;
import hugant.starclipse_android.building.items.industry.Starship;
import hugant.starclipse_android.building.items.industry.StarshipsFactory;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.ScaleNumber;
import hugant.starclipse_android.common.Subject;
import hugant.starclipse_android.infrastructure.InfrastructureFragment;
import hugant.starclipse_android.planet.Planet;
import hugant.starclipse_android.planet.PlanetFragment;
import hugant.starclipse_android.travel.TravelFragment;

public class MainActivity extends FragmentActivity {

	private BottomNavigationView navigation;

	private TravelFragment travelFragment;
	private PlanetFragment planetFragment;
	private InfrastructureFragment infrastructureFragment;
	private SettingsFragment settingsFragment;

	private ArrayList<Planet> planets;
	private ArrayList<Starship> starships;

	private Planet planet;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
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

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		planets = new ArrayList<>();

		planets.addAll(Arrays.asList(Planet.PLANETS));

		planet = planets.get(0);

		planet.getResources().add(new Resources(Subject.RESIDENTS, "500"));

		//for test
		for (Planet i: planets) {
			i.getInfrastructure().add(new House("small"));
			i.getInfrastructure().add(new House("average"));
			i.getInfrastructure().add(new House("big"));
			i.getInfrastructure().add(new TradingStation(planet.getResources()));
			i.getInfrastructure().add(new StarshipsFactory(starships));
			i.getInfrastructure().add(new ResourcesFactory(Subject.GOLD));
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

	public int getCurrentPlanetIndex() {
		return planets.indexOf(planet);
	}

}
