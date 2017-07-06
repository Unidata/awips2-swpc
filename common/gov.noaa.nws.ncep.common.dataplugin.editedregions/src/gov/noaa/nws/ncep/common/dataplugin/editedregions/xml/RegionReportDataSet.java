package gov.noaa.nws.ncep.common.dataplugin.editedregions.xml;

import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;

/**
 * TODO Review as it my not be needed in subsequent releases of ER
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
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "dataset")
public class RegionReportDataSet {

    /**
     * 
     * List of region reports.
     */
    @DynamicSerializeElement
    @XmlElement(name = "data-item")
    private List<RegionReport> reports;

    public RegionReportDataSet() {
        super();
    }

    /**
     * 
     * @return the list of region reports.
     */
    public List<RegionReport> getRegionReports() {
        return reports;
    }

    /**
     * 
     * @param reports
     *            the list of reports to set.
     */
    public void setRegionReports(List<RegionReport> reports) {
        this.reports = Objects.requireNonNull(reports);
    }

}
