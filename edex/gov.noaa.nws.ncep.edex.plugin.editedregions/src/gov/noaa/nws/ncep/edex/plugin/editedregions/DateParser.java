package gov.noaa.nws.ncep.edex.plugin.editedregions;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.DateParserException;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * Date Parser
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Dec 12, 2015  R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
abstract class DateParser {

	/**
	 * @param rawData
	 * @param startRange
	 * @param endRange
	 * @return
	 * @throws DateParserException
	 */
	public static Calendar stringToCalendar(String rawData, int startRange,
			int endRange) throws DateParserException {

		Calendar c = null;
		String rawTime = null;
		String rawDate = null;
		String rawDateTime = rawData.substring(startRange, endRange).trim();

		if (rawDateTime.compareToIgnoreCase("NULL") == 0) {
			return null;
		}

		StringTokenizer st1 = new StringTokenizer(rawDateTime, " ");

		if (st1.countTokens() != 2) {
			throw new DateParserException(
					"FAILURE - Expecting 1 space (MONTH-DAY-YEAR<space>TIME) "
							+ "for the date to split the time from the day");
		}

		rawDate = st1.nextToken(); // the rawDate value
		rawTime = st1.nextToken(); // the rawtime value

		Hashtable<String, Integer> dateValues = DateParser
				.getDateValues(rawDate);
		Hashtable<String, Integer> timeValues = DateParser
				.getTimeValues(rawTime);

		c = Calendar.getInstance();
		c.set(Calendar.YEAR, dateValues.get("YEAR"));
		c.set(Calendar.MONTH, (dateValues.get("MONTH") - 1));
		c.set(Calendar.DAY_OF_MONTH, dateValues.get("DAY_OF_MONTH"));
		c.set(Calendar.HOUR_OF_DAY, timeValues.get("HOUR"));
		c.set(Calendar.MINUTE, timeValues.get("MINUTE"));
		c.set(Calendar.SECOND, timeValues.get("SECOND"));
		c.set(Calendar.MILLISECOND, timeValues.get("MILLISECOND"));

		return c;

	}

	/**
	 * @param rawTime
	 * @return
	 * @throws DateParserException
	 */
	private static Hashtable<String, Integer> getTimeValues(String rawTime)
			throws DateParserException {

		int hour = 0;
		int minute = 0;
		int second = 0;
		int milliSecond = 0;
		StringTokenizer st1 = new StringTokenizer(rawTime, ":");
		StringTokenizer st2 = null;
		Hashtable<String, Integer> values = new Hashtable<String, Integer>();

		if (st1.countTokens() != 3) {
			throw new DateParserException("FAILURE - Expecting 3 " + '"'
					+ " : " + '"'
					+ " parameters (HOUR:MINUTES:SECONDS.MILLISECONDS) "
					+ "for the time to split the values");
		}

		hour = Integer.valueOf(st1.nextToken()).intValue();
		minute = Integer.valueOf(st1.nextToken()).intValue();

		st2 = new StringTokenizer(st1.nextToken(), ".");

		if (st2.countTokens() != 2) {
			throw new DateParserException("FAILURE - Expecting 2 " + '"'
					+ " . " + '"' + " parameters (SECONDS.MILLISECONDS) "
					+ "for the time to split the values");
		}

		second = Integer.valueOf(st2.nextToken()).intValue();
		milliSecond = Integer.valueOf(st2.nextToken()).intValue();

		values.put("HOUR", hour);
		values.put("MINUTE", minute);
		values.put("SECOND", second);
		values.put("MILLISECOND", milliSecond);

		return values;

	}

	/**
	 * @param rawDate
	 * @return
	 * @throws DateParserException
	 */
	private static Hashtable<String, Integer> getDateValues(String rawDate)
			throws DateParserException {

		int month = 0;
		int dayOfMonth = 0;
		int year = 0;
		StringTokenizer st = new StringTokenizer(rawDate, "-");
		Hashtable<String, Integer> values = new Hashtable<String, Integer>();

		if (st.countTokens() != 3) {
			throw new DateParserException("FAILURE - Expecting 3 " + '"'
					+ " - " + '"' + " parameters (MONTH-DAY-YEAR) "
					+ "for the date to split the values");
		}

		year = Integer.valueOf(st.nextToken()).intValue();
		month = Integer.valueOf(st.nextToken()).intValue();
		dayOfMonth = Integer.valueOf(st.nextToken()).intValue();

		values.put("YEAR", year);
		values.put("MONTH", month);
		values.put("DAY_OF_MONTH", dayOfMonth);

		return values;

	}
}
