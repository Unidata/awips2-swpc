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
public class CreateRegionHistoryReportResults implements IResults {

    @DynamicSerializeElement
    private final boolean success;

    /**
     * Default constructor.
     */
    public CreateRegionHistoryReportResults(boolean success) {
        this.success = success;
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

    public boolean isSuccessful() {
        return success;
    }

}
