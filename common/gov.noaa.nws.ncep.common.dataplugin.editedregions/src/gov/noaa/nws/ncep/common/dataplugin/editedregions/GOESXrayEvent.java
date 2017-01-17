package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.raytheon.uf.common.dataplugin.PluginDataObject;
import com.raytheon.uf.common.dataplugin.annotations.DataURI;
import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;

/**
 * 
 * Class representing a GOES Xray Event
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 5, 2015  R9583       jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
@Entity
@SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN, sequenceName = "SWPC_GOES_XRAY_EVENT_SEQ")
@Table(name = "SWPC_GOES_XRAY_EVENT", uniqueConstraints = @UniqueConstraint(columnNames = {
        "CURRENT_DTTM", "SATELLITE" }))
public class GOESXrayEvent extends PersistablePluginDataObject {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    
    public static final String BEGIN_DTTM = "beginDTTM";

    public static final String BEGIN_XR_LONG = "beginXRLong";

    public static final String BEGIN_XR_SHORT = "beginXRShort";

    public static final String BEGIN_X_RATIO = "beginXRatio";

    public static final String CURRENT_DTTM = "currentDTTM";

    public static final String CURRENT_INT_XR_LONG = "currentIntXRLong";

    public static final String CURRENT_INT_XR_SHORT = "currentIntXRShort";

    public static final String CURRENT_XR_LONG = "currentXRLong";

    public static final String CURRENT_XR_SHORT = "currentXRShort";

    public static final String CURRENT_X_RATIO = "currentXRatio";

    public static final String DESCRIPTION = "description";

    public static final String END_DTTM = "endDTTM";

    public static final String END_XR_LONG = "endXRLong";

    public static final String END_XR_SHORT = "endXRShort";

    public static final String END_X_RATIO = "endXRatio";

    public static final String INSERT_DTTM = "insertDTTM";

    public static final String MAX_CLASS = "maxClass";

    public static final String MAX_DTTM = "maxDTTM";

    public static final String MAX_EMISSION_MEAS = "maxEmissionMeas";

    public static final String MAX_TEMP = "maxTemp";

    public static final String MAX_XR_LONG = "maxXRLong";

    public static final String MAX_XR_SHORT = "maxXRShort";

    public static final String MAX_X_RATIO = "maxXRatio";

    public static final String SATELLITE = "satellite";

    public static final String PROCESSED = "processed";

    @Column(name = "CURRENT_DTTM", unique = false, nullable = false)
    @DynamicSerializeElement
    private Calendar currentDTTM = null;

    @Column(name = "INSERT_DTTM", unique = false, nullable = false)
    @DynamicSerializeElement
    private Calendar insertDTTM = null;

    @DataURI(position = 1)
    @Column(name = "SATELLITE", unique = false, nullable = false)
    @DynamicSerializeElement
    private String satellite = "";

    //@DataURI(position = 2)
    @Column(name = "BEGIN_DTTM", unique = false, nullable = false)
    @DynamicSerializeElement
    private Calendar beginDTTM = null;

    @Column(name = "BEGIN_XR_SHORT", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double beginXRShort = null;

    @Column(name = "BEGIN_XR_LONG", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double beginXRLong = null;

    @Column(name = "BEGIN_X_RATIO", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double beginXRatio = null;

    @Column(name = "CURRENT_XR_SHORT", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double currentXRShort = null;

    @Column(name = "CURRENT_XR_LONG", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double currentXRLong = null;

    @Column(name = "CURRENT_X_RATIO", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double currentXRatio = null;

    @Column(name = "CURRENT_INT_XR_SHORT", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double currentIntXRShort = null;

    @Column(name = "CURRENT_INT_XR_LONG", precision = 8, scale = 2, unique = false, nullable = false)
    @DynamicSerializeElement
    private Double currentIntXRLong = null;

    @Column(name = "MAX_DTTM", unique = false, nullable = true)
    @DynamicSerializeElement
    private Calendar maxDTTM = null;

    @Column(name = "MAX_XR_SHORT", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double maxXRShort = null;

    @Column(name = "MAX_XR_LONG", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double maxXRLong = null;

    @Column(name = "MAX_X_RATIO", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double maxXRatio = null;

    @Column(name = "MAX_TEMP", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double maxTemp = null;

    @Column(name = "MAX_EMISSION_MEAS", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double maxEmissionMeas = null;

    @Column(name = "MAX_CLASS", unique = false, nullable = true)
    @DynamicSerializeElement
    private String maxClass = null;

    @Column(name = "END_DTTM", unique = false, nullable = true)
    @DynamicSerializeElement
    private Calendar endDTTM = null;

    @Column(name = "END_XR_SHORT", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double endXRShort = null;

    @Column(name = "END_XR_LONG", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double endXRLong = null;

    @Column(name = "END_X_RATIO", precision = 8, scale = 2, unique = false, nullable = true)
    @DynamicSerializeElement
    private Double endXRatio = null;

    @Column(name = "DESCRIPTION", unique = false, nullable = false)
    @DynamicSerializeElement
    private String description = null;
    
    @Column(name = "PROCESSED", unique = false, nullable = false)
    @DynamicSerializeElement
    private boolean processed = false;

    /**
	 * 
	 */
    public GOESXrayEvent() {
    }

    /**
     * @return the currentDTTM
     */
    public Calendar getCurrentDTTM() {
        return currentDTTM;
    }

    /**
     * @param currentDTTM
     *            the currentDTTM to set
     */
    public void setCurrentDTTM(Calendar currentDTTM) {
        this.currentDTTM = currentDTTM;
    }

    /**
     * @return the insertDTTM
     */
    public Calendar getInsertDTTM() {
        return insertDTTM;
    }

    /**
     * @param insertDTTM
     *            the insertDTTM to set
     */
    public void setInsertDTTM(Calendar insertDTTM) {
        this.insertDTTM = insertDTTM;
    }

    /**
     * @return the satellite
     */
    public String getSatellite() {
        return satellite;
    }

    /**
     * @param satellite
     *            the satellite to set
     */
    public void setSatellite(String satellite) {
        this.satellite = satellite;
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
     * @param beginXRShort
     *            the beginXRShort to set
     */
    public void setBeginXRShort(Double beginXRShort) {
        this.beginXRShort = beginXRShort;
    }

    /**
     * @return the beginXRShort
     */
    public Double getBeginXRShort() {
        return beginXRShort;
    }

    /**
     * @param beginXRLong
     *            the beginXRLong to set
     */
    public void setBeginXRLong(Double beginXRLong) {
        this.beginXRLong = beginXRLong;
    }

    /**
     * @return the beginXRLong
     */
    public Double getBeginXRLong() {
        return beginXRLong;
    }

    /**
     * @return the beginXRatio
     */
    public Double getBeginXRatio() {
        return beginXRatio;
    }

    /**
     * @param beginXRatio
     *            the beginXRatio to set
     */
    public void setBeginXRatio(Double beginXRatio) {
        this.beginXRatio = beginXRatio;
    }

    /**
     * @return the currentXRShort
     */
    public Double getCurrentXRShort() {
        return currentXRShort;
    }

    /**
     * @param currentXRShort
     *            the currentXRShort to set
     */
    public void setCurrentXRShort(Double currentXRShort) {
        this.currentXRShort = currentXRShort;
    }

    /**
     * @return the currentXRLong
     */
    public Double getCurrentXRLong() {
        return currentXRLong;
    }

    /**
     * @param currentXRLong
     *            the currentXRLong to set
     */
    public void setCurrentXRLong(Double currentXRLong) {
        this.currentXRLong = currentXRLong;
    }

    /**
     * @return the currentXRatio
     */
    public Double getCurrentXRatio() {
        return currentXRatio;
    }

    /**
     * @param currentXRatio
     *            the currentXRatio to set
     */
    public void setCurrentXRatio(Double currentXRatio) {
        this.currentXRatio = currentXRatio;
    }

    /**
     * @return the currentIntXRShort
     */
    public Double getCurrentIntXRShort() {
        return currentIntXRShort;
    }

    /**
     * @param currentIntXRShort
     *            the currentIntXRShort to set
     */
    public void setCurrentIntXRShort(Double currentIntXRShort) {
        this.currentIntXRShort = currentIntXRShort;
    }

    /**
     * @return the currentIntXRLong
     */
    public Double getCurrentIntXRLong() {
        return currentIntXRLong;
    }

    /**
     * @param currentIntXRLong
     *            the currentIntXRLong to set
     */
    public void setCurrentIntXRLong(Double currentIntXRLong) {
        this.currentIntXRLong = currentIntXRLong;
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
     * @return the maxXRShort
     */
    public Double getMaxXRShort() {
        return maxXRShort;
    }

    /**
     * @param maxXRShort
     *            the maxXRShort to set
     */
    public void setMaxXRShort(Double maxXRShort) {
        this.maxXRShort = maxXRShort;
    }

    /**
     * @return the maxXRLong
     */
    public Double getMaxXRLong() {
        return maxXRLong;
    }

    /**
     * @param maxXRLong
     *            the maxXRLong to set
     */
    public void setMaxXRLong(Double maxXRLong) {
        this.maxXRLong = maxXRLong;
    }

    /**
     * @return the maxXRatio
     */
    public Double getMaxXRatio() {
        return maxXRatio;
    }

    /**
     * @param maxXRatio
     *            the maxXRatio to set
     */
    public void setMaxXRatio(Double maxXRatio) {
        this.maxXRatio = maxXRatio;
    }

    /**
     * @return the maxTemp
     */
    public Double getMaxTemp() {
        return maxTemp;
    }

    /**
     * @param maxTemp
     *            the maxTemp to set
     */
    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    /**
     * @return the maxEmissionMeas
     */
    public Double getMaxEmissionMeas() {
        return maxEmissionMeas;
    }

    /**
     * @param maxEmissionMeas
     *            the maxEmissionMeas to set
     */
    public void setMaxEmissionMeas(Double maxEmissionMeas) {
        this.maxEmissionMeas = maxEmissionMeas;
    }

    /**
     * @return the maxClass
     */
    public String getMaxClass() {
        return maxClass;
    }

    /**
     * @param maxClass
     *            the maxClass to set
     */
    public void setMaxClass(String maxClass) {
        this.maxClass = maxClass;
    }

    /**
     * @return the endXRShort
     */
    public Double getEndXRShort() {
        return endXRShort;
    }

    /**
     * @param endXRShort
     *            the endXRShort to set
     */
    public void setEndXRShort(Double endXRShort) {
        this.endXRShort = endXRShort;
    }

    /**
     * @return the endXRLong
     */
    public Double getEndXRLong() {
        return endXRLong;
    }

    /**
     * @param endXRLong
     *            the endXRLong to set
     */
    public void setEndXRLong(Double endXRLong) {
        this.endXRLong = endXRLong;
    }

    /**
     * @return the endXRatio
     */
    public Double getEndXRatio() {
        return endXRatio;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getEndDTTM() {
        return endDTTM;
    }

    public void setEndDTTM(Calendar endDTTM) {
        this.endDTTM = endDTTM;
    }
    
    public void setEndXRatio(Double endXRatio) {
        this.endXRatio = endXRatio;
    }    
    
    public boolean getProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	@Override
    public String getPluginName() {
        return EditedRegionsConstants.PLUGIN_NAME;
    }

}
