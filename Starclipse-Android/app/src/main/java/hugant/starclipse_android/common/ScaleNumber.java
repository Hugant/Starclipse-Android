package hugant.starclipse_android.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.lang.SerializationUtils;

/**
 * The class <b>ScaleNumber</b> allows you to translate number in a more convenient
 * form for the user.
 *
 * <p>
 * <strong>What number is converted: </strong>
 * <br>
 * <pre>    1000 = 1K;
 *  1000K = 1M;// Million
 *  1000M = 1B;// Billion
 *  1000B = 1T;// Trillion
 *  1000T = 1V;// Villion
 *  1000V = 1Z;// Zillion
 *  1000Z = 1J;// Jillion
 *  1000J = 1BaM;// BaMillion
 *  1000BaM = 1BaB;// BaBillion
 *  ...
 *  1000BaJ = 1CaM// CaMillion
 *  ...
 *  1000CaJ = 1DaM// DaMillion
 *  ...
 *  ...
 *  ...
 *  1000VaZ = 1ZaJ;// ZaJillion
 *  The number more than 100ZaJ not be converted.
 *
 * @author Hugant MD
 */
public class ScaleNumber implements Cloneable, Serializable {
	/**
	* <b>Contains the names of large numbers: <br></b>
	* K = 1000, <br>
	* M - Million = 100K, <br>
	* B - Billion = 100M, <br>
	* T - Trillion = 100B, <br>
	* V - Villion = 100T, <br>
	* Z - Zillion = 100V, <br>
	* J - Jillion = 100Z, <br>
	* BaM - BaMillion = 100J, <br>
	* BaB - BaBillion = 100BaM, <br>
	* BaT - BaTrillion = 100BaB, <br>
	* BaV - BaVillion = 100BaT, <br>
	* BaZ - BaZillion = 100BaV, <br>
	* BaJ - BaJillion = 100BaZ, <br>
	* CaM - CaMillion = 100BaJ, <br>
	* CaB - CaBillion = 100CaM, <br>
	* CaT - CaTrillion = 100CaB, <br>
	* CaV - CaVillion = 100CaT, <br>
	* CaZ - CaZillion = 100CaV, <br>
	* CaJ - CaJillion = 100CaZ; <br>
	* and more...
	*/
	private static final String[] POSTFIX_MAS = new String[]{ "", "K", "M", "B", "T", "V", "Z",   "J",
													         "BaM", "BaB", "BaT", "BaV", "BaZ", "BaJ",
													         "CaM", "CaB", "CaT", "CaV", "CaZ", "CaJ",
													         "DaM", "DaB", "DaT", "DaV", "DaZ", "DaJ",
													         "FaM", "FaB", "FaT", "FaV", "FaZ", "FaJ",
													         "GaM", "GaB", "GaT", "GaV", "GaZ", "GaJ",
													         "HaM", "HaB", "HaT", "HaV", "HaZ", "HaJ",
													         "JaM", "JaB", "JaT", "JaV", "JaZ", "JaJ",
													         "KaM", "KaB", "KaT", "KaV", "KaZ", "KaJ",
													         "LaM", "LaB", "LaT", "LaV", "LaZ", "LaJ",
													         "MaM", "MaB", "MaT", "MaV", "MaZ", "MaJ",
													         "NaM", "NaB", "NaT", "NaV", "NaZ", "NaJ",
													         "PaM", "PaB", "PaT", "PaV", "PaZ", "PaJ",
													         "RaM", "RaB", "RaT", "RaV", "RaZ", "RaJ",
													         "SaM", "SaB", "SaT", "SaV", "SaZ", "SaJ",
													         "TaM", "TaB", "TaT", "TaV", "TaZ", "TaJ",
													         "VaM", "VaB", "VaT", "VaV", "VaZ", "VaJ",
													         "ZaM", "ZaV", "ZaT", "ZaV", "ZaZ", "ZaJ"};

	private static final String PATTERN_PREFIX = getPrefixPattern();
	private static final String PATTERN_POSTFIX = getPostfixPattern();
	
	private BigDecimal prefix = new BigDecimal("0");
	private String postfix = "";

