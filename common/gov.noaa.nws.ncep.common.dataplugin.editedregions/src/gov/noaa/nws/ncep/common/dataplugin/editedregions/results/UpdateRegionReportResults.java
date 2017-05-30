package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

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
