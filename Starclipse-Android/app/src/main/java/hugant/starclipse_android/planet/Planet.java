package hugant.starclipse_android.planet;

import hugant.starclipse_android.R;
import hugant.starclipse_android.infrastructure.Infrastructure;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

public class Planet {
	public static final Planet[] PLANETS = {
			new Planet("Caroline",  new Resources(), R.drawable.planet1, 0),
			new Planet("Amine",     new Resources(), R.drawable.planet2, 0),
			new Planet("Kate",      new Resources(), R.drawable.planet3, 0),
			new Planet("Emily",     new Resources(), R.drawable.planet4, 0),
			new Planet("Hannah",    new Resources(), R.drawable.planet5, 0),
			new Planet("Brianna",   new Resources(), R.drawable.planet6, 0),
			new Planet("Hailey",    new Resources(), R.drawable.planet7, 0),
			new Planet("Kaylee",    new Resources(), R.drawable.planet8, 0),
			new Planet("Taylor",    new Resources(), R.drawable.planet9, 0),
			new Planet("Ashley",    new Resources(), R.drawable.planet10, 0),
			new Planet("Lauren",    new Resources(), R.drawable.planet11, 0),
			new Planet("Kayla",     new Resources(), R.drawable.planet12, 0),
			new Planet("Madeline",  new Resources(), R.drawable.planet13, 0),
			new Planet("Matthew",   new Resources(), R.drawable.planet14, 0),
			new Planet("Joseph",    new Resources(), R.drawable.planet15, 0),
			new Planet("Joshua",    new Resources(), R.drawable.planet16, 0),
			new Planet("Andrew",    new Resources(), R.drawable.planet17, 0),
			new Planet("Ryan",      new Resources(), R.drawable.planet18, 0),
			new Planet("Kayla",     new Resources(), R.drawable.planet19, 0),
			new Planet("Hoggarth",  new Resources(), R.drawable.planet20, 0),
			new Planet("Allford",   new Resources(), R.drawable.planet21, 0),
			new Planet("Baldwin",   new Resources(), R.drawable.planet22, 0),
			new Planet("Barnes",    new Resources(), R.drawable.planet23, 0),
			new Planet("Carroll",   new Resources(), R.drawable.planet24, 0),
			new Planet("Carrington",new Resources(), R.drawable.planet25, 0),
			new Planet("Derrik",    new Resources(), R.drawable.planet26, 0),
			new Planet("Hailey",    new Resources(), R.drawable.planet27, 0),
	};

	private Resources resources;
	private Resources effect;
	
	private Infrastructure infrastructure;

	private String name;
	private boolean defend = false;

	private int image = -1;
	private int description = -1;
	
	
	public Planet(String name, Resources effect, int image, int description) {
		this.name = name;
		this.image = image;
		this.effect = effect;
		this.description = description;
		this.infrastructure = new Infrastructure();
		this.resources = infrastructure.getResources();
	}

	public boolean isDefend() {
		return defend;
	}

	public Resources getResources() {
		return resources;
	}

	public String getName() {
		return name;
	}

	public Infrastructure getInfrastructure() {
		return infrastructure;
	}

	public int getImage() {
		return image;
	}

	public int getDescription() {
		return description;
	}
}
