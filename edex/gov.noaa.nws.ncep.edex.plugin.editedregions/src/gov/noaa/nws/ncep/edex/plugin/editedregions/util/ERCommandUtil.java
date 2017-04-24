package gov.noaa.nws.ncep.edex.plugin.editedregions.util;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Utility class for all command classes
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 12, 2016 R9583     sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
public class ERCommandUtil {		
	
	 /**
     * Clear hours, minutes, seconds and milliseconds of the given Calendar (set
     * to 0).
     * 
     * @param cal
     * @return
     * @throws ParseException
     */
    public static Calendar clearCalendarHHMMSS(Calendar cal) throws ParseException {

        Calendar newCal = Calendar.getInstance();
        newCal.clear();
        newCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        newCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        newCal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));

        return newCal;
    }
    
    /**
   	 * Add "0" in front of the number if it is a 1 digit number  
   	 * 
   	 * @param number
   	 * @return String
   	 */
   	public static String getTwoDigitValueAsString(int number) {
   		
   		String min = String.valueOf(number);
   		
   		if (min.length() == 1) {
   			min = "0" + min;
   		}
   		
   		return min;
   		
   	}
   	
	/**
   	 * Return the concatenation of hours and minutes from the Calendar as an integer
   	 * 
   	 * @param cal
   	 * @return Integer
   	 */
   	public static Integer getHourAndMinuteCombination(Calendar cal) {
   		
   		String value = getTwoDigitValueAsString(cal.get(Calendar.HOUR_OF_DAY))
        + "" + getTwoDigitValueAsString(cal.get(Calendar.MINUTE));
        
		return Integer.parseInt(value);
   		
   	}
}
