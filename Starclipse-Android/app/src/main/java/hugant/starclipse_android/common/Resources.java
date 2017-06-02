package hugant.starclipse_android.common;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.lang.SerializationUtils;

/**
 * Allows you to store an array of <b>Subjects</b> and to work with them.
 * 
 * @author Hugant MD
 *
 *@see hugant.starclipse_android.common.Subject
 */
public class Resources implements Cloneable, Serializable {
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	private Boolean isStorage = null;
	private ScaleNumber volume = null;
	private int length = 0;
	
	/**
	 * Create <b>Resources</b>(Storage) in which all <b>Subjects</b> will have
	 * a maximal size volume. If the <b>Subjects</b> are repeat, it will be
	 * added to the previous current.
	 * 
	 * <p>
	 * <strong>Example: </strong>
	 * <pre><code> Resources a = new Resources(new ScaleNumber("100"), "iron",
	 * 	"tree", "gold");// maxValue of iron = 100, tree = 100, gold = 100
	 * Resources a = new Resources(new ScaleNumber("100"), "iron", "iron",
	 * 	"tree", "gold");// maxValue of iron = 200, tree = 100, gold = 100
	 * </code></pre>
	 * @param volume is a <b>ScaleNumber</b> the future maximal size of each <b>Subject</b>.
	 * @param res is a array of string where each string is the type of <b>Subject</b>.
	 */
	public Resources(ScaleNumber volume, String... res) {
		for (String i : res) {
			if (!subjects.contains(new Subject(i, "0"))) {
				subjects.add(new Subject(i, "0", volume.getPrefix() + volume.getPostfix()));
			} else {
				subjects.get(subjects.indexOf(new Subject(i, "0")))
					.addToMaxValue(volume.getPrefix() + volume.getPostfix());
			}
			this.length++;
		}
		this.volume = volume;
		this.isStorage = Boolean.TRUE;
	}
	
	/**
	 * Create <b>Resources</b> in which you can specify the size of each <b>Subject</b>.
	 * If <b>Subject</b> will be repeated then will be generated an IllegalArgumentException.
	 * 
	 * <p>
	 * <strong>Example: </strong>
	 * <pre><code>Resources a = new Resources("iron", "100B"
	 *                            "gold", "100K"
	 *                            "water", "1M");
	 * //iron = 100B, gold = 100K, water = 1M
	 * 
	 * @throws IllegalArgumentException
	 * @param res
	 */
	public Resources(String... res) {
		if (res.length % 2 != 0) {
			throw new IllegalArgumentException("The number of array elements must be even");
		} else {
			for (int i = 0; i < res.length; i += 2) {
				if (subjects.contains(new Subject(res[i], "0"))) {
					subjects.clear();
					throw new IllegalArgumentException("There are two of the same item");
				} else {
					subjects.add(new Subject(res[i], res[i + 1]));
				}
				this.length++;
			}
		}
		isStorage = Boolean.FALSE;
	}


	/**
	 * Adds to the current <b>Resources</b> another <b>Resources</b>.
	 * @param res is a <b>Resources</b> which you want to plus
	 * @return this
	 */
	public Resources add(Resources res) {
		if (res != null) {
			if (canAdd(res)) {
				for (Subject i : res.subjects) {
					this.add(i);
				}
			}
		}
		return this;
	}

