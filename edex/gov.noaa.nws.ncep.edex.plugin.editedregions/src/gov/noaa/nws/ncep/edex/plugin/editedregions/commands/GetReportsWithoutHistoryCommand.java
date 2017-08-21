package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.sql.SQLException;
import java.util.List;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetReportsWithoutHistoryRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ExitResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetReportsWithoutHistoryResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionHistoryReportDao;

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
public class GetReportsWithoutHistoryCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private GetReportsWithoutHistoryRequest request = null;

    /**
     * 
     */
    // private EventsDao eventsDao = null;

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(GetReportsWithoutHistoryCommand.class);

    public GetReportsWithoutHistoryCommand() {

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
        this.request = (GetReportsWithoutHistoryRequest) request;
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

        GetReportsWithoutHistoryResults results = new GetReportsWithoutHistoryResults();
        try {
            RegionHistoryReportDao historyDao = new RegionHistoryReportDao();
            List<Integer> reportIds = historyDao.getReportsWithoutHistory();
            results.setReportIds(reportIds);

        } catch (SQLException ex) {
            setError(new EditedRegionsException(ex));
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
        ExitResponse response = new ExitResponse();

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
