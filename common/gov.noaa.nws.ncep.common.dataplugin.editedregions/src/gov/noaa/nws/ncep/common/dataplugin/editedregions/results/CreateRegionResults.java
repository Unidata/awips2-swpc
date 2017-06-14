package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

@DynamicSerialize
public class CreateRegionResults implements IResults {

    @DynamicSerializeElement
    private Long createdRegion = null;

    /**
     * Constructor
     */
    public CreateRegionResults() {

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
        if (this.createdRegion == null) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * @return the createdRegion
     */
    public Long getCreatedRegion() {
        return this.createdRegion;
    }

    /**
     * @param createdRegion
     *            the createdRegion to set
     */
    public void setCreatedRegion(Long createdRegion) {
        this.createdRegion = createdRegion;
    }

}