	/**
	* Create number type of <b>ScaleNumber</b> and convert it to the form
	* prefix + postfix.
	*
	* @throws IllegalArgumentException If number has an irregular form
	* @param number the <b>String</b> in the form: number[postfix]("1M", "1B", "0.24T")
	*/
	public ScaleNumber(String number) {
		if (number != null && number.matches("^-?(\\d+|\\d+[.]\\d+)(([" + PATTERN_PREFIX + "]a)"
				+ "?[" + PATTERN_POSTFIX + "])?$")) {
			for (int i = POSTFIX_MAS.length - 1; i > 0; i--) {
				if (number.contains(POSTFIX_MAS[i])) {
					this.prefix = 
							BigDecimal.valueOf(Double.parseDouble(number.substring(0, number.indexOf(POSTFIX_MAS[i]))));
					this.postfix = number.substring(number.indexOf(POSTFIX_MAS[i]));
					this.checkingValidity();
					return;
				}
			}
			
			this.prefix = BigDecimal.valueOf(Double.parseDouble(number));
			this.checkingValidity();
			
		} else {
			throw new IllegalArgumentException("Incorrect number");
		}
	}
	
	
	/**
	* Add <b>ScaleNumber</b> to this <b>ScaleNumber</b>.
	* 
	* @param number the <b>String</b>, which you want to plus.
	*/
	public ScaleNumber plus(String number) {
		return this.plus(new ScaleNumber(number));
	}
	
	
	/**
	 * Add <b>ScaleNumber</b> to this <b>ScaleNumber</b>.
	 *
	 * @param number the <b>ScaleNumber</b>, which you want to plus.
	 */
	public ScaleNumber plus(ScaleNumber number) {
		this.prefix = number.clone().transferTo(this.postfix).add(this.prefix);
		this.checkingValidity();
		return this;
	}
	
	
	/**
	* Takes the <b>ScaleNumber</b> from this <b>ScaleNumber</b>.
	* 
	* @param number the string, which you want take.
	*/
	public ScaleNumber subtract(String number) {
		return this.subtract(new ScaleNumber(number));
	}
	
	
	/**
	 * Takes the <b>ScaleNumber</b> from this <b>ScaleNumber</b>.
	 *
	 * @param number the <b>ScaleNumber</b>, which you want take.
	 */
	public ScaleNumber subtract(ScaleNumber number) {
		this.prefix = this.prefix.subtract(number.clone().transferTo(this.postfix));
		this.checkingValidity();
		return this;
	}
	
	
	/**
	 * Multiplies the <b>ScaleNumber</b> with another <b>ScaleNumber</b>.
	 * 
	 * @param number the String, which you want to multiply
	 */
	public ScaleNumber multiply(String number) {
		return this.multiply(new ScaleNumber(number));
	}
	
	
	/**
	 * Multiplies the the <b>ScaleNumber</b> with another the <b>ScaleNumber</b>.
	 * 
	 * @param number the <b>ScaleNumber</b>, which you want to multiply
	 */
	public ScaleNumber multiply(ScaleNumber number) {
		this.prefix = this.prefix.multiply(number.clone().transferTo(POSTFIX_MAS[0]));
		this.checkingValidity();
		return this;
	}
	
	
	/**
	 * Divides a <b>ScaleNumber</b> by another <b>ScaleNumber</b>.
	 * 
	 * @param number the String, which you want to divide
	 */
	public ScaleNumber divide(String number) {
		return this.divide(new ScaleNumber(number));
	}
	
	/**
	 * Divides a <b>ScaleNumber</b> by another <b>ScaleNumber</b>.
	 * Parameter <b>number</b> should be cloned.
	 * 
	 * @param number the <b>ScaleNumber</b>, which you want to divide
	 */
	public ScaleNumber divide(ScaleNumber number) {
		this.prefix = this.transferTo(POSTFIX_MAS[0]).divide(number.clone().transferTo(POSTFIX_MAS[0]));
		this.checkingValidity();
		return this;
	}

