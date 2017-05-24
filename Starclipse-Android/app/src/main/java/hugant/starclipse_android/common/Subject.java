package hugant.starclipse_android.common;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.SerializationUtils;

import hugant.starclipse_android.R;

/**
 * The class <b>Subject</b> creates the subject with its type, quantity and maximum value.
 * <p>
 * You can create a subject which will have only the value, value and type,
 * value and type and maximum value.
 * <pre><code> Subject a = new Subject("100V");
 * Subject b = new Subject("gold", "10B");
 * Subject c = new Subject("iron", "10J", "100BaM");
 * </pre></code>
 * <p>
 * You can make arithmetic operations on subjects, but on the condition that
 * have the same types.
 * <pre><code> Subject a = new Subject("tree", "10");
 * Subject b = new Subject("tree", "100");
 * a.add(b);// a = 110
 * b.subtract(a);// b = 90
 * <br>
 *
 * @author Hugant MD
 *
 * @see hugant.starclipse_android.common.ScaleNumber
 */
public class Subject implements Cloneable, Serializable {
	public final static String GOLD = "Gold";
	public final static String IRON = "Iron";
	public final static String COAL = "Coal";
	public final static String TREE = "Tree";
	public final static String STONE = "Stone";
	public final static String WATER = "Water";
	public final static String OXYGEN = "Oxygen";
	public final static String ENERGY = "Energy";
	public final static String FOOD = "Food";
	public final static String MONEY = "Money";
	public final static String SOIL = "Soil";

	private final static int GOLD_IMAGE = R.drawable.subject_gold_30dp;
	private final static int IRON_IMAGE = R.drawable.subject_iron_30dp;
	private final static int COAL_IMAGE = R.drawable.subject_coal_30dp;
	private final static int TREE_IMAGE = R.drawable.subject_tree_30dp;
	private final static int STONE_IMAGE = R.drawable.subject_stone_30dp;
	private final static int WATER_IMAGE = R.drawable.subject_water_30dp;
	private final static int OXYGEN_IMAGE = R.drawable.subject_oxygen_30dp;
	private final static int ENERGY_IMAGE = R.drawable.subject_energy_30dp;
	private final static int FOOD_IMAGE = R.drawable.subject_food_30dp;
	private final static int MONEY_IMAGE = R.drawable.subject_money_30dp;
	private final static int SOIL_IMAGE = R.drawable.subject_soil_30dp;
	private final static int RESIDENTS_IMAGE = R.drawable.subject_residents_30dp;

	public final static String[] ALL_RESOURCES = {  GOLD, IRON, COAL, TREE, STONE, WATER, OXYGEN,
													ENERGY, FOOD, MONEY, SOIL};

	public final static String RESIDENTS = "Residents";

	private ScaleNumber value = null;
	private ScaleNumber maxValue = null;
	private String type = null;


	/**
	* Create a <b>Subject</b> which have only value.
	* If value less than zero, will be generated
	* an IllegalArgumentException.
	*
	* @throws IllegalArgumentException
	* @param value is the amount of a subject
	*/
	public Subject(String value) {
		if (new ScaleNumber(value).getPrefix().doubleValue() < 0) {
			throw new IllegalArgumentException("The amount of material cannot be less than zero");
		} else {
			this.value = new ScaleNumber(value);
		}
	}

	/**
	* Create a <b>Subject</b> which have type and value.
	* If value less than zero, will be generated
	* an IllegalArgumentException.
	*
	* @throws IllegalArgumentException
	* @param type is the type of a subject
	* @param value is the amount of a subject
	*/
	public Subject(String type, String value) {
		if (new ScaleNumber(value).getPrefix().doubleValue() < 0) {
			throw new IllegalArgumentException("The amount of material cannot be less than zero");
		} else {
			this.value = new ScaleNumber(value);
		}

		this.type = type;
	}

