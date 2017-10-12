package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * The definition of a Region Consensus record. This class is used to represent
 * Yesterday's Report, Today's Consensus, and Today's Final.
 * 
 * @author alockleigh
 *
 */
@SuppressWarnings("rawtypes")
@Entity
// @SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN,
// sequenceName = "SWPC_REGIONS_SEQ")
@Table(name = "SWPC_REGION_CONSENSUS")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@DynamicSerialize
public class RegionConsensus extends PersistableDataObject {

    public static final String OBSERVATION_TIME = "observationTime";

    public static final String REGION = "region";

    private static final long serialVersionUID = 6589298851235632937L;

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_region_consensus_seq_gen")
    @SequenceGenerator(name = "swpc_region_consensus_seq_gen", sequenceName = "SWPC_REGION_CONSENSUS_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    private long id = 0;

    @Column
    @DynamicSerializeElement
    private Integer regionID;

    @Column
    @DynamicSerializeElement
    private Date createTime;

    @Column
    @DynamicSerializeElement
    private Long observationTime;

    @Column
    @DynamicSerializeElement
    private String observatory;

    @Column
    @DynamicSerializeElement
    private Integer region;

    @Column
    @DynamicSerializeElement
    private Integer latitude;

    @Column
    @DynamicSerializeElement
    private Integer longitude;

    @Column
    @DynamicSerializeElement
    private Integer carlon;

    @Column
    @DynamicSerializeElement
    private Integer extent;

    @Column
    @DynamicSerializeElement
    private Integer area;

    @Column
    @DynamicSerializeElement
    private Integer numspots;

    @Column
    @DynamicSerializeElement
    private String magclass;

    @Column
    @DynamicSerializeElement
    private String reportLocation;

    @Column
    @DynamicSerializeElement
    private String report00ZLocation;

    @Column
    @DynamicSerializeElement
    private String spotClass;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the regionID
     */
    public Integer getRegionID() {
        return this.regionID;
    }

    /**
     * @param regionID
     *            the regionID to set
     */
    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the observationTime
     */
    public Long getObservationTime() {
        return observationTime;
    }

    /**
     * @param observationTime
     *            the observationTime to set
     */
    public void setObservationTime(Long observationTime) {
        this.observationTime = observationTime;
    }

    /**
     * @return the observatory
     */
    public String getObservatory() {
        return observatory;
    }

    /**
     * @param observatory
     *            the observatory to set
     */
    public void setObservatory(String observatory) {
        this.observatory = observatory;
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
    public void setRegion(Integer region) {
        this.region = region;
    }

    /**
     * @return the latitude
     */
    public Integer getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Integer getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the carlon
     */
    public Integer getCarlon() {
        return carlon;
    }

    /**
     * @param carlon
     *            the carlon to set
     */
    public void setCarlon(Integer carlon) {
        this.carlon = carlon;
    }

    /**
     * @return the extent
     */
    public Integer getExtent() {
        return extent;
    }

    /**
     * @param extent
     *            the extent to set
     */
    public void setExtent(Integer extent) {
        this.extent = extent;
    }

    /**
     * @return the area
     */
    public Integer getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(Integer area) {
        this.area = area;
    }

    /**
     * @return the numspots
     */
    public Integer getNumspots() {
        return numspots;
    }

    /**
     * @param numspots
     *            the numspots to set
     */
    public void setNumspots(Integer numspots) {
        this.numspots = numspots;
    }

    /**
     * @return the magclass
     */
    public String getMagclass() {
        return magclass;
    }

    /**
     * @param magclass
     *            the magclass to set
     */
    public void setMagclass(String magclass) {
        this.magclass = magclass;
    }

    /**
     * @return the reportLocation
     */
    public String getReportLocation() {
        return reportLocation;
    }

    /**
     * @param reportLocation
     *            the reportLocation to set
     */
    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    /**
     * @return the report00ZLocation
     */
    public String getReport00ZLocation() {
        return report00ZLocation;
    }

    /**
     * @param report00zLocation
     *            the report00ZLocation to set
     */
    public void setReport00ZLocation(String report00zLocation) {
        report00ZLocation = report00zLocation;
    }

    /**
     * @return the spotClass
     */
    public String getSpotClass() {
        return spotClass;
    }

    /**
     * @param spotClass
     *            the spotClass to set
     */
    public void setSpotClass(String spotClass) {
        this.spotClass = spotClass;
    }

}
