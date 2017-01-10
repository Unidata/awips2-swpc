package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.time.LocalDate;
import java.time.LocalTime;

import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;

public class RegionReport extends PersistablePluginDataObject {

    private LocalDate date;

    private LocalTime obstime;

    private String obs;

    private String q;

    private String zeroZLocation;

    private String reportLocation;

    private String lo;

    private String ll;

    private String area;

    private int numSpots;

    private String spotClass;

    private String magClass;

    private String magStr;

    private Region region;

    @Override
    public String getPluginName() {
        // TODO: Use constant.
        return "editedregions";
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the obstime
     */
    public LocalTime getObstime() {
        return obstime;
    }

    /**
     * @param obstime
     *            the obstime to set
     */
    public void setObstime(LocalTime obstime) {
        this.obstime = obstime;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs
     *            the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the q
     */
    public String getQ() {
        return q;
    }

    /**
     * @param q
     *            the q to set
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * @return the zeroZLocation
     */
    public String getZeroZLocation() {
        return zeroZLocation;
    }

    /**
     * @param zeroZLocation
     *            the zeroZLocation to set
     */
    public void setZeroZLocation(String zeroZLocation) {
        this.zeroZLocation = zeroZLocation;
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
     * @return the lo
     */
    public String getLo() {
        return lo;
    }

    /**
     * @param lo
     *            the lo to set
     */
    public void setLo(String lo) {
        this.lo = lo;
    }

    /**
     * @return the ll
     */
    public String getLl() {
        return ll;
    }

    /**
     * @param ll
     *            the ll to set
     */
    public void setLl(String ll) {
        this.ll = ll;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the numSpots
     */
    public int getNumSpots() {
        return numSpots;
    }

    /**
     * @param numSpots
     *            the numSpots to set
     */
    public void setNumSpots(int numSpots) {
        this.numSpots = numSpots;
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
     * @return the magClass
     */
    public String getMagClass() {
        return magClass;
    }

    /**
     * @param magClass
     *            the magClass to set
     */
    public void setMagClass(String magClass) {
        this.magClass = magClass;
    }

    /**
     * @return the magStr
     */
    public String getMagStr() {
        return magStr;
    }

    /**
     * @param magStr
     *            the magStr to set
     */
    public void setMagStr(String magStr) {
        this.magStr = magStr;
    }

    /**
     * @return the region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * @param region
     *            the region to set
     */
    public void setRegion(Region region) {
        this.region = region;
    }

}
