package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import java.util.Collections;
import java.util.List;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionReportResults;

public final class EditRegionsServerUtil {
    private EditRegionsServerUtil() {
        throw new Error(String.format("Cannot create instance of %s!",
                EditRegionsServerUtil.class));
    }

    public static List<RegionReport> getRegionReports()
            throws EditedRegionsException {
        // TODO: verify implementation and make changes as appropriate
    	
    	GetRegionReportsRequest request = new GetRegionReportsRequest();
    	
    	request.setObtainAssignedReports(true);
    	request.setObtainUnassignedReports(true);
    	
    	if (request.isValid()) {
    		
    	}
    	
        return Collections.emptyList();
    }

    public static Integer saveNewRegionReport(RegionReport report)
            throws EditedRegionsException {
        CreateRegionReportRequest request = new CreateRegionReportRequest();
        request.setRegionReport(report);

        if (request.isValid()) {
            CreateRegionReportResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.getResults() != null && !response.hasErrors()) {
                CreateRegionReportResults result = (CreateRegionReportResults) response
                        .getResults();
                return Integer.valueOf(result.getReportID());
            }
        }
        return null;

    }
}
