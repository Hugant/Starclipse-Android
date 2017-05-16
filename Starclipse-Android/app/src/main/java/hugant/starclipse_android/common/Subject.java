package hugant.starclipse_android.common;

import java.math.BigDecimal;

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
public class Subject {
	public final static String GOLD = "gold";
	public final static String IRON = "iron";
	public final static String COAL = "coal";
	public final static String TREE = "tree";
	public final static String STONE = "stone";
	public final static String WATER = "water";
	public final static String OXYGEN = "oxygen";
	public final static String ENERGY = "energy";
	public final static String FOOD = "food";
	public final static String MONEY = "money";
	public final static String SOIL = "soil";

	public final static String[] ALL_RESOURCES = {  GOLD, IRON, COAL, TREE, STONE, WATER, OXYGEN,
													ENERGY, FOOD, MONEY, SOIL};

	public final static String RESIDENTS = "residents";

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
		
		this.type = (type == null ? null : type.toLowerCase());
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
		
		this.type = (type == null ? null : type.toLowerCase());
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
	 * @param subject is a <b>Subject</b> which you want to take
	 */
	public Subject minus(Subject subject) {
		if (subject.type == null || this.type == null) {
			this.value.minus(subject.value);
		} else if (this.type.equals(subject.type)) {
			this.value.minus(subject.value);
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
	 * @param subject is a <b>Subject</b> which you want to multiply
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
	 * @param subject is a <b>Subject</b> which you want to divide
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
		this.value.setPrefix(this.maxValue.getPrefix());
		this.value.setPostfix(this.maxValue.getPostfix());
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
		return new Subject(this.type, this.getValue(), this.getMaxValue());
	}
}
