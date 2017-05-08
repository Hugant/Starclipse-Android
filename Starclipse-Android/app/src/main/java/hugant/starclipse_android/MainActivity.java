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

	private TravelFragment travelFragment;
	private PlanetFragment planetFragment;
	private InfrastructureFragment infrastructureFragment;
	private SettingsFragment settingsFragment;

	private Planet planet = new Planet("Caroline", new Resources());

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

		planet.getInfrastructure().add(new House("small"));

		travelFragment = new TravelFragment();
		planetFragment = new PlanetFragment(planet);
		infrastructureFragment = new InfrastructureFragment(planet);
		settingsFragment = new SettingsFragment();

		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		navigation.setSelectedItemId(R.id.navigation_planet);
	}

}
