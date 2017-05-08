package hugant.starclipse_android.common;

import java.io.Serializable;

/**
 * The Timer class allows you to create a time counter(timer).
 * <br>
 * @author Hugant MD
 */
public class Timer implements Serializable {

	/**The number of milliseconds in the day {@value #DAY}ms*/
	public static final long DAY = 86400000L;
	/**The number of milliseconds in the hour {@value #HOUR}ms*/
	public static final long HOUR = 3600000L;
	/**The number of milliseconds in the minute {@value #MINUTE}ms*/
	public static final long MINUTE = 60000L;
	/**The number of milliseconds in the second {@value #SECOND}ms*/
	public static final long SECOND = 1000L;
	
	private long duration = 0;
	private long finish = 0;
	private String phrase = "";
	
	/**
     * Creates a timer a certain amount of time specified in the parameters. 
     * 
     * <p>
     * <strong>Parameters: </strong>
     * <pre>  <b>duration</b> the number of the milliseconds.
     *  <b>phrase</b> the phrase which will be returned at the end of the timer.</pre>
     * 
     * <p>
     * <strong>Examples: </strong>
     * <pre>
     * <code> Timer timer = new Timer(1000, "Claim");// 1s
     *  Timer timer = new Timer(Timer.HOUR * 4, "Claim");// 4h
     *  Timer timer = new Timer(Timer.DAY * 2 + Timer.MINUTE * 50, "Claim");// 2d 50m
     *  Timer timer = new Timer(Timer.HOUR * 48, "Claim");// 2d 
     * </code></pre>
     */
	public Timer(long duration, String phrase) {
		this.duration = duration;
		this.phrase = phrase;
	}
	
	
	/**
	 * Starts counting timer.
	 */
	public void start() {
		this.finish = new java.util.Date().getTime() + this.duration;
	}
	
	
	/**
	 * Return the remaining of the timer in the format <b><code>1d 9h 31m 5s</code></b>.
	 * <br>
	 * If the timer has finished the counting will return <b><code><i>your phrase</i></code></b>.
	 * <br>
	 * If the timer has no starting counting will generated UnsupportedOperationException</b>.
	 * 
	 * @exception UnsupportedOperationException
	 * 
	 * @return String in the format:<b> <code>(dd)d (hh)h (mm)m (ss)s</code></b>,
	 * 		   if the timer has finished to count then it will return <i>your phrase</i>,
	 * 		   if you did not start the timer, pop up UnsupportedOperationException.
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
	 * <p>
	 * dd - days<br>
	 * hh - hours<br>
	 * mm - minutes<br>
	 * ss - seconds<br>
	 * 
	 * <p>
	 * <strong>Example:</strong>
	 * <pre>
	 * <code> new Timer(1000).toString(seconds: ss);// seconds: 1
	 *  new Timer(Timer.Day + Timer.MINUTE * 30).toString(ddd hhh mmm sss);// 1d 0h 30m 0s
	 *  </code></pre>
	 * <br>
	 * @param format is your format of the output
	 * <br>
	 * @return String in your <b><code>format</code></b>,
	 * 		   if the timer has finished to count then it will return <i>your phrase</i>,
	 * 		   if you did not start the timer, pop up UnsupportedOperationException.
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
}
