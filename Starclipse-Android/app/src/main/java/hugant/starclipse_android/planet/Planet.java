package hugant.starclipse_android.planet;

import hugant.starclipse_android.R;
import hugant.starclipse_android.infrastructure.Infrastructure;
import hugant.starclipse_android.common.Resources;
import hugant.starclipse_android.common.Subject;

public class Planet {
	public static final Planet[] PLANETS = {
			new Planet("Caroline",  new Resources(), R.drawable.planet1, -1),
			new Planet("Amine",     new Resources(), R.drawable.planet2, -1),
			new Planet("Kate",      new Resources(), R.drawable.planet3, -1),
			new Planet("Emily",     new Resources(), R.drawable.planet4, -1),
			new Planet("Hannah",    new Resources(), R.drawable.planet5, -1),
			new Planet("Brianna",   new Resources(), R.drawable.planet6, -1),
			new Planet("Hailey",    new Resources(), R.drawable.planet7, -1),
			new Planet("Kaylee",    new Resources(), R.drawable.planet8, -1),
			new Planet("Taylor",    new Resources(), R.drawable.planet9, -1),
			new Planet("Ashley",    new Resources(), R.drawable.planet10, -1),
			new Planet("Lauren",    new Resources(), R.drawable.planet11, -1),
			new Planet("Kayla",     new Resources(), R.drawable.planet12, -1),
			new Planet("Madeline",  new Resources(), R.drawable.planet13, -1),
			new Planet("Matthew",   new Resources(), R.drawable.planet14, -1),
			new Planet("Joseph",    new Resources(), R.drawable.planet15, -1),
			new Planet("Joshua",    new Resources(), R.drawable.planet16, -1),
			new Planet("Andrew",    new Resources(), R.drawable.planet17, -1),
			new Planet("Ryan",      new Resources(), R.drawable.planet18, -1),
			new Planet("Kayla",     new Resources(), R.drawable.planet19, -1),
			new Planet("Hoggarth",  new Resources(), R.drawable.planet20, -1),
			new Planet("Allford",   new Resources(), R.drawable.planet21, -1),
			new Planet("Baldwin",   new Resources(), R.drawable.planet22, -1),
			new Planet("Number 21",    new Resources(), R.drawable.planet23, -1),
			new Planet("Carroll",   new Resources(), R.drawable.planet24, -1),
			new Planet("Carrington",new Resources(), R.drawable.planet25, -1),
			new Planet("Derrik",    new Resources(), R.drawable.planet26, -1),
			new Planet("Hailey",    new Resources(), R.drawable.planet27, -1),
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
		if (image == -1) {
			return R.drawable.ic_dashboard_black_24dp;
		}
		return image;
	}

	public int getDescription() {
		if (description == -1) {
			return R.string.description_not_found;
		}
		return description;
	}
}
