package hugant.starclipse_android.common;

import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

/**
 * The Timer class allows you to create a time counter.
 *
 * @author Hugant MD
 * @version 1.1
 */
public class Timer implements Cloneable, Serializable {

	/**The number of milliseconds in the day: {@value}*/
	public static final long DAY = 86400000L;
	/**The number of milliseconds in the hour: {@value}*/
	public static final long HOUR = 3600000L;
	/**The number of milliseconds in the minute: {@value}*/
	public static final long MINUTE = 60000L;
	/**The number of milliseconds in the second: {@value}*/
	public static final long SECOND = 1000L;
	
	private long duration = 0;
	private long finish = 0;
	private String phrase = "";
	
	/**
     * Creates a timer a certain amount of time specified in the parameters. 
     *
	 * @param duration The number of the milliseconds the timer is working
	 *
	 * @param phrase The <b>String</b> which will be returned at he end of
	 *               the timer
     */
	public Timer(long duration, String phrase) {
		this.duration = duration;
		this.phrase = phrase;
	}
	
	
	/**
	 * Starts the <b>Timer</b>.
	 */
	public void start() {
		this.finish = new java.util.Date().getTime() + this.duration;
	}
	
	
	/**
	 * Return the remaining of the timer in the format <b><code>1d 9h 31m 5s</code></b>.
	 * If the timer has finished the counting will return your <b>phrase</b>.
	 * 
	 * @throws UnsupportedOperationException If you did not start the timer
	 * 
	 * @return <b>String</b> in the format:<b><code>(dd)d (hh)h (mm)m (ss)s</code></b>,
	 * 		   if the timer has finished to count then it will return your <b>phrase</b>
	 */
	@Override
	public String toString() {
		final long timer = this.finish - new java.util.Date().getTime();
		
		if (timer > 0) {
			return  (getDays(timer)    == 0 ? "" : getDays(timer)    + "d ") + 
					(getHours(timer)   == 0 ? "" : getHours(timer)   + "h ") + 
					(getMinutes(timer) == 0 ? "" : getMinutes(timer) + "m ") + 
					(getSeconds(timer) + "s");
		} else if (finish == 0) {
			throw new UnsupportedOperationException("You did not start the timer");
		}
		
		return phrase;
	}
	
	
	/**
	 * Return the remaining of the timer in the your format.
	 *
	 * @param format is your format of the output. dd - days, hh - hours, mm - minutes
	 *               ss - seconds
	 *
	 * @throws UnsupportedOperationException If you did not start the timer
	 *
	 * @return <b>String</b> in your format, if the timer has finished to count then it
	 *         will return your <b>phrase</b>
	 */
	public String toString(String format) {
		final long timer = this.finish - new java.util.Date().getTime();
		
		if (timer > 0) {
			format = format.replace("dd", getDays(timer) + "");
			format = format.replace("hh", getHours(timer) + "");
			format = format.replace("mm", getMinutes(timer) + "");
			format = format.replace("ss", getSeconds(timer) + "");
			return format;
		} else if (finish == 0) {
			throw new UnsupportedOperationException("You did not start the timer");
		}
		
		return phrase;
	}
	
	/**
	 * Return true if the timer is work(time > 0), otherwise return false.
	 */
	public boolean isWork() {
		return (this.finish - new java.util.Date().getTime()) > 0;
	}
	
	private long getDays(long time) {
		return time / DAY;
	}
	
	private long getHours(long time) {
		return (time % DAY) / HOUR;
	}
	
	private long getMinutes(long time) {
		return ((time % DAY) % HOUR) / MINUTE;
	}
	
	private long getSeconds(long time) {
		return (((time % DAY) % HOUR) % MINUTE) / SECOND;
	}

	/**
	 * Clone the <b>Timer</b>.
	 *
	 * @return a clone of this instance.
	 */
	@Override
	protected Timer clone() {
		return (Timer) SerializationUtils.deserialize(SerializationUtils.serialize(this));
	}
}
