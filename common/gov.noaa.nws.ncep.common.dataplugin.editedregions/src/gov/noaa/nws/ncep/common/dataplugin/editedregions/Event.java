package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.dataplugin.PluginDataObject;
import com.raytheon.uf.common.dataplugin.annotations.DataURI;
import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedEventsConstants;

/**
 * The EditedEvents record class stores data that constitutes an event record.
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
@Entity
@SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN, sequenceName = "SWPC_EVENT_SEQ")
@Table(name = "SWPC_EVENT", uniqueConstraints = { @UniqueConstraint(columnNames = {
        "refTime", "type", "observatory", "endDate" }) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@DynamicSerialize
public class Event extends PersistablePluginDataObject {

    private static final long serialVersionUID = 8932954781474251151L;

    public static final String AGE = "age";

    public static final String BEGIN_DATE = "beginDate";

    public static final String BEGIN_Q = "begInq";

    public static final String BEGIN_TIME = "beginTime";

    public static final String BIN = "bin";

    public static final String CHANGE_FLAG = "changeFlag";

    public static final String CODED_TYPE = "codedType";

    public static final String END_DATE = "endDate";

    public static final String END_TIME = "endTime";

    public static final String END_Q = "endq";

    public static final String FREQUENCY = "frequency";

    public static final String ID = "id";

    public static final String INSERT_DTTM = "insert_dttm";

    public static final String LOCATION = "location";

    public static final String MAX_DATE = "maxDate";

    public static final String MAX_TIME = "maxTime";

    public static final String MAX_Q = "maxq";

    public static final String OBSERVATORY = "observatory";

    public static final String OBS_ID = "obsid";

    public static final String PARTICULARS_1 = "particulars1";

    public static final String PARTICULARS_2 = "particulars2";

    public static final String PARTICULARS_3 = "particulars3";

    public static final String PARTICULARS_4 = "particulars4";

    public static final String PARTICULARS_5 = "particulars5";

    public static final String PARTICULARS_6 = "particulars6";

    public static final String PARTICULARS_7 = "particulars7";

    public static final String PARTICULARS_8 = "particulars8";

    public static final String PARTICULARS_9 = "particulars9";

    public static final String PARTICULARS_10 = "particulars10";

    public static final String PUT_DMSD = "putdmsd";

    public static final String PUT_DMSS = "putdmss";

    public static final String REF_TIME = "reftime";

    public static final String QUALITY = "quality";

    public static final String REGION = "region";

    public static final String STATUS_CODE = "statusCd";

    public static final String STATUS_TEXT = "statusText";

    public static final String TYPE = "type";
    
    public static final String COMPOSITE_EVENT_INDICATOR = "COM";

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String age = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Calendar beginDate = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String begInq = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer beginTime = 0;
    
    @Embedded
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(referencedColumnName = "BIN_NUMBER", name = "BIN")
    @DynamicSerializeElement
    @XmlElement   
    private EventBin bin = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer changeFlag = 0;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer codedType = 0;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Calendar endDate = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String endq = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer endTime = 0;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String frequency = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String location = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Calendar maxDate = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String maxq = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer maxTime = 0;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String observatory = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer obsid = 0;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars1 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars2 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars3 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars4 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars5 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars6 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars7 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars8 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars9 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String particulars10 = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer putdmsd = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer putdmss = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String quality = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer region = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private Integer statusCd = 0;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String statusText = null;

    @Column
    @DataURI(position = 1)
    @DynamicSerializeElement
    @XmlAttribute
    private String type = null;

    /**
	 * Default constructor
	 */
    public Event() {

    }
    
    /**
     * @return String
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return Calendar
     */
    public Calendar getBeginDate() {
        return this.beginDate;
    }

    /**
     * @param beginDate
     */
    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return String
     */
    public String getBegInq() {
        return begInq;
    }

    /**
     * @param begInq
     */
    public void setBegInq(String begInq) {
        this.begInq = begInq;
    }

    /**
     * @return Integer
     */
    public Integer getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     */
    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    /**
	 * @return the bin
	 */
	public EventBin getBin() {
		return bin;
	}

	/**
	 * @param bin the bin to set
	 */
	public void setBin(EventBin bin) {
		this.bin = bin;
	}

	/**
     * @return Integer
     */
    public Integer getChangeFlag() {
        return changeFlag;
    }

    /**
     * @param changeFlag
     */
    public void setChangeFlag(Integer changeFlag) {
        this.changeFlag = changeFlag;
    }

    /**
     * @return Integer
     */
    public Integer getCodedType() {
        return codedType;
    }

    /**
     * @param codedType
     */
    public void setCodedType(Integer codedType) {
        this.codedType = codedType;
    }

    /**
     * @return Calendar
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    /**
     * @return String
     */
    public String getEndq() {
        return endq;
    }

    /**
     * @param endq
     */
    public void setEndq(String endq) {
        this.endq = endq;
    }

    /**
     * @return Integer
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * @return String
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * @return String
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return Calendar
     */
    public Calendar getMaxDate() {
        return maxDate;
    }

    /**
     * @param maxDate
     */
    public void setMaxDate(Calendar maxDate) {
        this.maxDate = maxDate;
    }

    /**
     * @return String
     */
    public String getMaxq() {
        return maxq;
    }

    /**
     * @param maxq
     */
    public void setMaxq(String maxq) {
        this.maxq = maxq;
    }

    /**
     * @return Integer
     */
    public Integer getMaxTime() {
        return maxTime;
    }

    /**
     * @param maxTime
     */
    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * @return String
     */
    public String getObservatory() {
        return observatory;
    }

    /**
     * @param observatory
     */
    public void setObservatory(String observatory) {
        this.observatory = observatory;
    }

    /**
     * @return Integer
     */
    public Integer getObsid() {
        return obsid;
    }

    /**
     * @param obsid
     */
    public void setObsid(Integer obsid) {
        this.obsid = obsid;
    }

    /**
     * @return String
     */
    public String getParticulars1() {
        return particulars1;
    }

    /**
     * @param particulars1
     */
    public void setParticulars1(String particulars1) {
        this.particulars1 = particulars1;
    }

    /**
     * @return String
     */
    public String getParticulars10() {
        return particulars10;
    }

    /**
     * @param particulars10
     */
    public void setParticulars10(String particulars10) {
        this.particulars10 = particulars10;
    }

    /**
     * @return String
     */
    public String getParticulars2() {
        return particulars2;
    }

    /**
     * @param particulars2
     */
    public void setParticulars2(String particulars2) {
        this.particulars2 = particulars2;
    }

    /**
     * @return String
     */
    public String getParticulars3() {
        return particulars3;
    }

    /**
     * @param particulars3
     */
    public void setParticulars3(String particulars3) {
        this.particulars3 = particulars3;
    }

    /**
     * @return String
     */
    public String getParticulars4() {
        return particulars4;
    }

    /**
     * @param particulars4
     */
    public void setParticulars4(String particulars4) {
        this.particulars4 = particulars4;
    }

    /**
     * @return String
     */
    public String getParticulars5() {
        return particulars5;
    }

    /**
     * @param particulars5
     */
    public void setParticulars5(String particulars5) {
        this.particulars5 = particulars5;
    }

    /**
     * @return String
     */
    public String getParticulars6() {
        return particulars6;
    }

    /**
     * @param particulars6
     */
    public void setParticulars6(String particulars6) {
        this.particulars6 = particulars6;
    }

    /**
     * @return String
     */
    public String getParticulars7() {
        return particulars7;
    }

    /**
     * @param particulars7
     */
    public void setParticulars7(String particulars7) {
        this.particulars7 = particulars7;
    }

    /**
     * @return String
     */
    public String getParticulars8() {
        return particulars8;
    }

    /**
     * @param particulars8
     */
    public void setParticulars8(String particulars8) {
        this.particulars8 = particulars8;
    }

    /**
     * @return String
     */
    public String getParticulars9() {
        return particulars9;
    }

    /**
     * @param particulars9
     */
    public void setParticulars9(String particulars9) {
        this.particulars9 = particulars9;
    }

    /**
     * @return Integer
     */
    public Integer getPutdmsd() {
        return putdmsd;
    }

    /**
     * @param putdmsd
     */
    public void setPutdmsd(Integer putdmsd) {
        this.putdmsd = putdmsd;
    }

    /**
     * @return Integer
     */
    public Integer getPutdmss() {
        return putdmss;
    }

    /**
     * @param putdmss
     */
    public void setPutdmss(Integer putdmss) {
        this.putdmss = putdmss;
    }

    /**
     * @return String
     */
    public String getQuality() {
        return quality;
    }

    /**
     * @param quality
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * @return Integer
     */
    public Integer getRegion() {
        return region;
    }

    /**
     * @param region
     */
    public void setRegion(Integer region) {
        this.region = region;
    }

    /**
     * @return Integer
     */
    public Integer getStatusCd() {
        return statusCd;
    }

    /**
     * @param statusCd
     */
    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    /**
     * @return String
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * @param statusText
     */
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    /**
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /* (non-Javadoc)
     * @see com.raytheon.uf.common.dataplugin.PluginDataObject#getPluginName()
     */
    @Override
    public String getPluginName() {
        return EditedEventsConstants.PLUGIN_NAME;
    }

    /**
     * Check if the event is a composite i.e. "observatory=COM"
     * 
     * @return boolean
     */
    public boolean isComposite() {
        boolean isComposite = false;

        if (this.observatory != null
                && observatory.trim().equalsIgnoreCase(Event.COMPOSITE_EVENT_INDICATOR)) {
            isComposite = true;
        }

        return isComposite;
    }       
}