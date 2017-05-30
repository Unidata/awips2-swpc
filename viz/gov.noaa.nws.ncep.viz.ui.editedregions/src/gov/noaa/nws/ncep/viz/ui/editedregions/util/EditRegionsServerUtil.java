package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UpdateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.UpdateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionReportResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetRegionReportsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.UpdateRegionReportResults;

public final class EditRegionsServerUtil {
    private EditRegionsServerUtil() {
        throw new Error(String.format("Cannot create instance of %s!",
                EditRegionsServerUtil.class));
    }

    public static GetRegionReportsResults getRegionReports(
            boolean bAssignedReports, boolean bUnassignedReports)
                    throws EditedRegionsException {
        // TODO: verify implementation and make changes as appropriate

        GetRegionReportsRequest request = new GetRegionReportsRequest();

        request.setObtainAssignedReports(bAssignedReports);
        request.setObtainUnassignedReports(bUnassignedReports);

        if (request.isValid()) {
            GetRegionReportsResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.getResults() != null && !response.hasErrors()) {
                GetRegionReportsResults result = (GetRegionReportsResults) response
                        .getResults();
                return result;
            }
        }

        return null;
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

    public static RegionReport updateRegionReport(RegionReport report)
            throws EditedRegionsException {
        UpdateRegionReportRequest request = new UpdateRegionReportRequest();
        request.setRegionReport(report);

        if (request.isValid()) {
            UpdateRegionReportResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.getResults() != null) {
                UpdateRegionReportResults results = (UpdateRegionReportResults) response
                        .getResults();
                return results.getReport();
            }
        }
        return null;
    }
}
