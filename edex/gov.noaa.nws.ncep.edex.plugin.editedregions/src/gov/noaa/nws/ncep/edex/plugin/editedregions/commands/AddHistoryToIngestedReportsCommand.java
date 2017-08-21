package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.sql.SQLException;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.AddHistoryToIngestedReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionHistoryReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.AddHistoryToIngestedReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionHistoryReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.AddHistoryToIngestedReportsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionHistoryReportResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants.REGION_REPORT_CHANGE_TYPE;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionHistoryReportDao;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;

/**
 * The command class that is executed to get region reports that do not have
 * attached history items.
 * 
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 19, 2016  R9583     sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author alockleigh
 * @version 1.0
 */
public class AddHistoryToIngestedReportsCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private AddHistoryToIngestedReportsRequest request = null;

    /**
     * 
     */
    // private EventsDao eventsDao = null;

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(AddHistoryToIngestedReportsCommand.class);

    public AddHistoryToIngestedReportsCommand() {

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
     * ()
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
        this.request = (AddHistoryToIngestedReportsRequest) request;
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

        AddHistoryToIngestedReportsResults results = new AddHistoryToIngestedReportsResults();
        results.setSuccessful(true);
        try {
            RegionHistoryReportDao historyDao = new RegionHistoryReportDao();
            RegionReportsDao reportsDao = new RegionReportsDao();

            List<Integer> reportIds = historyDao.getReportsWithoutHistory();

            for (Integer reportId : reportIds) {
                CreateRegionHistoryReportRequest historyRequest = new CreateRegionHistoryReportRequest();
                historyRequest.setChangeType(REGION_REPORT_CHANGE_TYPE.CREATE);
                historyRequest.setRegionReportId(reportId);
                historyRequest
                        .setNewReport(reportsDao.getRegionReport(reportId));

                CreateRegionHistoryReportCommand historyCommand = new CreateRegionHistoryReportCommand();
                historyCommand.setRequest(historyRequest);
                CreateRegionHistoryReportResponse historyResponse = (CreateRegionHistoryReportResponse) historyCommand
                        .execute();
                CreateRegionHistoryReportResults historyResults = (CreateRegionHistoryReportResults) historyResponse
                        .getResults();
                if (!historyResults.isSuccessful()) {
                    // Set flag to false
                    results.setSuccessful(false);
                    setError(new EditedRegionsException(
                            "Error recording history."));
                }
            }

        } catch (PluginException px) {
            results.setSuccessful(false);
            setError(new EditedRegionsException(px));
        } catch (SQLException sqlEx) {
            results.setSuccessful(false);
            setError(new EditedRegionsException(sqlEx));
        }
        this.setEndTime();

        statusHandler
                .info("Finish Executing " + this.getClass().getSimpleName());

        return createResponse(results);
    }

    /**
     * Create a response based on the results provided
     * 
     * @param results
     * @return IResponse
     */
    private IResponse createResponse(IResults results) {
        AddHistoryToIngestedReportsResponse response = new AddHistoryToIngestedReportsResponse();

        if (this.hasError()) {
            response.setError(this.getError());
        } else {
            response.setResults(results);
        }

        // populate the response and the results
        response.setRequest(this.getRequest());
        response.setProcessingTime(this.getProcessingTime());

        return response;

    }

}
