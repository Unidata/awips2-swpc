package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.dataplugin.PluginDataObject;
import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants.REGION_REPORT_CHANGE_TYPE;

/**
 * Class representing a Region Report
 * 
 * @author jtravis
 *
 */
@Entity
@SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN, sequenceName = "SWPC_REGION_HISTORY_REPORTS_SEQ")
@Table(name = "SWPC_REGION_HISTORY_REPORTS")
// @Table(name = "SWPC_REGION_REPORTS", uniqueConstraints = {
// @UniqueConstraint(columnNames = {
// "refTime", "type", "observatory", "endDate" }) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@DynamicSerialize
public class RegionHistoryReport extends PersistablePluginDataObject {

    public static final String REGION_REPORT_ID = "RegionReportID";

    // NOTE: if have a value that is an instance of class
    // that is backed by a db table use the following
    // annotation as an example:
    //
    //
    // @Embedded
    // @ManyToOne(cascade = { CascadeType.REFRESH })
    // @JoinColumn(referencedColumnName = "BIN_NUMBER", name = "BIN")
    // @DynamicSerializeElement
    // @XmlElement
    // private EventBin bin = null;
    //

    private static final long serialVersionUID = -2348816710419849461L;

    @Column
    @DynamicSerializeElement
    @XmlAttribute(name = "HistoryID")
    private Integer historyId; // PK

    @Column
    @DynamicSerializeElement
    @XmlAttribute(name = "RegionReportID")
    private Integer regionReportId; // fk

    @Column
    @DynamicSerializeElement
    @XmlAttribute(name = "ModifiedField")
    private String modifiedField = null;

    @Column
    @DynamicSerializeElement
    @XmlAttribute(name = "fieldValueBefore")
    private String fieldValueBefore;

    @Column
    @DynamicSerializeElement
    @XmlAttribute(name = "fieldValueCurrent")
    private String fieldValueCurrent;

    @Column
    @DynamicSerializeElement
    @XmlAttribute(name = "TimeOfChange")
    private long timeOfChange;

    @Column
    @DynamicSerializeElement
    @XmlAttribute(name = "TypeOfChange")
    private REGION_REPORT_CHANGE_TYPE typeOfChange = null;

    // TODO is this necessary?
    @Override
    public String getPluginName() {
        return EditedRegionsConstants.PLUGIN_NAME;
    }

    /**
     * @return the historyId
     */
    public Integer getHistoryId() {
        return this.historyId;
    }

    /**
     * @param historyId
     *            the historyId to set
     */
    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    /**
     * @return the regionReportId
     */
    public Integer getRegionReportId() {
        return this.regionReportId;
    }

    /**
     * @param regionReportId
     *            the regionReportId to set
     */
    public void setRegionReportId(Integer regionReportId) {
        this.regionReportId = regionReportId;
    }

    /**
     * @return the modifiedField
     */
    public String getModifiedField() {
        return this.modifiedField;
    }

    /**
     * @param modifiedField
     *            the modifiedField to set
     */
    public void setModifiedField(String modifiedField) {
        this.modifiedField = modifiedField;
    }

    /**
     * @return the fieldValueBefore
     */
    public String getFieldValueBefore() {
        return this.fieldValueBefore;
    }

    /**
     * @param fieldValueBefore
     *            the fieldValueBefore to set
     */
    public void setFieldValueBefore(String fieldValueBefore) {
        this.fieldValueBefore = fieldValueBefore;
    }

    /**
     * @return the fieldValueCurrent
     */
    public String getFieldValueCurrent() {
        return fieldValueCurrent;
    }

    /**
     * @param fieldValueCurrent
     *            the fieldValueCurrent to set
     */
    public void setFieldValueCurrent(String fieldValueCurrent) {
        this.fieldValueCurrent = fieldValueCurrent;
    }

    /**
     * @return the timeOfChange
     */
    public long getTimeOfChange() {
        return this.timeOfChange;
    }

    /**
     * @param timeOfChange
     *            the timeOfChange to set
     */
    public void setTimeOfChange(long timeOfChange) {
        this.timeOfChange = timeOfChange;
    }

    /**
     * @return the typeOfChange
     */
    public REGION_REPORT_CHANGE_TYPE getTypeOfChange() {
        return this.typeOfChange;
    }

    /**
     * @param typeOfChange
     *            the typeOfChange to set
     */
    public void setTypeOfChange(REGION_REPORT_CHANGE_TYPE typeOfChange) {
        this.typeOfChange = typeOfChange;
    }

}
