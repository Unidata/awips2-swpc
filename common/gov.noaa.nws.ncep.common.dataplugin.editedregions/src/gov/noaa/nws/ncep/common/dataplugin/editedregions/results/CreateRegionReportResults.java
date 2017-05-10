package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

@DynamicSerialize
public class CreateRegionReportResults implements IResults {

    @DynamicSerializeElement
    private int reportID = 0;

    /**
     * Default constructor.
     */
    public CreateRegionReportResults() {

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
     * Set the report ID.
     * 
     * @param reportID
     */
    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    /**
     * @return the region ID.
     */
    public int getReportID() {
        return this.reportID;
    }

}