	/**
	 * TODO: write javadoc
	 * @param res
	 * @return
	 */
	public boolean canAdd(Resources res) {
		if (res != null) {
			try {
				Resources cloneRes = this.clone();
				for (Subject i : res.subjects) {
					cloneRes.add(i);
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return false;
			} catch (ArithmeticException e) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds to the current <b>Resources</b> another <b>Subject</b>.
	 * If the <b>Subject</b> does not exist, will be generated an 
	 * ArithmeticException.
	 * @param sub is a <b>Subject</b> which you want to plus
	 * @return this
	 */
	public Resources add(Subject sub) {
		try {
			subjects.get(subjects.indexOf(sub)).add(sub);
		} catch (ArithmeticException e) {
			throw new ArithmeticException(e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			subjects.add(sub);
			length++;
		}
		return this;
	}


	/**
	 * TODO: write javadoc
	 * @param sub
	 * @return
	 */
	public boolean canAdd(Subject sub) {
		if (sub != null) {
			try {
				Resources cloneRes = this.clone();
				cloneRes.add(sub);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return false;
			} catch (ArithmeticException e) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Takes away from the current <b>Resources</b> another <b>Resources</b>.
	 * @param res is a <b>Resources</b> which you want to take
	 * @return this
	 */
	public Resources subtract(Resources res) {
		if (res != null) {
			if (canSubtract(res)) {
				for (Subject i : res.subjects) {
					this.subtract(i);
				}
			}
		}
		return this;
	}

	/**
	 * TODO: write javadoc
	 * @param res
	 * @return
	 */
	public boolean canSubtract(Resources res) {
		if (res != null) {
			try {
				Resources cloneRes = this.clone();
				for (Subject i : res.subjects) {
					cloneRes.subtract(i);
				}
			} catch (ArithmeticException e) {
				return false;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * Takes away from the current <b>Resources</b> another <b>Subject</b>.
	 * If the <b>Subject</b> does not exist, will be generated an 
	 * ArithmeticException.
	 * @param sub is a <b>Subject</b> which you want to take
	 * @return this
	 */
	public Resources subtract(Subject sub) {
		try {
			subjects.get(subjects.indexOf(sub)).subtract(sub);
			this.length--;
		} catch (ArithmeticException e) {
			throw new ArithmeticException(e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("No such element exists");
		}
		return this;
	}

	/**
	 * TODO: write javadoc
	 * @param sub
	 * @return
	 */
	public boolean canSubtract(Subject sub) {
		if (sub != null) {
			try {
				Resources cloneRes = this.clone();
				cloneRes.subtract(sub);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return false;
			} catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Multiply each <b>Subject</b> in the <b>Resources</b> on number.
	 * @param number is a <b>String</b> which presented in the form
	 * of <b>ScaleNumber</b>
	 * @return this
	 */
	public Resources multiply(String number) {
		for (Subject i : subjects) {
			i.multiply(new Subject(number));
		}
		return this;
	}
	
	/**
	 * Divide each <b>Subject</b> in the <b>Resources</b> on number.
	 * @param number is a <b>String</b> which presented in the form
	 * of <b>ScaleNumber</b>
	 * @return this
	 */
	public Resources divide(String number) {
		for (Subject i : subjects) {
			i.divide(new Subject(number));
		}
		return this;
	}
	
	/**
	 * Return <b>Subject</b> with the type you specified.
	 * If the <b>Subject</b> does not exist, will be generated an
	 * ArithmeticException
	 * 
	 * @throws ArithmeticException
	 * @param type is a type <b>Subject</b>
	 * @return Subject with the type you specified
	 */
	public Subject get(String type) {
		if (subjects.contains(new Subject(type, "0"))) {
			return subjects.get(subjects.indexOf(new Subject(type, "0")));
		} else {
			throw new ArithmeticException("No such element exists");
		}
	}
	
	/**
	 * Return <b>String</b> array which consists of types of each <b>Subject</b>.
	 * @return temp is a <b>String</b> array
	 */
	public String[] asTypeArray() {
		String temp[] = new String[subjects.size()];
		int index = 0;
		for (Subject i : subjects) {
			temp[index] = i.getType();
			index++;
		}
		return temp;	
	}
	
	/**
	 * "Fills" each <b>Subject</b> to the maximum value.
	 * If it is not storage, will generated UnsupportedOperationException.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void fill() {
		if (this.isStorage) {
			for (Subject i : subjects) {
				i.fill();
			}
		} else {
			throw new UnsupportedOperationException("It is impossible to fill not for storage");
		}
	}
	
	/**
	 * Return <b>true</b> if the <b>Resources</b> is a storage, else return <b>false</b>.
	 * @return isStorage
	 */
	public boolean isStorage() {
		return isStorage;
	}
	
	/**
	 * Return amount of subjects.
	 * 
	 * @return length
	 */
	public int length() {
		return length;
	}
	
	
	/**
	 * Return <b>ScaleNumber</b> volume of <b>Subject</b>.
	 * 
	 * @return volume
	 */
	public ScaleNumber getVolume() {
		return volume;
	}

	/**
	 * Return list of <b>Subject</b>'s.
	 */
	public ArrayList<Subject> getSubjects() {
		return this.subjects;
	}

	/**
	 * Return true if <b>Subject</b> with specified type is present
	 * in the <b>Resources</b>, otherwise return false.
	 */
	public boolean hasSubject(String type) {
		return subjects.contains(new Subject(type, "0"));
	}

	@Override
	public Resources clone() throws CloneNotSupportedException {
		super.clone();
		return (Resources) SerializationUtils.deserialize(SerializationUtils.serialize(this));
	}
}
