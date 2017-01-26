package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.List;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

@DynamicSerialize
public class GetAssignedRegionReportsResults implements IResults {
    @DynamicSerializeElement
    private List<RegionReport> reports = null;

    public GetAssignedRegionReportsResults() {

    }

    public void setReports(List<RegionReport> reports) {
        this.reports = reports;
    }

    public List<RegionReport> getReports() {
        return this.reports;
    }

    @Override
    public int numResults() {
        return this.reports != null ? this.reports.size() : 0;
    }

}
