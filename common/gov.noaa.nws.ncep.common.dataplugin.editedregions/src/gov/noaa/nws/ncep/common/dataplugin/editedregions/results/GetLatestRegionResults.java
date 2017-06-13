package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

@DynamicSerialize
public class GetLatestRegionResults implements IResults {

    @DynamicSerializeElement
    private Integer latestRegion = null;

    /**
     * Constructor
     */
    public GetLatestRegionResults() {

    }

	/**
	 * @return the latestRegion
	 */
	public Integer getLatestRegion() {
		return this.latestRegion;
	}

	/**
	 * @param latestRegion the latestRegion to set
	 */
	public void setLatestRegion(Integer latestRegion) {
		this.latestRegion = latestRegion;
	}
    
	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults#numResults()
	 */
	@Override
	public int numResults() {
		if (this.latestRegion == null) {
			return 0;
		} else {
			return 1;
		}
	}

}
