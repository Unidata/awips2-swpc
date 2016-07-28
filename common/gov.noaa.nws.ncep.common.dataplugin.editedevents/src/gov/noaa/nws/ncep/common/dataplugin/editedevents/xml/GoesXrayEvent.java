/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 */
package gov.noaa.nws.ncep.common.dataplugin.editedevents.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * The GoesXrayEventReport represents a "goes-xray-event-report" element in the
 * input xml file.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 9, 2015  R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
@DynamicSerialize
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "goes-xray-event-report")
public class GoesXrayEvent {

    /**
     * Current time of data
     */
    @DynamicSerializeElement
    @XmlAttribute(name = "current-time")
    protected Date currentTime;

    /**
     * Source of data (satellite)
     */
    @DynamicSerializeElement
    @XmlAttribute
    protected String source;

    /**
     * Insert time of data
     */
    @DynamicSerializeElement
    @XmlElement(name = "insert-time")
    protected Date insertTime;

    /**
     * Start date and time of the event
     */
    @DynamicSerializeElement
    @XmlElement(name = "begin-time")
    protected Date beginTime;

    /**
     * XRS short wavelength channel irradiance (0.05 - 0.4 nm) at the start of
     * the event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "begin-xrshort")
    protected Double beginXrShort;

    /**
     * XRS long wavelength channel irradiance (0.1-0.8 nm) at the start of the
     * event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "begin-xrlong")
    protected Double beginXrLong;

    /**
     * The ratio of XRS short wavelength channel irradiance to XRS long
     * wavelength channel irradiance at the start of the event.
     */
    @DynamicSerializeElement
    @XmlElement(name = "begin-xratio")
    protected Double beginXRatio;

    /**
     * XRS short wavelength channel irradiance (0.05 - 0.4 nm) for the latest
     * measurement of the event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "current-xrshort")
    protected Double currentXrShort;

    /**
     * XRS long wavelength channel irradiance (0.1-0.8 nm) for the latest
     * measurement of the event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "current-xrlong")
    protected Double currentXrLong;

    /**
     * The ratio of XRS short wavelength channel irradiance to XRS long
     * wavelength channel irradiance at the latest measurement time.
     */
    @DynamicSerializeElement
    @XmlElement(name = "current-xratio")
    protected Double currentXRatio;

    /**
     * The integrated XRS short wavelength channel irradiance (0.05 - 0.4 nm)
     * flux from the beginning of the event until the current time. Units =
     * J/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "current-int-xrshort")
    protected Double currentIntXrShort;

    /**
     * The integrated XRS long wavelength channel irradiance (0.1-0.8 nm) flux
     * from the beginning of the flare until the current time. Units = J/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "current-int-xrlong")
    protected Double currentIntXrLong;

    /**
     * Event maximum date and time
     */
    @DynamicSerializeElement
    @XmlElement(name = "max-time")
    protected Date maxTime;

    /**
     * XRS short wavelength channel irradiance (0.05 - 0.4 nm) at the time of
     * the maximum flux of the event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "max-xrshort")
    protected Double maxXrShort;

    /**
     * XRS long wavelength channel irradiance (0.1-0.8 nm) at the time of the
     * maximum flux of the event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "max-xrlong")
    protected Double maxXrLong;

    /**
     * The ratio of XRS short wavelength channel irradiance to XRS long
     * wavelength channel irradiance at the time of the maximum flux value of
     * the event
     */
    @DynamicSerializeElement
    @XmlElement(name = "max-xratio")
    protected Double maxXRatio;

    /**
     * Maximum temperature at the time of the maximum flux value of the event.
     * Units = K
     */
    @DynamicSerializeElement
    @XmlElement(name = "max-temp")
    protected Double maxTemp;

    /**
     *
     */
    @DynamicSerializeElement
    @XmlElement(name = "max-emission-meas")
    protected Double maxEmissionMeas;

    /**
     * A translation of the maximum long flux value into a known scale: A x <
     * 10-7 B 10-7 <= x < 10-6 C 10-6 <= x < 10-5 M 10-5 <= x < 10-4 X 10-4 <= x
     */
    @DynamicSerializeElement
    @XmlElement(name = "max-class")
    protected String maxClass;

    /**
     * Event end date time
     */
    @DynamicSerializeElement
    @XmlElement(name = "end-time")
    protected Date endTime;

