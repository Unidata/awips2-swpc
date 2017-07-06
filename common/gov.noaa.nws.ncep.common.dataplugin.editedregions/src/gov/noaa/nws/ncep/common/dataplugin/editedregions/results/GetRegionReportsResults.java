package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.Map;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Class holding results from executing the GetRegionReports command
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
public class GetRegionReportsResults implements IResults {

    /**
     * Holds the unassigned region reports
     */
    @DynamicSerializeElement
    private Map<Integer, RegionReport> unAssignedReports = null;

    /**
     * Holds the assigned region reports
     */
    @DynamicSerializeElement
    private Map<Integer, RegionReport> assignedRegionReports = null;

    /**
     * Constructor
     */
    public GetRegionReportsResults() {

    }

    /**
     * @return the unAssignedReports
     */
    public Map<Integer, RegionReport> getUnAssignedReports() {
        return unAssignedReports;
    }

    /**
     * @param unAssignedReports
     *            the unAssignedReports to set
     */
    public void setUnAssignedReports(
            Map<Integer, RegionReport> unAssignedReports) {
        this.unAssignedReports = unAssignedReports;
    }

    /**
     * @return the assignedRegionReports
     */
    public Map<Integer, RegionReport> getAssignedRegionReports() {
        return assignedRegionReports;
    }

    /**
     * @param assignedRegionReports
     *            the assignedRegionReports to set
     */
    public void setAssignedRegionReports(
            Map<Integer, RegionReport> assignedRegionReports) {
        this.assignedRegionReports = assignedRegionReports;
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

        int resultCount = this.unAssignedReports.size()
                + this.assignedRegionReports.size();

        return resultCount;
    }

}
