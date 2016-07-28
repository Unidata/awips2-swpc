package gov.noaa.nws.ncep.common.swpcrefdb.request;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;
import com.raytheon.uf.common.serialization.comm.IServerRequest;
/**
 * Class for allowing the client to request the back-end to
 * add retrieve satellite tracking status
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 12, 2016  R9583     sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
@DynamicSerialize
@XmlRootElement(name = "SatelliteTrackingStatusRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class SatelliteTrackingStatusRequest implements IServerRequest {
	
	 public static enum SatelliteTrackingStatusRequestType {
		 GET_GOES_SATELLITE, GET_GOES_SATELLITES, GET_GOES_SATELLITE_FOR_INTEGRATED_FLUX
	 }

	@DynamicSerializeElement
    private String instrument = null;

    @DynamicSerializeElement
    private String trackingStatus = null;

    @DynamicSerializeElement
    private Calendar beginDTTM = null;

    @DynamicSerializeElement
    private Calendar endDTTM = null;
    
    @DynamicSerializeElement
    @XmlAttribute(required = true)
    private SatelliteTrackingStatusRequestType requestType;
    
    public SatelliteTrackingStatusRequest() {
    	super();
    }

	/**
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}

	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
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
	 * @return the beginDTTM
	 */
	public Calendar getBeginDTTM() {
		return beginDTTM;
	}

	/**
	 * @param beginDTTM the beginDTTM to set
	 */
	public void setBeginDTTM(Calendar beginDTTM) {
		this.beginDTTM = beginDTTM;
	}

	/**
	 * @return the endDTTM
	 */
	public Calendar getEndDTTM() {
		return endDTTM;
	}

	/**
	 * @param endDTTM the endDTTM to set
	 */
	public void setEndDTTM(Calendar endDTTM) {
		this.endDTTM = endDTTM;
	}

	/**
	 * @return the requestType
	 */
	public SatelliteTrackingStatusRequestType getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(SatelliteTrackingStatusRequestType requestType) {
		this.requestType = requestType;
	}

}
