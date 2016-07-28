package gov.noaa.nws.ncep.common.swpcrefdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Defines a satellite
 * 
 *<pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 22, 2016 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
@XmlRootElement(name = "Satellite")
@XmlAccessorType(XmlAccessType.NONE)
public class Satellite {

	@DynamicSerializeElement
	private String code = null;
	
	@DynamicSerializeElement
	private String trackingStatus = null;
	
	@DynamicSerializeElement
	private long beginDttm = 0L;
	
	@DynamicSerializeElement
	private long endDttm = 0l;
	
	public Satellite() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param satelliteCode
	 * @param trackingStatus
	 * @param beginDttm
	 * @param endDttm
	 * 
	 */
	public Satellite(String satelliteCode, String trackingStatus, long beginDttm, long endDttm) {
		
		this.code = satelliteCode.toUpperCase();
		this.trackingStatus = trackingStatus.toUpperCase();
		this.beginDttm = beginDttm;
		this.endDttm = endDttm;
		
	}
	
	/**
	 * Is this the primary satellite for the date range
	 * 
	 * @return boolean
	 */
	public boolean isPrimary() {
		if (this.getTrackingStatus().compareToIgnoreCase("PRIMARY") == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Is this the secondary satellite for the date range
	 * 
	 * @return boolean
	 */
	public boolean isSecondary() {
		if (this.getTrackingStatus().compareToIgnoreCase("SECONDARY") == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Is this the tertiary satellite for the date range
	 * 
	 * @return boolean
	 */
	public boolean isTertiary() {
		if (this.getTrackingStatus().compareToIgnoreCase("TERTIARY") == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the trackingStatus
	 */
	public String getTrackingStatus() {
		return trackingStatus;
	}

	/**
	 * @param trackingStatus the trackingStatus to set
	 */
	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}
	
	/**
	 * @return the beginDttm
	 */
	public long getBeginDttm() {
		return beginDttm;
	}
	
	/**
	 * @param beginDttm the beginDttm to set
	 */
	public void setBeginDttm(long beginDttm) {
		this.beginDttm = beginDttm;
	}
	
	/**
	 * @return the endDttm
	 */
	public long getEndDttm() {
		return endDttm;
	}

	/**
	 * @param endDttm the endDttm to set
	 */
	public void setEndDttm(long endDttm) {
		this.endDttm = endDttm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		StringBuffer buff = new StringBuffer();
		
		buff.append("<Satellite>");
		buff.append("<Code>" + this.getCode() + "</Code>");
		buff.append("<TrackingStatus>" + this.getTrackingStatus() + "</TrackingStatus>");
		buff.append("<BeginDTTM>" + this.getBeginDttm() + "</BeginDTTM>");
		buff.append("<EndDTTM>" + this.getEndDttm() + "</EndDTTM>");
		buff.append("</Satellite>");
		
		return buff.toString().trim();
		
	}

}
