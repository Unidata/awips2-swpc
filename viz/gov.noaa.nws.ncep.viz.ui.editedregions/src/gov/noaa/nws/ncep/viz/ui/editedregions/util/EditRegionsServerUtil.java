package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import java.util.Collections;
import java.util.List;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetAssignedRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetAssignedRegionReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetAssignedRegionReportsResults;

public final class EditRegionsServerUtil {
    private EditRegionsServerUtil() {
        throw new Error(String.format("Cannot create instance of %s!",
                EditRegionsServerUtil.class));
    }

    public static List<RegionReport> getRegionReports()
            throws EditedRegionsException {
        GetAssignedRegionReportsRequest request = new GetAssignedRegionReportsRequest();

        Region region = new Region();
        region.setId(3);

        request.setRegionID(Integer.valueOf(region.getId()));
        request.setRegion(region);

        if (request.isValid()) {

            GetAssignedRegionReportsResponse response = Gateway.getInstance()
                    .submit(request);

            if (response.getResults() != null && !response.hasErrors()) {
                GetAssignedRegionReportsResults results = (GetAssignedRegionReportsResults) response
                        .getResults();
                return results.getReports();
            }
        }
        return Collections.emptyList();
    }
}
