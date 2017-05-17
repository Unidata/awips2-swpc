package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.dataplugin.PluginDataObject;
import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;

@Entity
@SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN, sequenceName = "SWPC_REGION_REPORTS_SEQ")
@Table(name = "SWPC_REGION_REPORTS")
// @Table(name = "SWPC_REGION_REPORTS", uniqueConstraints = {
// @UniqueConstraint(columnNames = {
// "refTime", "type", "observatory", "endDate" }) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@DynamicSerialize
public class RegionReport extends PersistablePluginDataObject {

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

    public static final String STATION = "station";

    // private static final String OBSERVATORY = "observatory";
    // private static final String TYPE = "type";
    // private static final String QUALITY = "quality";
    public static final String REGION = "region";
    // private static final String LATITUDE = "latitude";
    // private static final String REPORT_LONGITUDE = "reportLongitude";
    // private static final String LONGITUDE = "longitude";
    // private static final String REPORT_LOCATION = "reportLocation";
    // private static final String LOCATION = "location";
    // private static final String CAR_LON = "carlon";
    // private static final String EXTENT = "extent";
    // private static final String AREA = "area";
    // private static final String NUM_SPOTS = "numspots";
    // private static final String ZURICH = "zurich";
    // private static final String PENUMBRA = "penumbra";
    // private static final String COMPACT = "compact";
    // private static final String SPOT_CLASS = "spotclass";
    // private static final String MAG_CODE = "magcode";
    // private static final String MAG_CLASS = "magclass";
    // private static final String OBS_ID = "obsid";
    // private static final String REPORT_STATUS = "reportStatus";
    // private static final String VALID_SPOT_CLASS = "validSpotClass";

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Station")
    private Integer station;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Observatory")
    private String observatory;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Type")
    private String type;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Quality")
    private Integer quality;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Region")
    private Integer region;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Latitude")
    private Integer latitude;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Report-Longitude")
    private Integer reportLongitude;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Longitude")
    private Integer longitude;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Report-Location")
    private String reportLocation;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Location")
    private String location;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Carlon")
    private Integer carlon;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Extent")
    private Integer extent;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Area")
    private Integer area;

    @Column
    @DynamicSerializeElement
    @XmlElement
    private Integer numspots;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Zurich")
    private Integer zurich;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Penumbra")
    private Integer penumbra;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Compact")
    private String compact;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Spotclass")
    private String spotclass;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Magcode")
    private Integer magcode;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Magclass")
    private String magclass;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Obsid")
    private Integer obsid;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "Report-Status")
    private Integer reportStatus;

    @Column
    @DynamicSerializeElement
    @XmlElement(name = "ValidSpotClass")
    private boolean validSpotClass;

    @Override
    public String getPluginName() {
        return EditedRegionsConstants.PLUGIN_NAME;
    }

    /**
     * @return the station
     */
    public Integer getStation() {
        return this.station;
    }

    /**
     * @param station
     *            the station to set
     */
    public void setStation(Integer station) {
        this.station = station;
    }

    /**
     * @return the observatory
     */
    public String getObservatory() {
        return this.observatory;
    }

    /**
     * @param observatory
     *            the observatory to set
     */
    public void setObservatory(String observatory) {
        this.observatory = observatory;
    }

    /**
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the quality
     */
    public Integer getQuality() {
        return this.quality;
    }

    /**
     * @param quality
     *            the quality to set
     */
    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    /**
     * @return the region
     */
    public Integer getRegion() {
        return this.region;
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
        return this.latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the reportLongitude
     */
    public Integer getReportLongitude() {
        return this.reportLongitude;
    }

    /**
     * @param reportLongitude
     *            the reportLongitude to set
     */
    public void setReportLongitude(Integer reportLongitude) {
        this.reportLongitude = reportLongitude;
    }

    /**
     * @return the longitude
     */
    public Integer getLongitude() {
        return this.longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the reportLocation
     */
    public String getReportLocation() {
        return this.reportLocation;
    }

    /**
     * @param reportLocation
     *            the reportLocation to set
     */
    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the carlon
     */
    public Integer getCarlon() {
        return this.carlon;
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
        return this.extent;
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
        return this.area;
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
        return this.numspots;
    }

    /**
     * @param numspots
     *            the numspots to set
     */
    public void setNumspots(Integer numspots) {
        this.numspots = numspots;
    }

    /**
     * @return the zurich
     */
    public Integer getZurich() {
        return this.zurich;
    }

    /**
     * @param zurich
     *            the zurich to set
     */
    public void setZurich(Integer zurich) {
        this.zurich = zurich;
    }

    /**
     * @return the penumbra
     */
    public Integer getPenumbra() {
        return this.penumbra;
    }

    /**
     * @param penumbra
     *            the penumbra to set
     */
    public void setPenumbra(Integer penumbra) {
        this.penumbra = penumbra;
    }

    /**
     * @return the compact
     */
    public String getCompact() {
        return this.compact;
    }

    /**
     * @param compact
     *            the compact to set
     */
    public void setCompact(String compact) {
        this.compact = compact;
    }

    /**
     * @return the spotclass
     */
    public String getSpotclass() {
        return this.spotclass;
    }

    /**
     * @param spotclass
     *            the spotclass to set
     */
    public void setSpotclass(String spotclass) {
        this.spotclass = spotclass;
    }

    /**
     * @return the magcode
     */
    public Integer getMagcode() {
        return this.magcode;
    }

    /**
     * @param magcode
     *            the magcode to set
     */
    public void setMagcode(Integer magcode) {
        this.magcode = magcode;
    }

    /**
     * @return the magclass
     */
    public String getMagclass() {
        return this.magclass;
    }

    /**
     * @param magclass
     *            the magclass to set
     */
    public void setMagclass(String magclass) {
        this.magclass = magclass;
    }

    /**
     * @return the obsid
     */
    public Integer getObsid() {
        return this.obsid;
    }

    /**
     * @param obsid
     *            the obsid to set
     */
    public void setObsid(Integer obsid) {
        this.obsid = obsid;
    }

    /**
     * @return the reportStatus
     */
    public Integer getReportStatus() {
        return this.reportStatus;
    }

    /**
     * @param reportStatus
     *            the reportStatus to set
     */
    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * @return the validSpotClass
     */
    public boolean isValidSpotClass() {
        return this.validSpotClass;
    }

    /**
     * @param validSpotClass
     *            the validSpotClass to set
     */
    public void setValidSpotClass(boolean validSpotClass) {
        this.validSpotClass = validSpotClass;
    }

}