	/**
	 * Transfer the <b>ScaleNumber</b> to specified system(prefix).
	 * @param system is system which you want to transfer
	 * @return <b>ScaleNumber</b> in the specified system(prefix)
	 */
	private BigDecimal transferTo(String system) {
		// number * 10^(this.postfix - system) * 3
		this.prefix = this.prefix.multiply(
				BigDecimal.valueOf(10).pow((Arrays.asList(POSTFIX_MAS).indexOf(this.postfix) -
						Arrays.asList(POSTFIX_MAS).indexOf(system)) * 3));
		this.postfix = system;

		return this.prefix;
	}
	
	
	/**
	 * Compare this <b>ScaleNumber</b> to the string.
	 *
	 * @param number the string to compare with.
	 */
	public int compareTo(String number) {
		return this.compareTo(new ScaleNumber(number));
	}
	
	
	/**
	 * Compare this <b>ScaleNumber</b> to the <b>ScaleNumber</b>.
	 *
	 * @param number the <b>ScaleNumber</b> to compare with.
	 */
	public int compareTo(ScaleNumber number) {
		number = number.clone();
		number.transferTo(this.postfix);

		if (this.prefix.compareTo(number.prefix) == -1){
			return -1;
		} else if(this.prefix.compareTo(number.prefix) == 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * Transfer the <b>ScaleNumber</b> to form NNN.DDD+.
	 */
	private void checkingValidity() {
		while (this.prefix.abs().compareTo(BigDecimal.valueOf(1)) == -1 &&
				!this.postfix.equals(POSTFIX_MAS[0])) {
			this.prefix = this.prefix.multiply(BigDecimal.valueOf(1000));
			this.postfix = POSTFIX_MAS[Arrays.asList(POSTFIX_MAS).indexOf(this.postfix) - 1];
		}
		
		while (this.prefix.abs().compareTo(BigDecimal.valueOf(999)) == 1 && 
				!this.postfix.equals(POSTFIX_MAS[POSTFIX_MAS.length - 1])) {
			this.prefix = this.prefix.divide(BigDecimal.valueOf(1000));
			this.postfix = POSTFIX_MAS[Arrays.asList(POSTFIX_MAS).indexOf(this.postfix) + 1];
		}
	}
	
	/**
	 * Return the <b>BigDecimal</b> number, this prefix.
	 *
	 * @return prefix of this <b>ScaleNumber</b>
	 */
	public BigDecimal getPrefix() {
		return this.prefix;	
	}
	
	/**
	 * Return the system of the number, this postfix.
	 *
	 * @return postfix of this <b>ScaleNumber</b>
	 */
	public String getPostfix() {
		return this.postfix;
	}
	
	/**
	 * Set prefix to this <b>ScaleNumber</b>.
	 *
	 * @param prefix the number, which replaced this prefix.
	 */
	public void setPrefix(BigDecimal prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * Set postfix to this <b>ScaleNumber</b>.
	 *
	 * @param postfix the string, which replaced this postfix.
	 */
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	
	/**
	 * Return string in the form prefix + postfix.
	 */
	@Override
	public String toString() {
		return this.prefix + this.postfix;
	}

	/**
	 * Patterns for regular expressions.
	 * @return prefix pattern
	 */
	private static String getPrefixPattern() {
		String out = "";
		for (String i : POSTFIX_MAS) {
			if (!(i.length() < 2) && !out.contains(i.charAt(0) + "")) {
				out += i.charAt(0);
			}
		}
		return out;
	}

	/**
	 * Patterns for regular expressions.
	 * @return postfix pattern
	 */
	private static String getPostfixPattern() {
		String out = "";
		for (String i : POSTFIX_MAS) {
			if (!i.equals(POSTFIX_MAS[0]) && !out.contains(i.charAt(i.length() - 1) + "")) {
				out += i.charAt(i.length() - 1);
			}
		}
		return out;
	}


	/**
	 * Clone the <b>ScaleNumber</b>.
	 *
	 * @return a clone of this instance.
	 */
	@Override
	public ScaleNumber clone() {
		return (ScaleNumber) SerializationUtils.deserialize(SerializationUtils.serialize(this));
	}
}
