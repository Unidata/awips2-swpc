package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.List;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

@DynamicSerialize
public class GetRegionReportsResults implements IResults {
    
	@DynamicSerializeElement
    private List<RegionReport> unAssignedReports = null;
	
	@DynamicSerializeElement
    private List<RegionReport> assignedRegionReports = null;

    /**
     * Constructor
     */
    public GetRegionReportsResults() {

    }

	/**
	 * @return the unAssignedReports
	 */
	public List<RegionReport> getUnAssignedReports() {
		return unAssignedReports;
	}

	/**
	 * @param unAssignedReports the unAssignedReports to set
	 */
	public void setUnAssignedReports(List<RegionReport> unAssignedReports) {
		this.unAssignedReports = unAssignedReports;
	}

	/**
	 * @return the assignedRegionReports
	 */
	public List<RegionReport> getAssignedRegionReports() {
		return assignedRegionReports;
	}

	/**
	 * @param assignedRegionReports the assignedRegionReports to set
	 */
	public void setAssignedRegionReports(List<RegionReport> assignedRegionReports) {
		this.assignedRegionReports = assignedRegionReports;
	}
	
    @Override
    public int numResults() {
    	
    	int resultCount = this.unAssignedReports.size() +
    						this.assignedRegionReports.size();
    	
        return resultCount;
    }

}
