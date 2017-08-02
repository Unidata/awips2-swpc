package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

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
    private Integer historyReportId;

    @DynamicSerializeElement
    private RegionHistoryReport historyReport;

    @Override
    public int numResults() {
        return 1;
    }

    public Integer getHistoryReportId() {
        return historyReportId;
    }

    public void setHistoryReportId(Integer historyReportId) {
        this.historyReportId = historyReportId;
    }

    public RegionHistoryReport getHistoryReport() {
        return historyReport;
    }

    public void setHistoryReport(RegionHistoryReport historyReport) {
        this.historyReport = historyReport;
    }

}
