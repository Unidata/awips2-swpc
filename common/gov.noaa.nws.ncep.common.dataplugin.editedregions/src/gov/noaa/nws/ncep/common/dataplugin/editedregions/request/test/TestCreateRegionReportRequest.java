package gov.noaa.nws.ncep.common.dataplugin.editedregions.request.test;

import java.util.Objects;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionReportResults;

/**
 * @author jtravis
 *
 */
public class TestCreateRegionReportRequest {

    /**
     * @param args
     */

    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(TestCreateRegionReportRequest.class);

    public static void main(String[] args) {

        CreateRegionReportRequest request = new CreateRegionReportRequest();
        RegionReport report = new RegionReport();

        Gateway gateway = Gateway.getInstance();

        // -------------Region Report Parameters TEST 001-----------
        String reportLocation = "N03E82";
        String location = "N03E72";
        String observatory = "JST";
        String type = "spt";
        String compact = "1";
        String spotclass = "Hsx";
        String magclass = "A";
        int station = 16320;
        int quality = 2;
        int region = 2625;
        int latitude = 3;
        int reportLongitude = 82;
        int longitude = 72;
        int carlon = 253;
        int extent = 1;
        int area = 30;
        int numspots = 1;
        int zurich = 7;
        int penumbra = 2;
        int magcode = 1;
        int obsid = 4;
        int reportStatus = 3;
        boolean validSpotClass = true;

        report.setStation(station);
        report.setObservatory(observatory);
        report.setType(type);
        report.setQuality(quality);
        report.setRegion(region);
        report.setLatitude(latitude);
        report.setReportLongitude(reportLongitude);
        report.setLongitude(longitude);
        report.setReportLocation(reportLocation);
        report.setLocation(location);
        report.setCarlon(carlon);
        report.setExtent(extent);
        report.setArea(area);
        report.setNumspots(numspots);
        report.setZurich(zurich);
        report.setPenumbra(penumbra);
        report.setCompact(compact);
        report.setSpotclass(spotclass);
        report.setMagcode(magcode);
        report.setMagclass(magclass);
        report.setObsid(obsid);
        report.setReportStatus(reportStatus);
        report.setValidSpotClass(validSpotClass);

        request.setRegionReport(report);
        try {
            CreateRegionReportResponse response = gateway.submit(request);
            Objects.requireNonNull(response, "response");
            if (response.hasErrors()) {
                statusHandler.handle(Priority.ERROR,
                        "Error processing request.", response.getError());
            } else {
                CreateRegionReportResults results = (CreateRegionReportResults) response
                        .getResults();
                statusHandler.handle(Priority.INFO, "Request received.");
                statusHandler.handle(Priority.INFO,
                        String.format("Report ID: %d", results.getReportID()));
            }

        } catch (EditedRegionsException e) {
            statusHandler.handle(Priority.ERROR, "Unable to submit request.",
                    e);
        }

    }

}