    /**
     * XRS short wavelength channel irradiance (0.05 - 0.4 nm) at the end time
     * of the event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "end-xrshort")
    protected Double endXrShort;

    /**
     * XRS long wavelength channel irradiance (0.1-0.8 nm) at the end time of
     * the event. Units = W/m^2
     */
    @DynamicSerializeElement
    @XmlElement(name = "end-xrlong")
    protected Double endXrLong;

    /**
     * XRS short wavelength channel irradiance to XRS long wavelength channel
     * irradiance at the end of the event.
     */
    @DynamicSerializeElement
    @XmlElement(name = "end-xratio")
    protected Double endXRatio;

    public GoesXrayEvent() {
        super();
    }

    /**
     * @return the currentTime
     */
    public Date getCurrentTime() {
        return currentTime;
    }

    /**
     * @param currentTime
     *            the currentTime to set
     */
    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the insertTime
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     *            the insertTime to set
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return the beginTime
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     *            the beginTime to set
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return the beginXrShort
     */
    public Double getBeginXrShort() {
        return beginXrShort;
    }

    /**
     * @param beginXrShort
     *            the beginXrShort to set
     */
    public void setBeginXrShort(Double beginXrShort) {
        this.beginXrShort = beginXrShort;
    }

    /**
     * @return the beginXrLong
     */
    public Double getBeginXrLong() {
        return beginXrLong;
    }

    /**
     * @param beginXrLong
     *            the beginXrLong to set
     */
    public void setBeginXrLong(Double beginXrLong) {
        this.beginXrLong = beginXrLong;
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
     * @return the currentXrShort
     */
    public Double getCurrentXrShort() {
        return currentXrShort;
    }

    /**
     * @param currentXrShort
     *            the currentXrShort to set
     */
    public void setCurrentXrShort(Double currentXrShort) {
        this.currentXrShort = currentXrShort;
    }

    /**
     * @return the currentXrLong
     */
    public Double getCurrentXrLong() {
        return currentXrLong;
    }

    /**
     * @param currentXrLong
     *            the currentXrLong to set
     */
    public void setCurrentXrLong(Double currentXrLong) {
        this.currentXrLong = currentXrLong;
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
     * @return the currentIntXrShort
     */
    public Double getCurrentIntXrShort() {
        return currentIntXrShort;
    }

    /**
     * @param currentIntXrShort
     *            the currentIntXrShort to set
     */
    public void setCurrentIntXrShort(Double currentIntXrShort) {
        this.currentIntXrShort = currentIntXrShort;
    }

    /**
     * @return the currentIntXrLong
     */
    public Double getCurrentIntXrLong() {
        return currentIntXrLong;
    }

    /**
     * @param currentIntXrLong
     *            the currentIntXrLong to set
     */
    public void setCurrentIntXrLong(Double currentIntXrLong) {
        this.currentIntXrLong = currentIntXrLong;
    }

    /**
     * @return the maxTime
     */
    public Date getMaxTime() {
        return maxTime;
    }

    /**
     * @param maxTime
     *            the maxTime to set
     */
    public void setMaxTime(Date maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * @return the maxXrShort
     */
    public Double getMaxXrShort() {
        return maxXrShort;
    }

    /**
     * @param maxXrShort
     *            the maxXrShort to set
     */
    public void setMaxXrShort(Double maxXrShort) {
        this.maxXrShort = maxXrShort;
    }

    /**
     * @return the maxXrLong
     */
    public Double getMaxXrLong() {
        return maxXrLong;
    }

    /**
     * @param maxXrLong
     *            the maxXrLong to set
     */
    public void setMaxXrLong(Double maxXrLong) {
        this.maxXrLong = maxXrLong;
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
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the endXrShort
     */
    public Double getEndXrShort() {
        return endXrShort;
    }

    /**
     * @param endXrShort
     *            the endXrShort to set
     */
    public void setEndXrShort(Double endXrShort) {
        this.endXrShort = endXrShort;
    }

    /**
     * @return the endXrLong
     */
    public Double getEndXrLong() {
        return endXrLong;
    }

    /**
     * @param endXrLong
     *            the endXrLong to set
     */
    public void setEndXrLong(Double endXrLong) {
        this.endXrLong = endXrLong;
    }

    /**
     * @return the endXRatio
     */
    public Double getEndXRatio() {
        return endXRatio;
    }

    /**
     * @param endXRatio
     *            the endXRatio to set
     */
    public void setEndXRatio(Double endXRatio) {
        this.endXRatio = endXRatio;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        GoesXrayEvent report = new GoesXrayEvent();
        return report;
    }

}