	/**
	* Create a <b>Subject</b> which have type, value and maximum value.
	* If value or maxValue less than zero, will be generated
	* an IllegalArgumentException.
	*
	* @throws IllegalArgumentException
	* @param type is the type of a subject
	* @param value is the amount of a subject
	* @param maxValue is the maximum amount of a subject
	*/
	public Subject(String type, String value, String maxValue) {
		if (new ScaleNumber(value).getPrefix().doubleValue() < 0) {
			throw new IllegalArgumentException("The amount of material cannot be less than zero");
		} else {
			this.value = new ScaleNumber(value);
		}

		if (maxValue != null) {
			if (new ScaleNumber(maxValue).getPrefix().doubleValue() < 0) {
				throw new IllegalArgumentException("The maximum amount of material cannot be less than zero");
			} else {
				this.maxValue = new ScaleNumber(maxValue);
			}
		}

		this.type = type;
	}

	/**
	 * Adds a <b>Subject</b> to the <b>Subject</b>.
	 * If the have a different type will generate
	 * an ArithmeticException.
	 * If the value exceeds the maximum value, will generate
	 * an ArithmeticException.
	 *
	 * @throws ArithmeticException
	 * @param subject is a Subject which you want to add,
	 *                you can not clone this parameter
	 */
	public Subject add(Subject subject) {
		if (subject.type == null || this.type == null) {
			this.value.add(subject.value);
		} else if (this.type.equals(subject.type)) {
			this.value.add(subject.value);
		} else {
			throw new ArithmeticException("Arithmetic operations can only be done with the same material");
		}

		if (this.maxValue != null && this.value.compareTo(this.maxValue) == 1) {
			this.value = new ScaleNumber(this.getMaxValue());
		}

		return this;
	}

	/**
	 * Take a <b>Subject</b> to the <b>Subject</b>.
	 * If the have a different type will generate
	 * an ArithmeticException.
	 * If the value less than the minimal value, will generate
	 * an ArithmeticException.
	 *
	 * @throws ArithmeticException
	 * @param subject is a <b>Subject</b> which you want to take,
	 *                you can not clone this parameter
	 */
	public Subject subtract(Subject subject) {
		if (subject.type == null || this.type == null) {
			this.value.subtract(subject.value);
		} else if (this.type.equals(subject.type)) {
			this.value.subtract(subject.value);
		} else {
			throw new ArithmeticException("Arithmetic operations can only be done with the same material");
		}

		if (this.value.compareTo("0") == -1) {
			this.value.add(subject.value);
			throw new ArithmeticException("Out of bounds the minimum value");
		}

		return this;
	}

	/**
	 * Multiply a <b>Subject</b> to the <b>Subject</b>.
	 * If the have a different type will generate
	 * an ArithmeticException.
	 * If the value less than the minimal value or
	 * more than maximal value, will generate an ArithmeticException.
	 *
	 * @param subject is a <b>Subject</b> which you want to multiply,
	 *                you can not clone this parameter
	 * @return this
	 */
	public Subject multiply(Subject subject) {
		if (subject.type == null || this.type == null) {
			this.value.multiply(subject.value);
		} else if (this.type.equals(subject.type)) {
			this.value.multiply(subject.value);
		} else {
			throw new ArithmeticException("Arithmetic operations can only be done with the same material");
		}

		if (this.value.compareTo("0") == -1) {
			throw new ArithmeticException("Out of bounds the minimum value");
		} else if (this.maxValue != null && this.value.compareTo(this.maxValue) == 1) {
			throw new ArithmeticException("Out of bounds the maximum value");
		}

		return this;
	}

	/**
	 * Divide a <b>Subject</b> to the <b>Subject</b>.
	 * If the have a different type will generate
	 * an ArithmeticException.
	 * If the value less than the minimal value or
	 * more than maximal value, will generate an ArithmeticException.
	 *
	 * @param subject is a <b>Subject</b> which you want to divide,
	 *                you can not clone this parameter
	 * @return this
	 */
	public Subject divide(Subject subject) {
		if (subject.type == null || this.type == null) {
			this.value.divide(subject.value);
		} else if (this.type.equals(subject.type)) {
			this.value.divide(subject.value);
		} else {
			throw new ArithmeticException("Arithmetic operations can only be done with the same material");
		}

		if (this.value.compareTo("0") == -1) {
			throw new ArithmeticException("Out of bounds the minimum value");
		} else if (this.maxValue != null && this.value.compareTo(this.maxValue) == 1) {
			throw new ArithmeticException("Out of bounds the maximum value");
		}

		return this;
	}

	/**
	 * TODO: write javadoc
	 * @param subject
	 * @return
	 */
	public int compareTo(Subject subject) {
		return this.value.compareTo(subject.value);
	}

