package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.List;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * 
 * Class used to hold results from GetRegionsWithoutHistoryCommand
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Aug 21, 2017            alockleigh     Initial creation
 *
 * </pre>
 *
 * @author alockleigh
 */
@DynamicSerialize
public class AddHistoryToIngestedReportsResults implements IResults {

    @DynamicSerializeElement
    private List<Integer> reportIds;

    @Override
    public int numResults() {
        return (reportIds != null) ? reportIds.size() : 0;
    }

    /**
     * @return the reportIds
     */
    public List<Integer> getReportIds() {
        return reportIds;
    }

    /**
     * @param reportIds
     *            the reportIds to set
     */
    public void setReportIds(List<Integer> reportIds) {
        this.reportIds = reportIds;
    }

}
