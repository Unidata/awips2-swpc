package gov.noaa.nws.ncep.common.dataplugin.editedevents;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class representing a bin
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Aug 5, 2015  R9583       jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
@Embeddable
@Entity
@Table(name = "SWPC_EVENT_BIN")
@DynamicSerialize
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EventBin extends PersistableDataObject implements Serializable {

	private static final long serialVersionUID = -5728138554493977533L;

	public static final String BEGIN_DATE_TIME = "beginDTTM";

	public static final String BIN_NUMBER = "binNumber";

	public static final String CFI = "cfi";

	public static final String END_DATE_TIME = "endDTTM";

	public static final String LATITUDE = "lat";

	public static final String LONGITUDE = "lon";

	public static final String MAX_DATE_TIME = "maxDTTM";

	public static final String REGION_CODE = "region";

	public static final String FSP_CTM_FLAG = "rspCtm";

	public static final String TYPE = "type";

	public static final int BIN_NUMBER_START = 10;

	public static final int BIN_NUMBER_END = 9990;

	public static final int BIN_NUMBER_INCREMENT = 10;

	@Id
	@DynamicSerializeElement
	@Column(name = "BIN_NUMBER", unique = true, nullable = false)
    @XmlAttribute
	private Integer binNumber;

	@Column(name = "BEGIN_DTTM", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private Calendar beginDTTM = null;

	@Column(name = "MAX_DTTM", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private Calendar maxDTTM = null;

	@Column(name = "END_DTTM", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private Calendar endDTTM = null;

	@DynamicSerializeElement
    @XmlAttribute
	private String type = null;
    
	@Column(name = "REGION_CODE", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private int region;

	@Column(name = "LATITUDE", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private int lat;

	@Column(name = "LONGITUDE", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private int lon;

	@Column(name = "CFI", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private double cfi;

	@Column(name = "RSP_CTM", unique = false, nullable = true)
	@DynamicSerializeElement
    @XmlAttribute
	private boolean rspCtm;

	/**
	 * Default Constructor
	 */
	public EventBin() {
	}

	/**
	 * @return the binNumber
	 */
	public Integer getBinNumber() {
		return binNumber;
	}

	/**
	 * @param binNumber
	 *            the binNumber to set
	 */
	public void setBinNumber(Integer binNumber) {
		this.binNumber = binNumber;
	}

	/**
	 * @return the beginDTTM
	 */
	public Calendar getBeginDTTM() {
		return beginDTTM;
	}

	/**
	 * @param beginDTTM
	 *            the beginDTTM to set
	 */
	public void setBeginDTTM(Calendar beginDTTM) {
		this.beginDTTM = beginDTTM;
	}

	/**
	 * @return the maxDTTM
	 */
	public Calendar getMaxDTTM() {
		return maxDTTM;
	}

	/**
	 * @param maxDTTM
	 *            the maxDTTM to set
	 */
	public void setMaxDTTM(Calendar maxDTTM) {
		this.maxDTTM = maxDTTM;
	}

	/**
	 * @return the endDTTM
	 */
	public Calendar getEndDTTM() {
		return endDTTM;
	}

	/**
	 * @param endDTTM
	 *            the endDTTM to set
	 */
	public void setEndDTTM(Calendar endDTTM) {
		this.endDTTM = endDTTM;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the region
	 */
	public Integer getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(int region) {
		this.region = region;
	}

	/**
	 * @return the lat
	 */
	public int getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(int lat) {
		this.lat = lat;
	}

	/**
	 * @return the lon
	 */
	public int getLon() {
		return lon;
	}

	/**
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(int lon) {
		this.lon = lon;
	}

	/**
	 * @return the cfi
	 */
	public double getCfi() {
		return cfi;
	}

	/**
	 * @param cfi
	 *            the cfi to set
	 */
	public void setCfi(double cfi) {
		this.cfi = cfi;
	}

	/**
	 * @return the rspCtm flag
	 */
	public boolean isRspCtm() {
		return rspCtm;
	}

	/**
	 * @param rspCtm
	 *            the rspCtm flag to set
	 */
	public void setRspCtm(boolean rspCtm) {
		this.rspCtm = rspCtm;
	}

}
