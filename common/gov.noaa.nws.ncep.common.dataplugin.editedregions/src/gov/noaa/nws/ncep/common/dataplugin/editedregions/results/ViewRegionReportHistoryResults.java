package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.List;
import java.util.Map;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

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
    private List<Map<String, String>> historyReportList = null;

    @Override
    public int numResults() {
        return (historyReportList != null) ? historyReportList.size() : 0;
    }

    public List<Map<String, String>> getHistoryReportList() {
        return historyReportList;
    }

    public void setHistoryReportList(
            List<Map<String, String>> historyReportList) {
        this.historyReportList = historyReportList;
    }

}
