package hugant.starclipse_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang.SerializationUtils;

import java.util.ArrayList;

import hugant.starclipse_android.building.items.industry.Starship;
import hugant.starclipse_android.infrastructure.InfrastructureFragment;
import hugant.starclipse_android.planet.Planet;
import hugant.starclipse_android.planet.PlanetFragment;
import hugant.starclipse_android.planet.PlanetWrapper;
import hugant.starclipse_android.travel.TravelFragment;


public class MainActivity extends FragmentActivity {
	public static final String APP_PREFERENCE_NAME = "Starclipse";
	public static final String APP_PREFERENCE_PLANETS = "Planets";


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
					fragmentTransaction.disallowAddToBackStack();
					fragmentTransaction.commit();
					return true;
				case R.id.navigation_settings:
					fragmentTransaction.replace(R.id.content, settingsFragment);
					fragmentTransaction.disallowAddToBackStack();
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

		SharedPreferences sharedPreferences =
				getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

//		Gson gson = new Gson();

		if (sharedPreferences.contains(APP_PREFERENCE_PLANETS)) {
			PlanetWrapper wrapper = new Gson().fromJson(sharedPreferences.getString(APP_PREFERENCE_PLANETS, ""), PlanetWrapper.class);
			planets = wrapper.getPlanets();
			planet = planets.get(0);
		} else {
			planets = new ArrayList<>();
			planets.add(Planet.PLANETS[(int) Math.round(Math.random() * (Planet.PLANETS.length - 1))]);
			planet = planets.get(0);
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

	public Planet getNewPlanet() {
		int counter = 0;
		boolean isRandom = true;
		Planet randomPlanet = null;

		while (isRandom) {
			isRandom = false;
			randomPlanet = Planet.PLANETS[(int) Math.round(Math.random() * (Planet.PLANETS.length - 1))];
			for (Planet i : planets) {
				if (i.getName().equals(randomPlanet.getName()))
					isRandom = true;
			}
			counter++;
			if (counter == Planet.PLANETS.length) {
				return null;
			}
		}

		return randomPlanet;
	}

	@Override
	protected void onPause() {
		super.onPause();
//		byte[] a = SerializationUtils.serialize(planets);
//		String c = a.toString();
//		byte[] b = a;

//		android.util.Log.i("Hugant", a + "");
//		android.util.Log.i("Hugant", c);
//		android.util.Log.i("Hugant", b + "");


//		Gson gson = new Gson();
//		JsonElement element = gson.toJsonTree(new TypeToken<ArrayList<Planet>>(){}.getType());
//		JsonArray jsonArray = element.getAsJsonArray();
//		android.util.Log.i("Hugant", jsonArray.toString());
//
//		fro
		Gson gson = new Gson();
		String json = gson.toJson(new PlanetWrapper(planets));
		SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCE_NAME,MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(APP_PREFERENCE_PLANETS, json);
		editor.apply();

	}
}