	/**
	 * Adds the value to the maximum value of this <b>Subject</b>.
	 * @param subject is a string in the form <b>ScaleNumber</b>
	 */
	public void addToMaxValue(String subject) {
		this.addToMaxValue(new Subject(subject));
	}

	/**
	 * Adds the Subject to the maximum value of this <b>Subject</b>.
	 * @param subject is a <b>Subject</b>
	 */
	public void addToMaxValue(Subject subject) {
		this.maxValue.add(subject.value);
	}


	/**
	 * Set the maximum value.
	 * @param subject is a string in the form <b>ScaleNumber</b>
	 */
	public void setMaxValue(String subject) {
		this.setMaxValue(new Subject(subject));
	}

	/**
	 * Set the maximum value.
	 * @param subject is a <b>Subject</b>
	 */
	public void setMaxValue(Subject subject) {
		this.maxValue.setPrefix(subject.value.getPrefix());
		this.maxValue.setPostfix(subject.value.getPostfix());
	}

	/**
	 * "Fill" <b>Subject</b> to the maximum value.
	 */
	public void fill() {
		if (maxValue != null) {
			this.value.setPrefix(this.maxValue.getPrefix());
			this.value.setPostfix(this.maxValue.getPostfix());
		} else {
			throw new NullPointerException("Max value has not been initialized");
		}

	}

	/**
	 * Return the string in the form <b>ScaleNumber</b> rounded down to the nearest tenth.
	 * <br>
	 * <pre><code>  new Subject("145T").getValue();// "145T"
	 *  new Subject("1.001V").getValue();// "1V"
	 *  new Subject("1.10Z").getValue();// "1.1Z"
	 *  new Subject("1.1234J").getValue();// "1.12J"
	 * </pre><code>
	 * @return string in the form <b>ScaleNumber</b>
	 */
	public String getValue() {
		return value.getPrefix().setScale(2, BigDecimal.ROUND_DOWN).toString().
				replaceAll("(\\.00)|(0$)", "") + value.getPostfix();
	}

	/**
	 * Return the string in the form <b>ScaleNumber</b>.
	 * <br>
	 * <pre><code> new Subject("gold", "145T", "1J").getValue();// "1J"
	 * </pre><code>
	 * @return string in the form <b>ScaleNumber</b>
	 */
	public String getMaxValue() {
		return this.maxValue == null ? null :
				maxValue.getPrefix().setScale(2, BigDecimal.ROUND_DOWN).toString().
				replaceAll("(\\.00)|(0$)", "") + maxValue.getPostfix();
	}


	/**
	 * Return the string the type of this object.
	 * @return string the type of this object
	 */
	public String getType() {
		return this.type == null ? "no type" : this.type;
	}

	/**
	 * Compares <b>Subject</b>'s by name.
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Subject) {
			if (this.type.equals(((Subject) o).type))
				return true;
		}
		return false;
	}

	public int getImage() {
		switch (this.type) {
			case GOLD:
				return GOLD_IMAGE;
			case IRON:
				return IRON_IMAGE;
			case COAL:
				return COAL_IMAGE;
			case TREE:
				return TREE_IMAGE;
			case STONE:
				return STONE_IMAGE;
			case WATER:
				return WATER_IMAGE;
			case OXYGEN:
				return OXYGEN_IMAGE;
			case ENERGY:
				return ENERGY_IMAGE;
			case FOOD:
				return FOOD_IMAGE;
			case MONEY:
				return MONEY_IMAGE;
			case SOIL:
				return SOIL_IMAGE;
			case RESIDENTS:
				return RESIDENTS_IMAGE;
		}

		return -1;
	}

	/**
	 * Return the string in the form: type + " " + number.
	 * <pre><code> new Subject("oxygen", "10B").toString();// oxygen 10B
	 * </pre></code>
	 */
	@Override
	public String toString() {
		return (this.type == null ? "no type" : this.type) + " " + this.getValue();
	}

	/**
	 * Return cloned <b>Subject</b>.
	 *
	 * @return Subject
	 */
	@Override
	public Subject clone() throws CloneNotSupportedException {
		super.clone();
		return (Subject) SerializationUtils.deserialize(SerializationUtils.serialize(this));
	}
}
