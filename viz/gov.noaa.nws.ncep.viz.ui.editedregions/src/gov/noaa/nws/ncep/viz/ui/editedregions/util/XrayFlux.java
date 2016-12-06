package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import java.util.Calendar;

/**
 * Class that represents a GOES XrayFlux
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Apr 1, 2016  R9583      sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
public class XrayFlux {
	
	private float peakFlux = 0f;
	
	private float avgFlux = 0f;
	
	public XrayFlux(float peakFlux, float avgFlux) {
		this.peakFlux = peakFlux;
		this.avgFlux = avgFlux;    	
	}

	/**
	 * @return the peakFlux
	 */
	public float getPeakFlux() {
		return peakFlux;
	}

	/**
	 * @param peakFlux the peakFlux to set
	 */
	public void setPeakFlux(float peakFlux) {
		this.peakFlux = peakFlux;
	}

	/**
	 * @return the avgFlux
	 */
	public float getAvgFlux() {
		return avgFlux;
	}

	/**
	 * @param avgFlux the avgFlux to set
	 */
	public void setAvgFlux(float avgFlux) {
		this.avgFlux = avgFlux;
	}
	
	/**
	 * Calculates the integrated flux value
	 * 
	 * @param startTime
	 * @param endTime
	 * @return Float
	 */
	public Float getIntegratedFlux(Calendar startTime, Calendar endTime) {
		
		Float intFlux = this.avgFlux * (endTime.getTimeInMillis() - startTime.getTimeInMillis())/1000;
        
        return intFlux;
	}
	
	/**
	 * Returns the event class associated with the peak flux
	 * 
	 * @return String
	 */
	public String getEventClass() {
		String eventClass = null;
		float xLowerLimit = 0.0001f;
		float mLowerLimit = 0.00001f;
		float cLowerLimit = 0.000001f;
		float bLowerLimit = 0.0000001f;
		float aLowerLimit = 0.0000001f;
		
		if (this.peakFlux >= xLowerLimit) {				
			eventClass = "X" + String.format("%.1f", (this.peakFlux / xLowerLimit));		
			
		} else if (this.peakFlux >= mLowerLimit && this.peakFlux < xLowerLimit) {		
			eventClass = "M" + String.format("%.1f", (this.peakFlux / mLowerLimit));		
			
		} else if (this.peakFlux >= cLowerLimit && this.peakFlux < mLowerLimit) {		
			eventClass = "C" + String.format("%.1f", (this.peakFlux / cLowerLimit));		
			
		} else if (this.peakFlux >= bLowerLimit && this.peakFlux < cLowerLimit) {		
			eventClass = "B" + String.format("%.1f", (this.peakFlux / bLowerLimit));		
			
		} else if (this.peakFlux < aLowerLimit) {		
			eventClass = "A" + String.format("%.1f", (this.peakFlux / 0.00000001f));		
			
		}
		
		return eventClass;
	}
	
	
}

