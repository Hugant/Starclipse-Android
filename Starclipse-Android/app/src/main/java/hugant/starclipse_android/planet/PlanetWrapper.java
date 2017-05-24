package hugant.starclipse_android.planet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hugant on 24.05.2017.
 */

public class PlanetWrapper implements Serializable {
	private ArrayList<Planet> planets;

	public PlanetWrapper(){}

	public PlanetWrapper(ArrayList<Planet> planets) {
		this.planets = planets;
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}
}
