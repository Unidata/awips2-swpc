package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Class that holds the results from execution of the CreateRegion command
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jul 6, 2017            jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 */
@DynamicSerialize
public class GetConsensusFinalResults implements IResults {
    @DynamicSerializeElement
    private boolean populated;

    @DynamicSerializeElement
    private Long observationTime;

    @DynamicSerializeElement
    private String observatory;

    @DynamicSerializeElement
    private Integer region;

    @DynamicSerializeElement
    private Integer latitude;

    @DynamicSerializeElement
    private Integer longitude;

    @DynamicSerializeElement
    private Integer carlon;

    @DynamicSerializeElement
    private Integer extent;

    @DynamicSerializeElement
    private Integer area;

    @DynamicSerializeElement
    private Integer numspots;

    @DynamicSerializeElement
    private String magclass;

    @DynamicSerializeElement
    private String reportLocation;

    @DynamicSerializeElement
    private String report00ZLocation;

    @DynamicSerializeElement
    private String spotClass;

    /**
     * Constructor
     */
    public GetConsensusFinalResults() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults#
     * numResults()
     */
    @Override
    public int numResults() {
        return 0;
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

    /**
     * @return the populated
     */
    public boolean isPopulated() {
        return populated;
    }

    /**
     * @param populated
     *            the populated to set
     */
    public void setPopulated(boolean populated) {
        this.populated = populated;
    }

}
