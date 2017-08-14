package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ViewRegionReportHistoryRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ViewRegionReportHistoryResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.ViewRegionReportHistoryResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.RegionHistoryReportUtility;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionHistoryReportDao;

/**
 * The command class that is executed to obtain all history for a given Region
 * Report
 * 
 * TODO complete this
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class ViewRegionReportHistoryCommand extends BaseCommand {

    private final RegionHistoryReportDao historyReportDao = new RegionHistoryReportDao();

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private ViewRegionReportHistoryRequest request = null;

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(ViewRegionReportHistoryCommand.class);

    /**
     * Default Constructor
     */
    public ViewRegionReportHistoryCommand() {
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
        this.request = (ViewRegionReportHistoryRequest) request;

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getRequest()
     */
    @Override
    public IRequest getRequest() {
        return this.request;
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

        statusHandler
                .info("Starting Executing " + this.getClass().getSimpleName());

        this.setStartTime();

        ViewRegionReportHistoryResponse response = new ViewRegionReportHistoryResponse();
        ViewRegionReportHistoryResults results = new ViewRegionReportHistoryResults();

        if (this.request.isValid()) {
            Integer reportId = this.request.getReportId();
            List<RegionHistoryReport> reports = historyReportDao
                    .getHistoryReports(reportId);

            List<Map<String, String>> reportsList = new ArrayList<>();
            for (RegionHistoryReport report : reports) {
                Map<String, String> reportMap = RegionHistoryReportUtility
                        .convertToMap(report);
                reportsList.add(reportMap);
            }

            results.setHistoryReportList(reportsList);
            response.setResults(results);

        }
        this.setEndTime();

        response.setError(this.getError());
        response.setProcessingTime(this.getProcessingTime());

        statusHandler
                .info("Finishing Executing " + this.getClass().getSimpleName());

        return response;
    }

}
