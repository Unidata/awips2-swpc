package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetAssignedRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetAssignedRegionReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetAssignedRegionReportsResults;

/**
 * The command class that is executed to add an event
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class GetAssignedRegionReportsCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private GetAssignedRegionReportsRequest request = null;

    /**
     * Default Constructor
     */
    public GetAssignedRegionReportsCommand() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getError()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * hasError()
     */
    @Override
    public boolean hasError() {
        if (this.error == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getStartTime()
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getEndTime()
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getProcessingTime()
     */
    @Override
    public long getProcessingTime() {
        long time = 0L;

        time = this.getEndTime() - this.getStartTime();

        return time;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#isValid
     * () TODO - do we really need to validate the request here... TODO - the
     * request should be validated before adding it to the command
     */
    @Override
    public boolean isValid() {
        if (this.request != null && this.request.isValid()) {

            // a request is associated with the command
            // and the request is valid
            return true;

        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.
     * IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (GetAssignedRegionReportsRequest) request;

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getRequest()
     */
    @Override
    public IRequest getRequest() {
        return (IRequest) this.request;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute
     * ()
     */
    @Override
    public IResponse execute() {
        this.setStartTime();

        List<Region> regions = new ArrayList<Region>();

        Region region = new Region();
        region.setId(request.getRegionID().intValue());
        regions.add(region);

        RegionReport report1 = new RegionReport();
        report1.setQ("2");
        report1.setRegion(new Region());
        report1.setArea("30");
        report1.setLl("003");
        report1.setLo("072");
        report1.setArea("30");
        report1.setNumSpots(1);
        report1.setSpotClass("Hsx");

        RegionReport report2 = new RegionReport();
        report2.setQ("2");
        report2.setRegion(new Region());
        report2.setArea("00");
        report2.setLl("14");
        report2.setLo("61");
        report2.setNumSpots(1);
        report2.setSpotClass("Axx");

        this.setEndTime();

        return this.createResponse(Arrays.asList(report1));
    }

    /**
     * @param results
     * @return IResponse
     */
    private IResponse createResponse(List<RegionReport> regions) {

        GetAssignedRegionReportsResults results = new GetAssignedRegionReportsResults();
        results.setReports(regions);

        GetAssignedRegionReportsResponse response = new GetAssignedRegionReportsResponse();

        if (this.hasError()) {
            response.setError(this.getError());
        } else {
            response.setResults(results);
        }

        response.setRequest(this.getRequest());
        response.setProcessingTime(this.getProcessingTime());

        return response;
    }

}
