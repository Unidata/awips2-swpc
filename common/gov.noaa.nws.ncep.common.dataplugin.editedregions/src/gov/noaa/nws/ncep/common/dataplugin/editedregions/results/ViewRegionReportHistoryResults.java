package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.Collections;
import java.util.Map;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * 
 * Class holding results from ViewRegionReportHistoryCommand
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Aug 2, 2017            alockleigh     Initial creation
 *
 * </pre>
 *
 * @author alockleigh
 */
@DynamicSerialize
public class ViewRegionReportHistoryResults implements IResults {

    @DynamicSerializeElement
    private Map<Integer, RegionHistoryReport> historyReportsMap = Collections
            .emptyMap();

    @Override
    public int numResults() {
        return historyReportsMap.size();
    }

    public Map<Integer, RegionHistoryReport> getHistoryReportsMap() {
        return historyReportsMap;
    }

    public void setHistoryReportsMap(
            Map<Integer, RegionHistoryReport> historyReportsMap) {
        this.historyReportsMap = historyReportsMap;
    }

}