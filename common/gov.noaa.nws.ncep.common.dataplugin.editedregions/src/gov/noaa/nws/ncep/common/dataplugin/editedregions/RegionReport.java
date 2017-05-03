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

@Entity
@SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN, sequenceName = "SWPC_REGION_REPORTS_SEQ")
@Table(name = "SWPC_REGION_REPORTS")
//@Table(name = "SWPC_REGION_REPORTS", uniqueConstraints = { @UniqueConstraint(columnNames = {
//        "refTime", "type", "observatory", "endDate" }) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@DynamicSerialize
public class RegionReport extends PersistablePluginDataObject {

	// NOTE: if have a value that is an instance of class
	// that is backed by a db table use the following
	// annotation as an example:
	//
	//
	//	    @Embedded
    //      @ManyToOne(cascade = { CascadeType.REFRESH })
    //      @JoinColumn(referencedColumnName = "BIN_NUMBER", name = "BIN")
    //      @DynamicSerializeElement
    //      @XmlElement
	//		private EventBin bin = null;
	//
	
    private static final long serialVersionUID = -2348816710419849461L;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int station;
    
    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String observatory;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String type;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int quality;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int region;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int latitude;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int reportLongitude;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int longitude;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String reportLocation;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String location;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int carlon;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int extent;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int area;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int numspots;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int zurich;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int penumbra;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String compact;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String spotclass;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int magcode;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private String magclass;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int obsid;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private int reportStatus;

    @Column
    @DynamicSerializeElement
    @XmlAttribute
    private boolean validSpotClass;

    @Override
    public String getPluginName() {
        // TODO: Use constant.
        return "editedregions";
    }

    /**
     * @return the station
     */
    public int getStation() {
        return station;
    }

    /**
     * @param station
     *            the station to set
     */
    public void setStation(int station) {
        this.station = station;
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
     * @return the quality
     */
    public int getQuality() {
        return quality;
    }

    /**
     * @param quality
     *            the quality to set
     */
    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * @return the region
     */
    public int getRegion() {
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
     * @return the latitude
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the reportLongitude
     */
    public int getReportLongitude() {
        return reportLongitude;
    }

    /**
     * @param reportLongitude
     *            the reportLongitude to set
     */
    public void setReportLongitude(int reportLongitude) {
        this.reportLongitude = reportLongitude;
    }

    /**
     * @return the longitude
     */
    public int getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
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
     * @return the location
     */
    public String getLocation() {
        return location;
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
    public int getCarlon() {
        return carlon;
    }

    /**
     * @param carlon
     *            the carlon to set
     */
    public void setCarlon(int carlon) {
        this.carlon = carlon;
    }

    /**
     * @return the extent
     */
    public int getExtent() {
        return extent;
    }

    /**
     * @param extent
     *            the extent to set
     */
    public void setExtent(int extent) {
        this.extent = extent;
    }

    /**
     * @return the area
     */
    public int getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * @return the numspots
     */
    public int getNumspots() {
        return numspots;
    }

    /**
     * @param numspots
     *            the numspots to set
     */
    public void setNumspots(int numspots) {
        this.numspots = numspots;
    }

    /**
     * @return the zurich
     */
    public int getZurich() {
        return zurich;
    }

    /**
     * @param zurich
     *            the zurich to set
     */
    public void setZurich(int zurich) {
        this.zurich = zurich;
    }

    /**
     * @return the penumbra
     */
    public int getPenumbra() {
        return penumbra;
    }

    /**
     * @param penumbra
     *            the penumbra to set
     */
    public void setPenumbra(int penumbra) {
        this.penumbra = penumbra;
    }

    /**
     * @return the compact
     */
    public String getCompact() {
        return compact;
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
        return spotclass;
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
    public int getMagcode() {
        return magcode;
    }

    /**
     * @param magcode
     *            the magcode to set
     */
    public void setMagcode(int magcode) {
        this.magcode = magcode;
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
     * @return the obsid
     */
    public int getObsid() {
        return obsid;
    }

    /**
     * @param obsid
     *            the obsid to set
     */
    public void setObsid(int obsid) {
        this.obsid = obsid;
    }

    /**
     * @return the reportStatus
     */
    public int getReportStatus() {
        return reportStatus;
    }

    /**
     * @param reportStatus
     *            the reportStatus to set
     */
    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * @return the validSpotClass
     */
    public boolean isValidSpotClass() {
        return validSpotClass;
    }

    /**
     * @param validSpotClass
     *            the validSpotClass to set
     */
    public void setValidSpotClass(boolean validSpotClass) {
        this.validSpotClass = validSpotClass;
    }

}
