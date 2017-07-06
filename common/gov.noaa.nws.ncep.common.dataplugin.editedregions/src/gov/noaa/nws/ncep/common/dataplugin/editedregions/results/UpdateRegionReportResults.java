package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Results class holding results from executing the UpdateRegionReport command
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
public class UpdateRegionReportResults implements IResults {

    @DynamicSerializeElement
    private RegionReport report;

    /**
     * Default constructor.
     */
    public UpdateRegionReportResults() {

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
        // TODO change to return the real number of results
        return 1;
    }

    /**
     * Set the report object.
     * 
     * @param reportID
     */
    public void setReport(RegionReport report) {
        this.report = report;
    }

    /**
     * @return the report object.
     */
    public RegionReport getReport() {
        return this.report;
    }

}
