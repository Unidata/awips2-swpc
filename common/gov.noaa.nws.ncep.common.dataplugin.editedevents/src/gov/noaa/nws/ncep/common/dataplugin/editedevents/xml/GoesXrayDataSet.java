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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * The GoesXrayDataSet class represents the "dataset" element in the input xml
 * file containing goes-xray events data.
 * 
 * <pre>
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#     Engineer    Description
 * -------      -------     --------    -----------
 * 11/09/2015   R9583       sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 * 
 */
@DynamicSerialize
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "dataset")
public class GoesXrayDataSet {

    /**
     * List of goes-xray event reports
     */
    @DynamicSerializeElement
    @XmlElement(name = "goes-xray-event-report")
    private List<GoesXrayEvent> xrayEvents = new ArrayList<GoesXrayEvent>();

    public GoesXrayDataSet() {
        super();
    }

    /**
     * @return the xrayEvents
     */
    public List<GoesXrayEvent> getXrayEvents() {
        return xrayEvents;
    }

    /**
     * @param xrayEvents
     *            the xrayEvents to set
     */
    public void setXrayEvents(List<GoesXrayEvent> xrayEvents) {
        this.xrayEvents = xrayEvents;
    }

}
