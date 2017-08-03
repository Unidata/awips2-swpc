package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

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
 * @author alockleigh
 */
@DynamicSerialize
public class UpdateRegionHistoryReportResults implements IResults {

    @DynamicSerializeElement
    private final boolean updated;

    /**
     * Default constructor.
     */
    public UpdateRegionHistoryReportResults(boolean updated) {
        this.updated = updated;
    }

    /**
     * The number of results. This always will return 0 because there are no
     * items to return, just a boolean value to indicate whether the record was
     * updated.
     */
    @Override
    public int numResults() {
        return 0;
    }

    public boolean isUpdated() {
        return updated;
    }

}
