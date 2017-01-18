package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.List;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

@DynamicSerialize
public class GetAssignedRegionReportsResults implements IResults {
    @DynamicSerializeElement
    private List<Region> regions = null;

    public GetAssignedRegionReportsResults() {

    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Region> getRegions() {
        return this.regions;
    }

    @Override
    public int numResults() {
        return this.regions != null ? this.regions.size() : 0;
    }

}
