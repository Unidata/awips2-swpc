package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.util.Calendar;

import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

@DynamicSerialize
public class RegionReport extends PersistablePluginDataObject {

    private static final long serialVersionUID = -2348816710419849461L;

    @DynamicSerializeElement
    private Calendar date;

    @DynamicSerializeElement
    private Calendar obstime;

    @DynamicSerializeElement
    private String obs;

    @DynamicSerializeElement
    private String q;

    @DynamicSerializeElement
    private String zeroZLocation;

    @DynamicSerializeElement
    private String reportLocation;

    @DynamicSerializeElement
    private String lo;

    @DynamicSerializeElement
    private String ll;

    @DynamicSerializeElement
    private String area;

    @DynamicSerializeElement
    private int numSpots;

    @DynamicSerializeElement
    private String spotClass;

    @DynamicSerializeElement
    private String magClass;

    @DynamicSerializeElement
    private String magStr;

    @DynamicSerializeElement
    private Region region;

    @Override
    public String getPluginName() {
        // TODO: Use constant.
        return "editedregions";
    }

    /**
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * @return the obstime
     */
    public Calendar getObstime() {
        return obstime;
    }

    /**
     * @param obstime
     *            the obstime to set
     */
    public void setObstime(Calendar obstime) {
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
