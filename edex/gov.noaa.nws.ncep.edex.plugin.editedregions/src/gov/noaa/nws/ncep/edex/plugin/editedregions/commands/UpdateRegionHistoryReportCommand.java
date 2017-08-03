package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UpdateRegionHistoryReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.UpdateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.UpdateRegionReportResults;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;

/**
 * The sub-command class that is executed to add a region history report item.
 * 
 * 
 * @author alockleigh
 * @version 1.0
 */
public class UpdateRegionHistoryReportCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private UpdateRegionHistoryReportRequest request = null;

    /**
     * Dao for EventBin records
     */
    private RegionReportsDao regionReportsDao = null;

    /**
     * Default Constructor
     */
    public UpdateRegionHistoryReportCommand() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getError()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getStartTime()
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getEndTime()
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * isValid()
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * setRequest(gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf
     * .IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (UpdateRegionHistoryReportRequest) request;

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getRequest()
     */
    @Override
    public IRequest getRequest() {
        return (IRequest) this.request;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * execute()
     */
    @Override
    public IResponse execute() {
        this.setStartTime();

        // RegionReport updatedReport = null;

        // this.regionReportsDao = new RegionReportsDao();
        //
        // RegionReport report = this.request.getRegionReport();
        // report.setId(this.request.getRegionReportID());
        //
        // this.regionReportsDao.update(report);
        // updatedReport = (RegionReport) this.regionReportsDao
        // .queryById(report.getId());

        this.setEndTime();

        return this.createResponse(null);
    }

    /**
     * Create the response that will be returned from executing the request
     * 
     * @param results
     * @return IResponse
     */
    private IResponse createResponse(RegionReport report) {
        UpdateRegionReportResponse response = new UpdateRegionReportResponse();

        if (this.hasError()) {
            response.setError(this.getError());
        } else {
            UpdateRegionReportResults results = new UpdateRegionReportResults();
            results.setReport(report);
            response.setResults(results);
        }

        response.setRequest(this.getRequest());
        response.setProcessingTime(this.getProcessingTime());
        return response;
    }

}
